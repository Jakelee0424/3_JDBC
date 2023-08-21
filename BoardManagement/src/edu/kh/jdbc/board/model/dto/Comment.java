package edu.kh.jdbc.board.model.dto;

public class Comment {

	private int commentNo;
	private String commentContent;
	private String createDate;
	private String deleteFlag;
	private int memberNo;
	
	public Comment() {}
	
	public Comment(int commentNo, String commentContent, String createDate, String deleteFlag, int memberNo) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.createDate = createDate;
		this.deleteFlag = deleteFlag;
		this.memberNo = memberNo;
	}
	

	public Comment(int commentNo, String commentContent, String createDate, int memberNo) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.createDate = createDate;
		this.memberNo = memberNo;
	}

	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	
	@Override
	public String toString() {
		return "Board [commentNo=" + commentNo + ", commentContent=" + commentContent + ", createDate=" + createDate
				+ ", deleteFlag=" + deleteFlag + ", memberNo=" + memberNo + "]";
	}
	
	
	
	

	
	
	
	
}
