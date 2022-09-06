package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Emp;
import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 직급명, 급여를 입력 받아
		// 해당 직급에서 입력 받은 급여보다 많이 받는 사원의
		// 이름, 직급명, 급여, 연봉 출력
		
		// 단, 조회 결과가 없으면 " 조회 결과 없음" 출력
		// 조회 결과가 있으면
		// 선동일 / 대표 / 8000000 / 96000000
		// 송종기 / 부장 / 6000000 / 72000000
		// .....
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			System.out.print("직급명 입력 : ");
			String input = sc.nextLine();
			System.out.print("급여 입력 : ");
			int num = sc.nextInt();
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 객체

			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":49679"; 
			String sid = ":XE"; 
			String user = "kh_khd";
			String pw = "Myodong312";
			
			conn = DriverManager.getConnection(type+ ip + port + sid, user, pw);
									// jdbc:oracle:thin:@localhost:49679:XE == URL
			
			
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, SALARY*12 ANNUAL_INCOMEI"
					+ " FROM EMPLOYEE"
					+ " JOIN JOB  USING(JOB_CODE)"
					+ " WHERE JOB_NAME = '"+input+"'"
					+ " AND SALARY > '"+num+"'";
			
			
			stmt = conn.createStatement(); //Statement 생성
			rs = stmt.executeQuery(sql);
			
			
			List<Employee> list = new ArrayList<>();
			
			while (rs.next()) { // 한줄한줄 조회
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				int annualIncome =rs.getInt("ANNUAL_INCOMEI");
				
				

				list.add(new Employee(empName, jobName, salary, annualIncome));
			}
			
			if (list.isEmpty()) { 
				System.out.println("조회 결과가 없습니다.");
			} else {
				for (Employee employee : list) {
					System.out.println(employee);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
		}
		
	}

}
