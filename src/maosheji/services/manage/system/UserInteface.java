package maosheji.services.manage.system;

import maosheji.core.Services;
import maosheji.core.dao.page.PagerModel;
import maosheji.core.system.bean.User;

public interface UserInteface extends Services<User> {
	/**
	 * @param e
	 * @return
	 */
	public User login(User e);
}
