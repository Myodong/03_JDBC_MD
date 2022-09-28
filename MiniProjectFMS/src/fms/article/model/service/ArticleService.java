package fms.article.model.service;

import static fms.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import fms.article.model.dao.ArticleDAO;
import fms.article.model.vo.ArticleVo;
import fms.client.model.vo.ClientVo;

public class ArticleService {
	
	private ArticleDAO dao = new ArticleDAO();

	
	
	
	/** 물품 등록 서비스
	 * @param articlevo
	 * @return
	 * @throws Exception
	 */
	public int registration(ArticleVo articlevo)throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.registration(conn, articlevo);
		
		// 3. 트랜잭션 제어 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// 4. Connection 반환
		close(conn);
		
		// 5. 삽입 결과 반환
		return result;
	}




	/** 물품 삭제 서비스
	 * @param articleNo
	 * @return result
	 * @throws Exception
	 */
	public int delete(int articleNo)throws Exception {
		Connection conn = getConnection();
		
		int result = dao.delete(conn,articleNo);
		
		if(result > 0 ) commit(conn);
		else			rollback(conn);
		
		close(conn);


		return result;
	}




	/** 물품 재고 목록 서비스
	 * @return articletList
	 * @throws Exception
	 */
	public List<ArticleVo> stockAll()throws Exception {
		Connection conn = getConnection(); // 커넥션 생성
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<ArticleVo> articletList = dao.stockAll(conn);
		
		close(conn); // 커넥션 반환
		
		return articletList; // 조회 결과 반환
	}




	/** 물품 정보 수정 서비스(물품번호 검사)
	 * @param articleNo
	 * @return result
	 * @throws Exception
	 */
	public int noCheck(int articleNo)throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.noCheck(conn, articleNo);
		
		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);
		
		// 4. 조회 결과 반환
		return result;
	}




	/** 물품 정보 수정 서비스 (물품 명칭)
	 * @param articleVo
	 * @return result
	 * @throws Exception
	 */
	public int updatName(ArticleVo articleVo)throws Exception {
		// 커넥션 생성
				Connection conn = getConnection();
				
				// DAO 메서드 호출 후 결과 반환 받고
				int result = dao.updatName(conn,articleVo);
				
				// 트랜잭션 제어 처리
				if(result > 0)  commit(conn);
				else			rollback(conn);
				
				// 커넥션 반환
				close(conn);
				
				// 수정 결과 반환
				return result;
	}




	/** 물품 정보 수정 서비스 (물품 수량,단위)
	 * @param articleVo
	 * @return result
	 * @throws Exception
	 */
	public int updatQuantity(ArticleVo articleVo)throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updatQuantity(conn,articleVo);
		
		if(result > 0)  commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}




	/** 물품 정보 수정 서비스 (물품 유통기한)
	 * @param articleVo
	 * @return result
	 * @throws Exception
	 */
	public int updatSl(ArticleVo articleVo)throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updatSl(conn,articleVo);
		
		if(result > 0)  commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}




	/** 물품 정보 수정 서비스 (물품 제조일자)
	 * @param articleVo
	 * @return result
	 * @throws Exception
	 */
	public int updatDom(ArticleVo articleVo)throws Exception {
		 Connection conn = getConnection();
		 
		 int result = dao.updatDom(conn,articleVo);
		 
		 if(result > 0) commit(conn);
		 else			rollback(conn);
		 
		 close(conn);
		 

		return result;
	}




	/** 물품 제공등록 (등록 매장 확인)
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int storeCheck(int articleNo, int memberNo)throws Exception {
		 Connection conn = getConnection();
		 
		 int result2 = dao.storeCheck(conn,articleNo,memberNo);
		 
		 if(result2 > 0) commit(conn);
		 else			rollback(conn);
		 
		 close(conn);
		 

		return result2;
	}




	/** 물품 제공등록 서비스
	 * @param articleVo
	 * @return result3
	 * @throws Exception
	 */
	public int offeredRegistration(ArticleVo articleVo) throws Exception {
		Connection conn = getConnection();

		int result3 = dao.offeredRegistration(conn, articleVo); // 재고 수량 감소

		if (result3 != 0) {
			int result2 = dao.clientOffered(conn, articleVo); // 고객 제공 수량증가

			if (result2 != 0) {
				int result = dao.OfferedDd(conn, articleVo); // 고객 제공일자

				if (result > 0)
					commit(conn);
				else
					rollback(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		close(conn);
		return result3;
	}




	/** 고객 번호 확인
	 * @param clientNo
	 * @return client
	 * @throws Exception
	 */
	public int cnoCheck(int clientNo)throws Exception {
		Connection conn = getConnection();
		
		int client = dao.cnoCheck(conn, clientNo);
		
		close(conn);
		
		return client;
	}




	/** 물품 제공 현황 서비스
	 * @return articleList
	 * @throws Exception
	 */
	public List<ArticleVo> offeredselectAll()throws Exception {
		Connection conn = getConnection();
		
		List<ArticleVo> articleList = dao.offeredselectAll(conn);
		
		close(conn); 
		
		return articleList; 
	}











	
	
	
	
	
	
	
	

}
