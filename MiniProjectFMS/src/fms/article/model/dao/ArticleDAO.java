package fms.article.model.dao;

import static fms.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fms.article.model.vo.ArticleVo;
import fms.client.model.vo.ClientVo;

public class ArticleDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	// 기본생성자
	public ArticleDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("article-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}


	/** 물품 등록 DAO
	 * @param conn
	 * @param articlevo
	 * @return
	 * @throws Exception
	 */
	public int registration(Connection conn, ArticleVo articlevo)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("registration");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, articlevo.getArticleName());
			pstmt.setInt(2, articlevo.getArticleQuantity());
			pstmt.setString(3, articlevo.getArticleUnit());
			pstmt.setString(4, articlevo.getArticleSl());
			pstmt.setString(5, articlevo.getArticleDom());
			pstmt.setInt(6, articlevo.getMemberNo()); //회원번호 담기 


			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}

	
	
	
	

	/** 물품 삭제 DAO
	 * @param conn
	 * @param articleNo
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection conn, int articleNo)throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("delete");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, articleNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}


	public List<ArticleVo> stockAll(Connection conn) throws Exception {
		// 결과 저장용 변수 선언
		List<ArticleVo> articletList = new ArrayList<>();

		try {
			// SQL 얻어오기
			String sql = prop.getProperty("stockAll");

			// Statement 객체 생성
			stmt = conn.createStatement();

			// SQL(SELECT) 수행 후 결과 (ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);

			// 반복문(while)을 이용해서 조회 결과의 각 행에 접근 후
			// 컬럼 값을 얻어와 ArticleVo 객체에 저장 후 List에 추가
			while (rs.next()) {

				int articleNo = rs.getInt("ARTICLE_NO");
				String articleName = rs.getString("ARTICLE_NM");
				int articleQuantity = rs.getInt("ARTICLE_QUANTITY");
				String articleUnit = rs.getString("ARTICLE_UNIT");
				String articleSl = rs.getString("ARTICLE_SL");
				String articleDom = rs.getString("ARTICLE_DOM");
				String articleRd = rs.getString("ARTICLE_RD");
				String memberName = rs.getString("MEMBER_NM");
				String memberStore = rs.getString("MEMBER_STORE");

				ArticleVo articlet = new ArticleVo(articleNo,articleName,articleQuantity,articleUnit,
						articleSl,articleDom,articleRd,memberName,memberStore);

				articletList.add(articlet);
			}

		} finally {
			// JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}

		// 조회 결과를 옮겨 담은 List 반환
		return articletList;
	}


	/** 물품 정보 수정 DAO(물품번호 검사)
	 * @param conn
	 * @param articleNo
	 * @return
	 * @throws Exception
	 */
	public int noCheck(Connection conn, int articleNo)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("noCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNo);
			
			rs = pstmt.executeQuery();
			
	
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;	}


	/** 물품 정보 수정 DAO(물품 명칭)
	 * @param conn
	 * @param articleVo
	 * @return result
	 * @throws Exception
	 */
	public int updatName(Connection conn, ArticleVo articleVo)throws Exception {
		// 결과 저장용 변수 생성
		int result = 0; // UPDATE 반영 결과 행의 개수(정수형)를 저장하기 위한 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updatName");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setString(1, articleVo.getArticleName());
			pstmt.setInt(2, articleVo.getArticleNo());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// JDBC 객체 자원 반환
			close(pstmt);
		}
		
		
		// 수정 결과 반환
		return result;
	}


	/** 물품 정보 수정 DAO (물품 수량,단위)
	 * @param conn
	 * @param articleVo
	 * @return
	 * @throws Exception
	 */
	public int updatQuantity(Connection conn, ArticleVo articleVo)throws Exception {
		// 결과 저장용 변수 생성
		int result = 0; // UPDATE 반영 결과 행의 개수(정수형)를 저장하기 위한 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updatQuantity");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setInt(1, articleVo.getArticleQuantity());
			pstmt.setString(2, articleVo.getArticleUnit());
			pstmt.setInt(3, articleVo.getArticleNo());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// JDBC 객체 자원 반환
			close(pstmt);
		}
		
		
		// 수정 결과 반환
		return result;
	}

	
	/** 물품 정보 수정 DAO (물품 유통기한)
	 * @param conn
	 * @param articleVo
	 * @return result
	 * @throws Exception
	 */
	public int updatSl(Connection conn, ArticleVo articleVo)throws Exception {
		int result = 0; 
		
		try {
			String sql = prop.getProperty("updatSl");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, articleVo.getArticleSl());
			pstmt.setInt(2, articleVo.getArticleNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 물품 정보 수정 DAO (물품 제조일자)
	 * @param conn
	 * @param articleVo
	 * @return
	 * @throws Exception
	 */
	public int updatDom(Connection conn, ArticleVo articleVo)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("updatDom");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,articleVo.getArticleDom());
			pstmt.setInt(2,articleVo.getArticleNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 물품 제공등록 (등록 매장 확인)
	 * @param conn
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int storeCheck(Connection conn, int articleNo, int memberNo)throws Exception {
		int result2 = 0;
		
		try {
			String sql = prop.getProperty("storeCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, articleNo);
			pstmt.setInt(2, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result2 = rs.getInt(1); 
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return result2;
	}


	/** 물품 제공등록 DAO (재고 수량 감소)
	 * @param conn
	 * @param articleVo
	 * @return
	 * @throws Exception
	 */
	public int offeredRegistration(Connection conn, ArticleVo articleVo)throws Exception {
		int result3 = 0;
		try {
			String sql = prop.getProperty("offeredRegistration");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,articleVo.getArticleQuantity()); // 제공수량 감소
			pstmt.setInt(2,articleVo.getArticleNo());		// 물품번호
			
			result3 = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		return result3;
	}


	/** 물품 제공등록 DAO (고객 수량 증가)
	 * @param conn
	 * @param articleVo
	 * @return
	 * @throws Exception
	 */
	public int clientOffered(Connection conn, ArticleVo articleVo)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("clientOffered");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,articleVo.getArticleQuantity()); // 제공수량 증가
			pstmt.setInt(2,articleVo.getArticleNo());		// 물품번호
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;

	}


	/** 고객 번호 확인
	 * @param conn
	 * @param clientNo
	 * @return client
	 * @throws Exception
	 */
	public int cnoCheck(Connection conn, int clientNo)throws Exception {
		int client = 0;
		
		try {
			String sql = prop.getProperty("cnoCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, clientNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				client = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		return client;
	}


	/** 물품 제공 현황 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<ArticleVo> offeredselectAll(Connection conn)throws Exception {
		List<ArticleVo> articleList = new ArrayList<>();

		try {
			String sql = prop.getProperty("offeredselectAll");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				int articleNo = rs.getInt("ARTICLE_NO"); 					//물품 번호
				String articleName = rs.getString("ARTICLE_NM");			//물품 명칭
				int articleOffer = rs.getInt("ARTICLE_OFFER");				//물품제공 수량
				String articleUnit = rs.getString("ARTICLE_UNIT");			//물품 단위
				String articleSl = rs.getString("ARTICLE_SL");				//유통기한
				String articleDom = rs.getString("ARTICLE_DOM");			//제조일자
				String articleDd = rs.getString("ARTICLE_DD");				//제공일자
				String memberStore = rs.getString("MEMBER_STORE");			//제공 매장

				ArticleVo article = new ArticleVo(articleNo,articleName,articleUnit,articleSl,articleDom,articleDd,
						articleOffer,memberStore);

				articleList.add(article);
			}

		} finally {
			close(rs);
			close(stmt);
		}

		return articleList;
	}


	/** 물품 제공등록 (제공일자 등록)
	 * @param conn
	 * @param articleVo
	 * @return
	 * @throws Exception
	 */
	public int OfferedDd(Connection conn, ArticleVo articleVo)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("OfferedDd");
			
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setInt(1,articleVo.getArticleNo());		// 물품번호
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	
	
	
	
	
}
