package fms.client.model.service;

import static fms.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.InputMismatchException;

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
	
	
	
	
	
	
	
	
}
