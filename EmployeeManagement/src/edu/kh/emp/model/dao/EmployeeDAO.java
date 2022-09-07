package edu.kh.emp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.emp.model.vo.Employee;

// DAO(Data Access Object, 데이터 접근 객체)
// -> 데이터 베이스에 접근(연결)하는 객체
// -> JDBC 코드 작성
public class EmployeeDAO {

	//JDBC 객체 참조 변수로 필드 선언(class 내부에서 공통 사용)
		private Connection conn;      // 필드 (Heap, 변수가 비어있을 수 없음)
		private Statement stmt; 	    // -> JVM이 지정한 기본값으로 초기
		private ResultSet rs = null;	// -> 참조형의 초기값은 null;
										// -> 별도 초기화 안해도 된다!
		
		private PreparedStatement pstmt;
		// Statement의 자식으로 향상된 기능을 제공
		// -> ?기호 (placeholder/ 위치홀더)를 이용해서
		// 	  SQL에 작성되어지는 리터럴을 동적으로 제어함
		
		// SQL ?기호에 추가되는 값은
		// 숫자인 경우 '' 없이 대입
		// 문자열인 경우 ''가 자동으로 추가되어 대입
		
		
		
		
		
		private String driver = "oracle.jdbc.driver.OracleDriver";
		
		private String url = "jdbc:oracle:thin:@localhost:49679:XE";
		private String user = "kh_khd";
		private String pw = "Myodong312";
				
				
//		public void method() {
//			Connection conn2; //지역변수 (Stack, 변수가 비어 있을 수 있음)
//		}


		/** 전체 사원 정보 조회 DAO
		 * @return empList
		 */
		public List<Employee> selectAll() {
			// 1. 결과 저장용 변수 선언
			List<Employee> empList = new ArrayList<>();
			
			try {
				
			// 2. JDBC 참조 변수에 객체 대입
			// -> conn, stmt, rs에 객체 대입
				
				Class.forName(driver); // 오라클 jdbc 드라이버 객체 메모리 로드
				conn = DriverManager.getConnection(url,user,pw);
				// 오라클 jdbc 드라이버 객체를 이용하여 DB 접속 방법 생성
				
				String sql = "SELECT EMP_ID , EMP_NAME , EMP_NO , EMAIL, PHONE , \r\n"
						+ "       NVL(DEPT_TITLE, '부서없음')DEPT_TITLE , JOB_NAME , SALARY  \r\n"
						+ "FROM EMPLOYEE \r\n"
						+ "LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)\r\n"
						+ "JOIN JOB USING (JOB_CODE)";
				
				// Statement 객체 생성
				stmt = conn.createStatement();
				
				// SQL을 수행 후 결과(ResultSet) 반환 받음
				rs = stmt.executeQuery(sql);
				
				
			// 3. 조회 결과를 얻어와 한 행씩 접근하여 
			//	  Employee 객체 생성 후 컬럼 값 옮겨 담기	
			//	  -> List 추가
				while (rs.next()) {
					
					int empId = rs.getInt("EMP_ID");
					// EMP_ID 컬럼은 문자열 컬럼이지만
					// 저장된 값들이 모두 숫자형태
					// -> DB에서 자동으로 형변환 진행해서 얻어옴
					String empName = rs.getNString("EMP_NAME");
					String empNo = rs.getNString("EMP_NO");
					String email = rs.getNString("EMAIL");
					String phone = rs.getNString("PHONE");
					String departmentTitle = rs.getNString("DEPT_TITLE");
					String jobName = rs.getNString("JOB_NAME");
					int salary = rs.getInt("SALARY");

					Employee emp = new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary);
					
					empList.add(emp); // List 담기
					
				} // while 종료
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			// 4. JDBC 객체 자원 반환	
				
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			

			// 5. 결과 반환
			return empList;
		}


