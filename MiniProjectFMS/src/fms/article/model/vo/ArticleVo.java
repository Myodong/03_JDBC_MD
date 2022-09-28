package fms.article.model.vo;


public class ArticleVo {
	
	private int 	articleNo;			// 물품 번호
	private String 	articleName;		// 물품 명칭
	private int 	articleQuantity;	// 수량
	private String 	articleUnit;		// 단위
	private String 	articleSl;			// 유통기한
	private String 	articleDom;			// 제조일자
	private String 	articleRd;			// 등록일자
	private String 	articleDd;			// 제공일자
	private int 	articleOffer;		// 제공수량

	
	private int 	memberNo;			// 회원번호
	private String 	memberName;			// 회원 이름
	private String 	memberStore;		// 담당매장
	
	private int		clientNo;			// 고객 번호
	private String  clientName;			// 고객 이름
	
	// 기본 생성자 
	public ArticleVo() {	}




	public ArticleVo(String articleName, int articleQuantity, String articleUnit, String articleSl, String articleDom, int memberNo) {
		super();
		this.articleName = articleName;
		this.articleQuantity = articleQuantity;
		this.articleUnit = articleUnit;
		this.articleSl = articleSl;
		this.articleDom = articleDom;
		this.memberNo = memberNo;
	}







	public ArticleVo(int articleNo, String articleName, int articleQuantity, String articleUnit, String articleSl,
			String articleDom, String articleRd, String memberName, String memberStore) {
		super();
		this.articleNo = articleNo;
		this.articleName = articleName;
		this.articleQuantity = articleQuantity;
		this.articleUnit = articleUnit;
		this.articleSl = articleSl;
		this.articleDom = articleDom;
		this.articleRd = articleRd;
		this.memberName = memberName;
		this.memberStore = memberStore;
	}




	public ArticleVo(int articleNo, String articleName, String articleUnit, String articleSl, String articleDom,
			String articleDd, int articleOffer, String memberStore) {
		super();
		this.articleNo = articleNo;
		this.articleName = articleName;
		this.articleUnit = articleUnit;
		this.articleSl = articleSl;
		this.articleDom = articleDom;
		this.articleDd = articleDd;
		this.articleOffer = articleOffer;
		this.memberStore = memberStore;
	}









	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public int getArticleQuantity() {
		return articleQuantity;
	}
	public void setArticleQuantity(int articleQuantity) {
		this.articleQuantity = articleQuantity;
	}
	public String getArticleUnit() {
		return articleUnit;
	}
	public void setArticleUnit(String articleUnit) {
		this.articleUnit = articleUnit;
	}
	public String getArticleSl() {
		return articleSl;
	}
	public void setArticleSl(String articleSl) {
		this.articleSl = articleSl;
	}
	public String getArticleDom() {
		return articleDom;
	}
	public void setArticleDom(String articleDom) {
		this.articleDom = articleDom;
	}
	public String getArticleRd() {
		return articleRd;
	}
	public void setArticleRd(String articleRd) {
		this.articleRd = articleRd;
	}
	public String getArticleDd() {
		return articleDd;
	}
	public void setArticleDd(String articleDd) {
		this.articleDd = articleDd;
	}
	public int getArticleOffer() {
		return articleOffer;
	}
	public void setArticleOffer(int articleOffer) {
		this.articleOffer = articleOffer;
	}




	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberStore() {
		return memberStore;
	}
	public void setMemberStore(String memberStore) {
		this.memberStore = memberStore;
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






	
	
	
	
	
	
	
	
}
