package maosheji.services.manage.commentType.impl;import maosheji.core.ServersManager;import maosheji.services.manage.commentType.CommentTypeService;import maosheji.services.manage.commentType.bean.CommentType;import maosheji.services.manage.commentType.dao.CommentTypeDao;public class CommentTypeServiceImpl extends ServersManager<CommentType>		implements CommentTypeService {	private CommentTypeDao commentTypeDao;	public void setCommentTypeDao(CommentTypeDao commentTypeDao) {		this.commentTypeDao = commentTypeDao;	}		@Override	public int update(CommentType e) {		commentTypeDao.updateAllN();		super.update(e);		return 1;	}}