package maosheji.services.common;import java.io.Serializable;import maosheji.core.dao.page.PagerModel;public class Reply extends PagerModel implements Serializable {	private static final long serialVersionUID = 1L;	private String id;	private int commentID;	private String content;	private String createdate;	public void clear() {		super.clear();		id = null;		commentID = 0;		content = null;		createdate = null;	}	public String getId() {		return id;	}	public void setId(String id) {		this.id = id;	}	public int getCommentID() {		return commentID;	}	public void setCommentID(int commentID) {		this.commentID = commentID;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getCreatedate() {		return createdate;	}	public void setCreatedate(String createdate) {		this.createdate = createdate;	}}