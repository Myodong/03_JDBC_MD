package fms.client.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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




	/** 고객 삭제 DAO
	 * @param conn
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int secession(Connection conn, int memberNo) throws Exception {
		int result = 0;

		try {
			String sql = prop.getProperty("secession");

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, memberNo);

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}




	/** 고객 목록 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<ClientVo> selectAll(Connection conn)throws Exception {
		// 결과 저장용 변수 선언
		List<ClientVo> clientList = new ArrayList<>();
		
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("selectAll");

			// Statement 객체 생성
			stmt = conn.createStatement();

			// SQL(SELECT) 수행 후 결과 (ResultSet) 반환 받기
			rs = stmt.executeQuery(sql);

			// 반복문(while)을 이용해서 조회 결과의 각 행에 접근 후 
			// 컬럼 값을 얻어와 Member 객체에 저장 후  List에 추가
			while (rs.next()) {

				
				int clientNo =rs.getInt("CLIENT_NO");
				String clientName =rs.getString("CLIENT_NM");
				String clientSsn =rs.getString("CLIENT_SSN");
				String clientPho =rs.getString("CLIENT_PHO");
				String clientAddress =rs.getString("CLIENT_ADDRESS");
				String clientDate =rs.getString("ENROLL_DATE");
				String clientFl =rs.getString("CLIENT_FL");
				
				
				
				ClientVo client = new ClientVo(clientNo,clientName,clientSsn,clientPho,clientAddress,clientDate,clientFl);
		
				clientList.add(client);

		

				
			}
			
			
		} finally {
			// JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 조회 결과를 옮겨 담은 List 반환
		return clientList;
	}




	/** 고객정보 수정 고유번호 확인  DAO
	 * @param conn
	 * @param clientNo
	 * @return result
	 * @throws Exception
	 */
	public int noCheck(Connection conn, int clientNo)throws Exception {
		// 1. 결과 저장용 변수 
				int result = 0;
				
				try {
					// 2. SQL 얻어오기
					String sql = prop.getProperty("noCheck");
					
					// 3. PreaparedStatement 객체 생성
					pstmt = conn.prepareStatement(sql);
					
					// 4. ? 알맞은 값 세팅
					pstmt.setInt(1, clientNo);
					
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




	/** 고객 정보 이름 수정 DAO
	 * @param conn
	 * @param clientVo
	 * @return
	 * @throws Exception
	 */
	public int updatName(Connection conn, ClientVo clientVo)throws Exception {
		// 결과 저장용 변수 생성
				int result = 0; // UPDATE 반영 결과 행의 개수(정수형)를 저장하기 위한 변수
				
				try {
					// SQL 얻어오기
					String sql = prop.getProperty("updatName");
					
					// PreparedStatement 객체 생성
					pstmt = conn.prepareStatement(sql);
					
					// ? 알맞은 값 대입
					pstmt.setString(1, clientVo.getClientName());
					pstmt.setInt(2, clientVo.getClientNo());
					
					// SQL 수행 후 결과 반환 받기
					result = pstmt.executeUpdate();
					
				} finally {
					// JDBC 객체 자원 반환
					close(pstmt);
				}
				
				
				// 수정 결과 반환
				return result;
	}




	/** 고객 정보 전화번호 수정 DAO
	 * @param conn
	 * @param clientVo
	 * @return result
	 * @throws Exception
	 */
	public int updatPho(Connection conn, ClientVo clientVo) throws Exception {
		// 결과 저장용 변수 생성
		int result = 0; // UPDATE 반영 결과 행의 개수(정수형)를 저장하기 위한 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updatPho");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setString(1, clientVo.getClientPho());
			pstmt.setInt(2, clientVo.getClientNo());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// JDBC 객체 자원 반환
			close(pstmt);
		}
		// 수정 결과 반환
		return result;
	}




	public int updatAddress(Connection conn, ClientVo clientVo)throws Exception {
		// 결과 저장용 변수 생성
		int result = 0; // UPDATE 반영 결과 행의 개수(정수형)를 저장하기 위한 변수
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("updatAddress");
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// ? 알맞은 값 대입
			pstmt.setString(1, clientVo.getClientAddress());
			pstmt.setInt(2, clientVo.getClientNo());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		} finally {
			// JDBC 객체 자원 반환
			close(pstmt);
		}
		// 수정 결과 반환
		return result;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
