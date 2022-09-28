package fms.client.model.vo;

public class ClientVo {
	
	private int clientNo;			// 고객 번호
	private String clientName;		// 고객 이름
	private String clientSsn;		// 고객 주민번호
	private String clientPho;		// 고객 전화번호
	private String clientAddress;	// 고객 주소
	private String clientDate;		// 고객 회원가입일
	private String clientFl;		// 고객 삭제여부
	
	
	// 기본 생성자
	public ClientVo() {	}


	
	





	public ClientVo(String clientName, String clientSsn, String clientPho, String clientAddress) {
		super();
		this.clientName = clientName;
		this.clientSsn = clientSsn;
		this.clientPho = clientPho;
		this.clientAddress = clientAddress;
	}

	public ClientVo(int clientNo, String clientName, String clientSsn, String clientPho, String clientAddress,
			String clientDate, String clientFl) {
		super();
		this.clientNo = clientNo;
		this.clientName = clientName;
		this.clientSsn = clientSsn;
		this.clientPho = clientPho;
		this.clientAddress = clientAddress;
		this.clientDate = clientDate;
		this.clientFl = clientFl;
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
	public String getClientDate() {
		return clientDate;
	}
	public void setClientDate(String clientDate) {
		this.clientDate = clientDate;
	}
	public String getClientFl() {
		return clientFl;
	}
	public void setClientFl(String clientFl) {
		this.clientFl = clientFl;
	}



	
	
	
}
