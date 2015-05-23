/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package maosheji.services.front.indexImg.dao;

import java.util.List;

import maosheji.core.DaoManager;
import maosheji.services.front.indexImg.bean.IndexImg;


public interface IndexImgDao extends DaoManager<IndexImg> {

	/**
	 * @param i
	 * @return
	 */
	List<IndexImg> getImgsShowToIndex(int i);

}
