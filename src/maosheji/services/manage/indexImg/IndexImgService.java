/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package maosheji.services.manage.indexImg;

import java.util.List;

import maosheji.core.Services;
import maosheji.services.manage.indexImg.bean.IndexImg;
import maosheji.services.manage.product.bean.Product;


/**
 * @author huangf
 */
public interface IndexImgService extends Services<IndexImg> {

	/**
	 * 加载图片显示到门户
	 * @param i
	 */
	List<IndexImg> getImgsShowToIndex(int i);

}
