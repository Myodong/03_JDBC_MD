package fms.client.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Properties;

import fms.client.model.vo.ClientVo;

import static fms.common.JDBCTemplate.*;

public class ClientDAO {

	//필드 (== 멤버 변수)
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	// 기본 생성자
	public ClientDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("client-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	
	
	/** 주민번호 중복 검사 DAO
	 * @param conn
	 * @param clientSsn
	 * @return result
	 * @throws Exception
	 */
	public int ssnCheck(Connection conn, String clientSsn)throws Exception {
		// 1. 결과 저장용 변수 
		int result = 0;
		
		try {
			// 2. SQL 얻어오기
			String sql = prop.getProperty("ssnCheck");
			
			// 3. PreaparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ? 알맞은 값 세팅
			pstmt.setString(1, clientSsn);
			
			// 5. SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과 옮겨 담기
			// 1행 조회 -> if
			// N행 조회 -> while
			if(rs.next()) {
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




	/** 고객 등록 DAO
	 * @param conn
	 * @param clientvo
	 * @return result
	 * @throws Exception
	 */
	public int registration(Connection conn, ClientVo clientvo)throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("registration");
			
			pstmt = conn.prepareStatement(sql);
			

			pstmt.setString(1, clientvo.getClientSsn());
			pstmt.setString(2, clientvo.getClientName());
			pstmt.setString(3, clientvo.getClientPho());
			pstmt.setString(4, clientvo.getClientAddress());
			
			result = pstmt.executeUpdate();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
