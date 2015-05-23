package maosheji.services.manage.comment.dao.impl;import java.util.List;import maosheji.core.dao.BaseDao;import maosheji.core.dao.page.PagerModel;import maosheji.services.manage.catalog.bean.Catalog;import maosheji.services.manage.comment.bean.Comment;import maosheji.services.manage.comment.dao.CommentDao;public class CommentDaoImpl implements CommentDao {	private BaseDao dao;	public void setDao(BaseDao dao) {		this.dao = dao;	}	public PagerModel selectPageList(Comment e) {		return dao.selectPageList("manage.comment.selectPageList",				"manage.comment.selectPageCount", e);	}	public List selectList(Comment e) {		return dao.selectList("manage.comment.selectList", e);	}	public Comment selectOne(Comment e) {		return (Comment) dao.selectOne("manage.comment.selectOne", e);	}	public int delete(Comment e) {		return dao.delete("manage.comment.delete", e);	}	public int update(Comment e) {		return dao.update("manage.comment.update", e);	}	public int deletes(String[] ids) {		Comment e = new Comment();		for (int i = 0; i < ids.length; i++) {			e.setId(ids[i]);			delete(e);		}		return 0;	}	public int insert(Comment e) {		return dao.insert("manage.comment.insert", e);	}	public int deleteById(int id) {		return dao.delete("manage.comment.deleteById", id);	}	public Comment selectById(String id) {		return (Comment) dao.selectOne(id);	}	@Override	public int selectNotReplyCount() {		return (Integer) dao.selectOne("manage.comment.selectNotReplyCount");	}	@Override	public void updateStatus(Comment c) {		dao.update("manage.comment.updateStatus",c);	}}