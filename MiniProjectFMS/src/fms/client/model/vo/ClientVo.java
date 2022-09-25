package fms.client.model.vo;

public class ClientVo {
	
	private int clientNo;			// 번호
	private String clientName;		// 이름
	private String clientSsn;		// 주민번호
	private String clientPho;		// 전화번호
	private String clientAddress;	// 주소
	private String enrollDate;		// 회원가입일
	
	
	// 기본 생성자
	public ClientVo() {	}


	
	
	public ClientVo(String clientName, String clientSsn, String clientPho, String clientAddress) {
		super();
		this.clientName = clientName;
		this.clientSsn = clientSsn;
		this.clientPho = clientPho;
		this.clientAddress = clientAddress;
	}




	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientSsn() {
		return clientSsn;
	}
	public void setClientSsn(String clientSsn) {
		this.clientSsn = clientSsn;
	}
	public String getClientPho() {
		return clientPho;
	}
	public void setClientPho(String clientPho) {
		this.clientPho = clientPho;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	
	
	
}
