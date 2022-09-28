package fms.login.model.vo;

public class LoginVo {

	private int memberNo;			// 번호
	private String memberId ; 		// 아이디
	private String memberPw; 		// 비번
	private String memberName;		// 이름
	private String memberSsn;		// 주민번호
	private String memberPho;		// 전화번호
	private String memberAddress;	// 주소
	private String memberStore;		// 담당매장
	private String enrollDate;		// 회원가입일


	
	
	
	public LoginVo() {	}
	
	
	
	
	public LoginVo(String memberId, String memberPw, String memberName, String memberSsn, String memberPho,
			String memberAddress, String memberStore) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberSsn = memberSsn;
		this.memberPho = memberPho;
		this.memberAddress = memberAddress;
		this.memberStore = memberStore;
	}



	public LoginVo(int memberNo, String memberId, String memberName, String enrollDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.enrollDate = enrollDate;
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberSsn() {
		return memberSsn;
	}
	public void setMemberSsn(String memberSsn) {
		this.memberSsn = memberSsn;
	}
	public String getMemberPho() {
		return memberPho;
	}
	public void setMemberPho(String memberPho) {
		this.memberPho = memberPho;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberStore() {
		return memberStore;
	}
	public void setMemberStore(String memberStore) {
		this.memberStore = memberStore;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}



	
	
	
	
}
