package edu.kh.jdbc.member.model.dto;

public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memName;
	private String memberGender;
	private String enrollDate;
	private String unregisterFlag;
	
	public Member() {}


	public Member(String memberId, String memberPw, String memName, String memberGender) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memName = memName;
		this.memberGender = memberGender;
	}


	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getUnregisterFlag() {
		return unregisterFlag;
	}
	public void setUnregisterFlag(String unregisterFlag) {
		this.unregisterFlag = unregisterFlag;
	}
	


	
}
