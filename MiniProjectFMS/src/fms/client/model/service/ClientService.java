package fms.client.model.service;





import static fms.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import fms.client.model.dao.ClientDAO;
import fms.client.model.vo.ClientVo;

public class ClientService {
	
	private ClientDAO dao = new ClientDAO();

	

	/** 주민번호 중복 검사 서비스
	 * @param clientSsn
	 * @return result
	 * @throws Exception
	 */
	public int ssnCheck(String clientSsn)throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.ssnCheck(conn, clientSsn);
		
		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);
		
		// 4. 조회 결과 반환
		return result;
	}



	/** 고객 등록 서비스
	 * @param clientvo
	 * @return
	 * @throws Exception
	 */
	public int registration(ClientVo clientvo)throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.registration(conn, clientvo);
		
		// 3. 트랜잭션 제어 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// 4. Connection 반환
		close(conn);
		
		// 5. 삽입 결과 반환
		return result;
	}




	/** 고객 삭제 (FL 컬럼 값 Y로변경)
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(int memberNo)throws Exception {

		Connection conn = getConnection();
		
		int result = dao.secession(conn,memberNo);
		
		if(result > 0 ) commit(conn);
		else			rollback(conn);
		
		close(conn);


		return result;
	}



	/** 고객 목록 조회 서비스
	 * @return
	 * @throws Exception
	 */
	public List<ClientVo> selectAll()throws Exception {
		Connection conn = getConnection(); // 커넥션 생성
		
		// DAO 메서드 호출 후 결과 반환 받기
		List<ClientVo> clientList = dao.selectAll(conn);
		
		close(conn); // 커넥션 반환
		
		return clientList; // 조회 결과 반환
	}



	/** 고객정보 수정 고유번호 확인  서비스
	 * @param clientNo
	 * @return result
	 * @throws Exception
	 */
	public int noCheck(int clientNo)throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.noCheck(conn, clientNo);
		
		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);
		
		// 4. 조회 결과 반환
		return result;

	}



	/** 고객 정보 이름 수정 서비스
	 * @param clientVo
	 * @return result
	 * @throws Exception
	 */
	public int updatName(ClientVo clientVo)throws Exception {
		// 커넥션 생성
		Connection conn = getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받고
		int result = dao.updatName(conn,clientVo);
		
		// 트랜잭션 제어 처리
		if(result > 0)  commit(conn);
		else			rollback(conn);
		
		// 커넥션 반환
		close(conn);
		
		// 수정 결과 반환
		return result;
	}




	/** 고객 정보 전화번호 수정 서비스
	 * @param clientVo
	 * @return result
	 * @throws Exception
	 */
	public int updatPho(ClientVo clientVo)throws Exception {
		// 커넥션 생성
		Connection conn = getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받고
		int result = dao.updatPho(conn,clientVo);
		
		// 트랜잭션 제어 처리
		if(result > 0)  commit(conn);
		else			rollback(conn);
		
		// 커넥션 반환
		close(conn);
		
		// 수정 결과 반환
		return result;
	}



	/** 고객 정보 주소 수정 서비스
	 * @param clientVo
	 * @return
	 * @throws Exception
	 */
	public int updatAddress(ClientVo clientVo)throws Exception {
		// 커넥션 생성 
		Connection conn = getConnection();
		
		// DAO 메서드 호출 후 결과 반환
		int result = dao.updatAddress(conn, clientVo);
		
		// 트랜잭션 제어 처리
		if(result > 0)  commit(conn);
		else			rollback(conn);
		// 커넥션반환
		close(conn);
		// 수정 결과 반환
		return result;
	}
	
	
	
	
	
	
	
	
}
