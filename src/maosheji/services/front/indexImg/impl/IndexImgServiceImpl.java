/**
 * 2012-7-8
 * jqsl2012@163.com
 */
package maosheji.services.front.indexImg.impl;

import java.util.List;

import maosheji.core.ServersManager;
import maosheji.services.front.indexImg.IndexImgService;
import maosheji.services.front.indexImg.bean.IndexImg;
import maosheji.services.front.indexImg.dao.IndexImgDao;


/**
 * @author huangf
 */
public class IndexImgServiceImpl extends ServersManager<IndexImg> implements
		IndexImgService {

	private IndexImgDao indexImgDao;

	public IndexImgDao getIndexImgDao() {
		return indexImgDao;
	}

	public void setIndexImgDao(IndexImgDao indexImgDao) {
		this.indexImgDao = indexImgDao;
	}

	@Override
	public List<IndexImg> getImgsShowToIndex(int i) {
		return indexImgDao.getImgsShowToIndex(i);
	}

}
