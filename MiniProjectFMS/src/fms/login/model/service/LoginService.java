package fms.login.model.service;



import static fms.common.JDBCTemplate.*;


import java.sql.Connection;

import fms.login.model.dao.LoginDAO;
import fms.login.model.vo.LoginVo;
import fms.login.view.LoginView;

public class LoginService {

	private LoginDAO dao = new LoginDAO();
	
	
	

	/** 아이디 중복 검사 서비스
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String memberId) throws Exception {
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.idDupCheck(conn, memberId);
		
		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);
		
		// 4. 조회 결과 반환
		return result;
	}




	/** 주민번호 14자리 검사 서비스
	 * @param memberSsn
	 * @return result
	 * @throws Exception
	 */
	public int ssnCheck(String memberSsn) throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.ssnCheck(conn, memberSsn);
		
		// 3. Connection 반환 (SELECT 구문은 트랜잭션 제어 필요 X)
		close(conn);
		
		// 4. 조회 결과 반환
		return result;
	}




	/** 회원가입 서비스
	 * @param loginvo
	 * @return result
	 * @throws Exception
	 */
	public int signUp(LoginVo loginvo)throws Exception {
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.signUp(conn, loginvo);
		
		// 3. 트랜잭션 제어 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// 4. Connection 반환
		close(conn);
		
		// 5. 삽입 결과 반환
		return result;
	}




	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public LoginVo login(String memberId, String memberPw)throws Exception {
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		// 2. DAO 메서드 호출 후 결과 반환 받기
		LoginVo loginMember = dao.login(conn, memberId, memberPw);
		
		// 3. 커넥션 반환
		close(conn);
		
		// 4. 조회 결과 반환
		return loginMember;
	}
	
	
	
}
