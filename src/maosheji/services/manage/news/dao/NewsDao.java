/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package maosheji.services.manage.news.dao;

import java.util.List;

import maosheji.core.DaoManager;
import maosheji.services.manage.news.bean.News;


/**
 * @author huangf
 * @param <T>
 */
public interface NewsDao extends DaoManager<News> {

	/**
	 * @param e
	 * @return
	 */
	List<News> selecIndexNews(News e);

	/**
	 * @param news
	 */
	void sync(News news);

	void updateDownOrUp(News news);

	int selectCount(News news);

}