		/** 사번이 일치하는 사원 정보 조회 DAO
		 * @param empId
		 * @return emp
		 */
		public Employee selectEmpId(int empId) {
			
			// 결과 저장용 변수를 선언
			Employee emp =null;
			// 조회결과가 있으면 Employee 객체를 생성해서 emp에 대입 (null이 아님
			// 조회결과가 없으면 emp에 아무것도 대입하지 않음 (null)
			
			try {
				Class.forName(driver); // 오라클 JDBC 드라이버 메모리 로드
				conn= DriverManager.getConnection(url, user, pw); // 커넥션 생성해서 얻어오기
				
				// SQL 생성
				String sql = "SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, \r\n"
						+ "      NVL(DEPT_TITLE, '부서없음') DEPT_TITLE, \r\n"
						+ "      JOB_NAME, SALARY\r\n"
						+ "FROM EMPLOYEE\r\n"
						+ "LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)\r\n"
						+ "JOIN JOB USING(JOB_CODE)\r\n"
						+ "WHERE EMP_ID  = " + empId;
												// View에서 입력받은 사번 
				
		
				// Statement 생성
				stmt = conn.createStatement();
				
				// SQL 수행 후 결과 반환 받기
				rs = stmt.executeQuery(sql);
				
				// ** 조회 결과가 최대 1행인 경우
				//    불필요한 조건 검사를 줄이기 위해 if문 사용 권장 **
				if(rs.next()) { 
					
//					int empId = rs.getInt("EMP_ID"); // -> 전달 받은 파라미터와 같은 값이므로 불필요
					String empName = rs.getNString("EMP_NAME");
					String empNo = rs.getNString("EMP_NO");
					String email = rs.getNString("EMAIL");
					String phone = rs.getNString("PHONE");
					String departmentTitle = rs.getNString("DEPT_TITLE");
					String jobName = rs.getNString("JOB_NAME");
					int salary = rs.getInt("SALARY");
					
					// 조회 결과를 담은 Employee 객체 생성후
					// 결과 저장용 변수 emp에 대입
					emp = new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary);
					
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(conn != null) conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//결과 반환
			return emp;
		}


		/** 주민번호가 일치하는 사원 정보 조회 DAO
		 * @param empNo
		 * @return emp
		 */
		public Employee seletEmpNo(String empNo) {
			
			// 결과 저장용 변수 선언
			Employee emp =null;
			
			try {
				// Connection 생성
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user,pw);
				
				// SQL작성 
				String sql = "SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, \r\n"
						+ "      NVL(DEPT_TITLE, '부서없음') DEPT_TITLE, \r\n"
						+ "      JOB_NAME, SALARY\r\n"
						+ "FROM EMPLOYEE\r\n"
						+ "LEFT JOIN DEPARTMENT ON (DEPT_ID = DEPT_CODE)\r\n"
						+ "JOIN JOB USING(JOB_CODE)\r\n"
						+ "WHERE EMP_NO  = ?";
										// placehoder
				
				// Statement 객체 사용 시 순서
				// SQL 작성 -> Statement 생성 -> SQL 수행 후 결과 반환
				
				// PreparedStatement 객체 사용 시 순서
				// SQL 작성
				// -> PreparedStatement 객체 생성(?가 포함됨 SQL을 매개변수로 사용)
				// -> ?에 알맞은 값 대입
				// -> SQL 수행 후 결과 반환
				
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				// ?에 알맞은 값 대입
				pstmt.setString(1, empNo);
				
				// SQL 수행 후 결과 반환
				rs = pstmt.executeQuery();
				// preparedStatement는
				// 객체 생성 시 이미 SQL이 담겨져 있는 상태이므로
				// SQL 수행 시 매개변수로 전달할 필요가 없다!
				
				// -> 실수로 SQL을 매개변수에 추가하면
				// ?에 작성 추가했던 값이 모두 사라져 수행 시 오류 발생
				
				
				
				if(rs.next()) { 
					
					int empId = rs.getInt("EMP_ID"); 
					String empName = rs.getNString("EMP_NAME");
//					String empNo = rs.getNString("EMP_NO");// -> 전달 받은 파라미터와 같은 값이므로 불필요
					String email = rs.getNString("EMAIL");
					String phone = rs.getNString("PHONE");
					String departmentTitle = rs.getNString("DEPT_TITLE");
					String jobName = rs.getNString("JOB_NAME");
					int salary = rs.getInt("SALARY");
					
					// 조회 결과를 담은 Employee 객체 생성후
					// 결과 저장용 변수 emp에 대입
					emp = new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary);
					
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			// 결과 반환
			return emp;
		}


		/** 사원 정보 추가 DAO
		 * @param emp
		 * @return result(INSERT 성공한 행의 개수 반환)
		 */
		public int insertEmployee(Employee emp) {
			
			// 결과 저장용 변수 선언
			int result = 0;
			
			try {
				//커넥션 생성
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user,pw);
				
				// ** DML 수행할 예정
				// - 트랜잭션에 DML 구문이 임시 저장
				// -> 정상적인 DML인지를 판별해서 개발자가 직접 commit, rollback을 수행
				
				// 하지만....... connection객체 생성 시
				// AutoCommit이 활성화 되어있는 상태이기 때문에
				// 이름 해제하는 코드를 추가!!!!!!!!!!!!!!!!!!
				
				conn.setAutoCommit(false); // AutoCommit 비활성화
				// -> AutoCommit 비활성화해도
				//	 conn.close()구문 수행되면 자동으로 commit이 수행됨
				// --> close() 수행 전에 트랜잭션 제어 코드를 작성해야함!
				
				
				// SQL 작성
				
				String sql = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?,?,SYSDATE,NULL,DEFAULT)";
				// 퇴사여부 컬럼의 DEFAULT == 'N'
				
				//PreparedStatement 객체 생성 (매개변수에 SQL 추가)
				pstmt = conn.prepareStatement(sql);
				
				// ?(placeholder)에 알맞은 값 대임
				pstmt.setInt(1, emp.getEmpId());
		         pstmt.setString(2, emp.getEmpName());
		         pstmt.setString(3, emp.getEmpNo());
		         pstmt.setString(4, emp.getEmail());
		         pstmt.setString(5, emp.getPhone());
		         pstmt.setString(6, emp.getDeptCode());
		         pstmt.setString(7, emp.getJobCode());
		         pstmt.setString(8, emp.getSalLevel());
		         pstmt.setInt(9, emp.getSalary());
		         pstmt.setDouble(10, emp.getBonus());
		         pstmt.setInt(11, emp.getManagerId());
				
				// SQL 수행 후 결과 반환 받기
				result = pstmt.executeUpdate();
				
				
				
				// executeUpdate() : DML(INSERT, UPDATE, DELETE)수행 후 결과 행 개수 반환
				// executeQuery() : SELECT 수행 후 ResultSet 반환
				
				//*** 트랜잭션 제어 처리 ***
				// -> DMK 성공 여부에 따라 commit, rollback 제어
				if (result > 0) conn.commit();   // DML 성공시 commit
				else			conn.rollback(); // DML 실패시 rollback
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
			
			
			// 결과 반환
			return result;
		}


