package maosheji.services.manage.task.dao.impl;import java.util.List;import maosheji.core.dao.BaseDao;import maosheji.core.dao.page.PagerModel;import maosheji.services.manage.task.bean.Task;import maosheji.services.manage.task.dao.TaskDao;public class TaskDaoImpl implements TaskDao {	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public PagerModel selectPageList(Task e) {		return dao.selectPageList("manage.task.selectPageList",				"manage.task.selectPageCount", e);	}	public List selectList(Task e) {		return dao.selectList("manage.task.selectList", e);	}	public Task selectOne(Task e) {		return (Task) dao.selectOne("manage.task.selectOne", e);	}	public int delete(Task e) {		return dao.delete("manage.task.delete", e);	}	public int update(Task e) {		return dao.update("manage.task.update", e);	}	public int deletes(String[] ids) {		Task e = new Task();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}	public int insert(Task e) {		return dao.insert("manage.task.insert", e);	}	public int deleteById(int id) {		return dao.delete("manage.task.deleteById", id);	}	@Override	public Task selectById(String id) {		return (Task) dao.selectOne("manage.task.deleteById", id);	}}