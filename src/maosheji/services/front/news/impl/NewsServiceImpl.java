/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package maosheji.services.front.news.impl;

import java.util.List;

import maosheji.core.ServersManager;
import maosheji.core.dao.page.PagerModel;
import maosheji.services.front.news.NewsService;
import maosheji.services.front.news.bean.News;
import maosheji.services.front.news.dao.NewsDao;


/**
 * @author huangf
 */
public class NewsServiceImpl extends ServersManager<News> implements
		NewsService {
	private NewsDao newsDao;
	
	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	/**
	 * @param e
	 */
	public List<News> selecIndexNews(News e) {
		return newsDao.selecIndexNews(e);
	}

	@Override
	public List<String> selectAllMd5() {
		// TODO Auto-generated method stub
		return newsDao.selectAllMd5();
	}

	@Override
	public void updateInBlackList(String[] ids) {
		// TODO Auto-generated method stub
//		if(ids==null || ids.length==0){
//			return;
//		}
//		
//		for(int i=0;i<ids.length;i++){
//			String e = ids[i];
//			newsDao.updateInBlackList(e);
//		}
	}

	@Override
	public void sync(String[] ids, int status) {
//		if(ids==null || ids.length==0){
//			return;
//		}
//		
//		for(int i=0;i<ids.length;i++){
//			News news = new News();
//			news.setId(ids[i]);
////			news.setStatus(status);
//			newsDao.sync(news);
//		}
	}

	@Override
	public List<News> selectNoticeList(News news) {
		return newsDao.selectNoticeList(news);
	}

	@Override
	public News selectSimpleOne(News news) {
		return newsDao.selectSimpleOne(news);
	}

}
