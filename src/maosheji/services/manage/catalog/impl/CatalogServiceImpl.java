package maosheji.services.manage.catalog.impl;import java.util.HashSet;import java.util.Iterator;import java.util.LinkedList;import java.util.List;import java.util.Set;import maosheji.core.ServersManager;import maosheji.services.manage.catalog.CatalogService;import maosheji.services.manage.catalog.bean.Catalog;import maosheji.services.manage.catalog.dao.CatalogDao;import org.apache.commons.lang.StringUtils;public class CatalogServiceImpl extends ServersManager<Catalog> implements		CatalogService {	private CatalogDao catalogDao;	public void setCatalogDao(CatalogDao catalogDao) {		this.catalogDao = catalogDao;	}	// 加载根节点	public List<Catalog> loadRoot(Catalog e) {		if (e == null) {			e = new Catalog();		}		e.setPid("0");		List<Catalog> root = catalogDao.selectList(e);		for (int i = 0; i < root.size(); i++) {			Catalog ee = root.get(i);			loadChildrenByPid(ee);		}		Catalog top = new Catalog();		top.setName("-");		top.setId("0");				top.setPid("0");		root.add(0, top);		return root;	}	/**	 * 加载指定节点下的全部子节点	 * 	 * @param item	 */	private void loadChildrenByPid(Catalog item) {		Catalog e = new Catalog();		e.setPid(item.getId());		e.setType(item.getType());		item.setChildren(catalogDao.selectList(e));		if (item.getChildren() != null && item.getChildren().size() > 0) {			for (int i = 0; i < item.getChildren().size(); i++) {				loadChildrenByPid(item.getChildren().get(i));			}		}	}		/**	 * 加载指定节点下的全部子节点	 * 	 * @param item	 */	private void loadChildrenByPid(List<String> ids,String id) {		Catalog e = new Catalog();		e.setPid(id);		List<Catalog> items = catalogDao.selectList(e);		if(items==null || items.size()==0){			ids.add(id);			return;		}				for (int i = 0; i < items.size(); i++) {			Catalog ee = items.get(i);			ids.add(ee.getId());			loadChildrenByPid(ids,ee.getId());		}	}	@Override	public boolean deleteByID(String id) {		List<String> ids = new LinkedList<String>();		ids.add(id);		loadChildrenByPid(ids,id);		catalogDao.deletes(ids);		return true;	}}