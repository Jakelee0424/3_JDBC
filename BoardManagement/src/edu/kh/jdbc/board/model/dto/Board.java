package edu.kh.jdbc.board.model.dto;

public class Board {

	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String createDate;
	private int readCount;
	private String deleteFlag;
	private int memberNo;
	
	public Board() {}

	public Board(int boardNo, String boardTitle, String boardContent, String createDate, int readCount,
			String deleteFlag, int memberNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.createDate = createDate;
		this.readCount = readCount;
		this.deleteFlag = deleteFlag;
		this.memberNo = memberNo;
	}

	public Board(int boardNo, String boardTitle, String boardContent, String createDate, int memberNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.createDate = createDate;
		this.memberNo = memberNo;
	}

	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
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
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", createDate=" + createDate + ", readCount=" + readCount + ", deleteFlag=" + deleteFlag
				+ ", memberNo=" + memberNo + "]";
	}
	
	
	
}
