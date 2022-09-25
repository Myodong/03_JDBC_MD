package fms.login.model.dao;

import static fms.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import fms.login.model.vo.LoginVo;
import fms.login.view.LoginView;

public class LoginDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Properties prop = null;
	
	
	
	
	// 기본 생성자
	public LoginDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("login-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	/** 아이디 중복 검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(Connection conn, String memberId) throws Exception{

		// 1. 결과 저장용 변수 
		int result = 0;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("idDupCheck");
			
			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 알맞은 값 세팅
			pstmt.setString(1, memberId);
			
			// 5. SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과 옮겨 담기
			// 1행 조회 -> if
			// N행 조회 -> while
			if(rs.next()) {
				// result = rs.getInt("COUNT(*)"); // 컬럼명
				result = rs.getInt(1); // 컬럼 순서
			}
			
		}finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		
		// 8. 결과 반환
		return result;
	}




	public int ssnCheck(Connection conn, String memberSsn)throws Exception {
		// 1. 결과 저장용 변수 
		int result = 0;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("ssnCheck");
			
			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 알맞은 값 세팅
			pstmt.setString(1, memberSsn);
			
			// 5. SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과 옮겨 담기
			// 1행 조회 -> if
			// N행 조회 -> while
			if(rs.next()) {
				// result = rs.getInt("COUNT(*)"); // 컬럼명
				result = rs.getInt(1); // 컬럼 순서
			}
			
		}finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);
			close(pstmt);
		}
		
		// 8. 결과 반환
		return result;
	}




	/** 회원가입 DAO
	 * @param conn
	 * @param loginvo
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, LoginVo loginvo)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, loginvo.getMemberId());
			pstmt.setString(2, loginvo.getMemberPw());
			pstmt.setString(3, loginvo.getMemberName());
			pstmt.setString(4, loginvo.getMemberSsn());
			pstmt.setString(5, loginvo.getMemberPho());
			pstmt.setString(6, loginvo.getMemberAddress());
			pstmt.setString(7, loginvo.getMemberStore());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}




	public LoginVo login(Connection conn, String memberId, String memberPw)throws Exception {
		// 1. 결과 저장용 변수 선언
			LoginVo loginMember = null;
				
				try {
					// 2. SQL 얻어오기(main-query.xml 파일에 작성된 SQL)
					String sql = prop.getProperty("login");
					
					// 3. PreparedStatement 객체 생성
					pstmt = conn.prepareStatement(sql);
					
					// 4. ?에 알맞은 값 대입
					pstmt.setString(1, memberId);
					pstmt.setString(2, memberPw);
					
					// 5. SQL(SELECT) 수행 후 결과(ResultSet) 반환 받기
					rs = pstmt.executeQuery();
					
					// 6. 조회 결과가 있을 경우
					//    컬럼 값을 모두 얻어와
					//    Member 객체를 생성해서 loginMember 대입 
					if(rs.next()) {
						loginMember = new LoginVo(rs.getInt("MEMBER_NO"), 
												 memberId,
												 rs.getString("MEMBER_NM"),
												 rs.getString("ENROLL_DATE") );
					}
					
				}finally {
					// 7. 사용한 JDBC 객체 자원 반환
					close(rs);
					close(pstmt);
				}
				
				// 8. 조회 결과 반환
				return loginMember;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
