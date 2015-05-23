package maosheji.services.front.account.impl;import java.io.IOException;import java.text.MessageFormat;import java.util.Date;import java.util.HashMap;import java.util.Iterator;import java.util.Map;import java.util.Map.Entry;import maosheji.core.FrontContainer;import maosheji.core.ServersManager;import maosheji.core.front.SystemManager;import maosheji.core.util.FreemarkerTemplateUtil;import maosheji.core.util.MD5;import maosheji.core.util.MailUtil;import maosheji.services.front.account.AccountService;import maosheji.services.front.account.bean.Account;import maosheji.services.front.account.dao.AccountDao;//import maosheji.services.front.email.EmailService;//import maosheji.services.front.email.bean.Email;//import maosheji.services.front.notifyTemplate.bean.NotifyTemplate;import maosheji.services.manage.accountRank.bean.AccountRank;import org.apache.commons.lang.StringUtils;import org.apache.commons.lang.time.DateUtils;import org.apache.log4j.Logger;import freemarker.template.TemplateException;public class AccountServiceImpl extends ServersManager<Account> implements		AccountService {	private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);	private AccountDao accountDao;	//private EmailService emailService;	/*public void setEmailService(EmailService emailService) {		this.emailService = emailService;	}*/	public void setAccountDao(AccountDao accountDao) {		this.accountDao = accountDao;	}	public int selectCount(Account e) {		return accountDao.selectCount(e);	}		/**	 * 更新会员信息。	 * 如果会员等级发生变动，则需要使用邮件、短信、站内信的方式告知用户。	 *///	@Override//	public int update(Account e) {//		int r = 0;//		if(StringUtils.isNotBlank(e.getRank())){//			Account acc = accountDao.selectOne(e);//			if(!acc.getRank().equals(e.getRank())){//				int a = Integer.valueOf(acc.getRank().substring(1));//				int b = Integer.valueOf(e.getRank().substring(1));//				if( a > b){//					//会员等级降了//					r = -1;//				}else if(a < b){//					//会员等级升了//					r = 1;//				}else {//					//等级没变化//					r = 0;//				}//			}//		}//		//		super.update(e);//		//		if(StringUtils.isNotBlank(e.getRank())){//			if(r==-1){//				//邮件、短信、或站内信通知用户//			}else if(r==-1){//				//邮件、短信、或站内信通知用户//			}//		}//		return 1; //	}		@Override	public void updateScore(Account acc) {		if(StringUtils.isBlank(acc.getAccount())){			throw new NullPointerException();		}				synchronized (FrontContainer.update_account_score_lock) {			Account account = accountDao.selectAccountScore(acc.getAccount());			if(account==null){				throw new NullPointerException();			}						acc.setScore(acc.getAddScore() + account.getScore());						logger.error("SystemManager.accountRankMap = " + SystemManager.accountRankMap.size());			for(Iterator<Entry<String, AccountRank>> it = SystemManager.accountRankMap.entrySet().iterator();it.hasNext();){				Entry<String, AccountRank> item = it.next();				AccountRank accountRank = item.getValue();				if (acc.getScore() == accountRank.getMinScore()						|| acc.getScore() == accountRank.getMaxScore()						|| (acc.getScore() > accountRank.getMinScore() && acc								.getScore() < accountRank.getMaxScore())) {					// 得到此范围内的会员等级代号					acc.setRank(accountRank.getCode());										logger.error(">>accountRank.getCode() = " + accountRank.getCode());					break;				}			}						logger.error("account.getRank()="+account.getRank()+",acc.getRank()="+acc.getRank());						//如果新的会员等级代号和旧的一样，则说明会员的等级没有发生任何的变化。不需要更新 也不需要邮件通知			if(acc.getRank().equals(account.getRank())){				acc.setRank(null);			}else{				int oldAccountRank = Integer.valueOf(account.getRank().substring(1));//旧的会员等级				int newAccountRank = Integer.valueOf(acc.getRank().substring(1));//新的会员等级				if(oldAccountRank > newAccountRank){					//会员等级降了				}else if(oldAccountRank < newAccountRank){					//会员等级升了				}								//..通知会员			}						accountDao.updateScore(acc);			//可以邮件、短信、站内信 等方式通知用户，订单完成，积分已经打到用户的账户上了。		}	}		public void insertOutAccount(Account acc){		synchronized (this) {			if(acc==null){				throw new NullPointerException("acc is null");			}						acc.setAccount("_out_"+System.currentTimeMillis());			acc.setNickname("_out_");			accountDao.insert(acc);			logger.error("insertOutAccount.acc="+acc);		}	}	@Override	public void doForget(Account e) {		logger.error("forget...account="+e.getAccount());		if(e==null || StringUtils.isBlank(e.getAccount())){			throw new NullPointerException("请求非法！");		}				Account acc = new Account();		acc.setAccount(e.getAccount());		acc = accountDao.selectOne(acc);		if(acc==null){			throw new NullPointerException("根据账号查询不到指定的会员信息，请联系管理员！");		}				//sendEmail(acc,NotifyTemplate.email_forget_password);	}	/*	@Override	public void sendEmail(Account e,String emailNotifyTemplateCode) {		if(e==null || StringUtils.isBlank(e.getAccount())){			throw new NullPointerException("参数不能为空！");		}				String sign = System.currentTimeMillis()+e.getAccount();				//存储发送的邮件		Email email = new Email();		email.setAccount(e.getAccount());		email.setType(emailNotifyTemplateCode);//Email.email_type_forget);		email.setSign(MD5.md5(sign));		String url = null;		String emailTitle = null;//			String emailHtml = null;		if(emailNotifyTemplateCode.equals(NotifyTemplate.email_forget_password)){//找回密码邮件						url = SystemManager.systemSetting.getWww()+"/user/reset.html?sign="+email.getSign();			emailTitle = "找回myshop的密码";//				emailHtml = MessageFormat.format(SystemManager.systemSetting.getEmailFormat(), email.getAccount() , url);		}else if(emailNotifyTemplateCode.equals(NotifyTemplate.email_reg)){//激活新注册账号的邮件						url = SystemManager.systemSetting.getWww()+"/user/activeAccount.html?sign="+email.getSign();			emailTitle = "激活myshop的账号";//				emailHtml = MessageFormat.format(SystemManager.systemSetting.getActiveAccountEmail(), email.getAccount() , url);		}else if(emailNotifyTemplateCode.equals(NotifyTemplate.email_change_email)){//修改邮箱						url = SystemManager.systemSetting.getWww()+"/user/active.html?sign="+email.getSign();			emailTitle = "激活myshop的账号";//				emailHtml = MessageFormat.format(SystemManager.systemSetting.getChangeEmail(), email.getAccount() , url);		}else{			throw new NullPointerException("发送的邮件类型不明确！");		}		email.setUrl(url);		Date dd = new Date();		email.setStarttime(String.valueOf(dd.getTime()));//当前时间		email.setEndtime(String.valueOf(DateUtils.addHours(dd, 2).getTime()));//当前时间+2小时		email.setNewEmail(e.getNewEmail());//新邮箱						logger.error("sign = "+sign+",email.sign = "+email.getSign()+",e.getEmail() = " + e.getEmail());		//发送邮件到用户的邮箱		MailUtil mail = null;		if(StringUtils.isNotBlank(e.getNewEmail())){			logger.error("sendEmail 1 ");			mail = new MailUtil(e.getNewEmail());//new MailUtil("543089122@qq.com",SystemManager.getInstance().get("from_email_account"),SystemManager.getInstance().get("from_email_password"), SystemManager.getInstance().get("from_eamil_smtpServer"), "myshop注册验证邮件");//用户注册成功发送邮件		}else{			logger.error("sendEmail 2 ");			mail = new MailUtil(e.getEmail());		}		*/			/**		 * 根据模板代号获取模板内容，然后封装数据后作为HTML内容发送到用户的邮箱		 */	/*		String template = SystemManager.notifyTemplateMap.get(NotifyTemplate.email_reg).getTemplate();		if(StringUtils.isBlank(template)){			throw new NullPointerException("本系统可能不支持"+emailNotifyTemplateCode+"此模板代码！请联系管理员.");		}		Map<String,String> data = new HashMap<String, String>();		data.put("nickname", e.getNickname());//会员昵称		data.put("system", SystemManager.systemSetting.getName());//系统名称		data.put("url", url);//激活邮箱URL地址		data.put("servicesPhone", SystemManager.systemSetting.getTel());//系统客服电话		data.put("systemEmail", SystemManager.systemSetting.getEmail());//系统邮箱		data.put("helpUrl", "http://127.0.0.1:8080/help.html");//系统帮助地址		boolean result = mail.startSend(emailTitle,FreemarkerTemplateUtil.freemarkerProcess(data,template));		logger.error("email resule = " + result);				if(result){			email.setSendStatus(Email.email_sendStatus_y);		}else{			email.setSendStatus(Email.email_sendStatus_n);		}		emailService.insert(email);	}	*/	@Override	public void updatePasswordByAccount(Account acc) {		accountDao.updatePasswordByAccount(acc);	}	@Override	public void updateEmailByAccount(Account acc) {		accountDao.updateEmailByAccount(acc);	}	public static void main(String[] args) {//		Object arguments;//		String pattern = "你好{1}";//		System.out.println(MessageFormat.format(pattern , 12,34));				String content = "ab,cc,{1},{2},{0},dd,ff";		Object array[] = {"userName", "password", "2014-10-12"};	      content = MessageFormat.format(content, array);	      System.out.println(content);	}	/*@Override	public void updateDataWhenActiveAccount(Account acc,String account) {		accountDao.update(acc);				//使激活的链接失效		Email email = new Email();		email.setAccount(account);		email.setType(maosheji.services.common.NotifyTemplate.email_reg);		emailService.updateEmailInvalidWhenReg(email);	}*/	@Override	public void updateDataWhenActiveAccount(Account acc, String account) {		// TODO Auto-generated method stub			}}