		/** 사번이 일치하는 사원 정보 수정 DAO
		 * @param emp
		 * @return result
		 */
		public int updateEmployee(Employee emp) {

			int result = 0; // 결과 저장용 변수 
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, pw);
				conn.setAutoCommit(false); //AutoCommit 비활성화
				
				
				String sql = "UPDATE EMPLOYEE SET"
						+ " EMAIL = ?, PHONE = ?, SALARY=? "
						+ " WHERE EMP_ID = ?";		
						
				// PreparedStatement 생성
				pstmt = conn.prepareStatement(sql);
				
				// ?에 알맞은 값을 세팅
				pstmt.setString(1, emp.getEmail());
				pstmt.setString(2, emp.getPhone());
				pstmt.setInt(3, emp.getSalary());
				pstmt.setInt(4, emp.getEmpId());
				
				result = pstmt.executeUpdate(); // 반영된 행의 개수 반환
				
				// 트랜잭션 제어 처리
				if (result == 0) conn.rollback();
				else			 conn.commit();
						
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				try {
					//JDBC 객체 자원 반환
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			return result; //결과 반환
		}


		public int deleteEmployee(int empId) {

			int result = 0; // 결과 저장용 변수 
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, pw);
				conn.setAutoCommit(false); 
				
				String sql = "DELETE FROM EMPLOYEE"
						+ " WHERE EMP_ID = ?";		
			
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, empId);
				result = pstmt.executeUpdate(); 
				
				if (result == 0) conn.rollback();
				else			 conn.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			
			} finally {
				
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return result;
		}


	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
