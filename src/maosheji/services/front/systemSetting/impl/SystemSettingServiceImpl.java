package maosheji.services.front.systemSetting.impl;import maosheji.core.ServersManager;import maosheji.services.front.systemSetting.SystemSettingService;import maosheji.services.front.systemSetting.bean.SystemSetting;import maosheji.services.front.systemSetting.dao.SystemSettingDao;public class SystemSettingServiceImpl extends ServersManager<SystemSetting>		implements SystemSettingService {	private SystemSettingDao systemSettingDao;	public void setSystemSettingDao(SystemSettingDao systemSettingDao) {		this.systemSettingDao = systemSettingDao;	}}