package fms.login.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import fms.login.model.service.LoginService;

public class LoginView {

	private Scanner sc = new Scanner(System.in);
	
	// 로그인서비스 객체 생성
	private LoginService service = new LoginService();
	
	// 로그인된 회원 정보를 저장한 객체를 참조하는 참조변수
		public static LoginView loginMember = null;
	
	
	
	
	
	/**
	 * 로그인 메뉴 출력 메서드
	 */
	public void loginMenu() {
		int input = -1;
		
		do {
			try {
				if (loginMember == null) { // 로그인 전 화면
					System.out.println("\n===== FMS Program =====\n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원 가입");
/*	시간나면구현	System.out.println("3. 아이디 찾기 ");
					System.out.println("4. 비밀번호 찾기");    */
					System.out.println("0. 프로그램 종료");
					System.out.println("\n======================\n");
					
					System.out.print("[+] 메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (input) {
					case 1: break; // 로그인
					case 2: signUp(); break; // 회원 가입
/*	시간나면구현	case 3:	break; // 아이디 찾기
					case 4:	break; // 비밀번호 찾기 */
					case 0: System.out.println("~~~~~ 프로그램 종료 ~~~~~"); break;
					default: System.out.println("[!] 번호가 잘못되었습니다.");
					}
				} else { // 로그인 성공시 화면 
					System.out.println("\n===== FMS Program =====\n");
					System.out.println("1. 고객 관리");
					System.out.println("2. 물품 관리");
// 시간나면구현		System.out.println("3. 매장 관리 ");
					System.out.println("0. 로그 아웃");
					System.out.println("\n======================\n");
					
					System.out.print("[+] 메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (input) {
					case 1: break; // 고객 관리
					case 2: break; // 물품 관리
// 시간나면구현 	case 3: break; // 매장 관리
					case 0: loginMember = null;
					System.out.println("\n[-] 로그아웃 되었습니다.\n");
					input = -1; break; // 로그아웃
					default: System.out.println("[!] 번호가 잘못되었습니다.");
					}
					
					
				}
				
				
				
			} catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine();
			}
			
		} while (input != 0);
		
	}
	
	
	private void signUp() {
		System.out.println("[회원 가입]");

		String memberId = null; 		// 아이디
		String memberPw1 = null; 		// 비번
		String memberPw2 = null;		// 비번확인
		String memberName = null;		// 이름
		String memberSsn = null;		// 주민번호
		String memberPho = null;		// 전화번호
		String memberAddress = null;	// 주소
		String memberStore = null;		// 담당매장
		
		try {
			while (true) {
				System.out.print("아이디 입력 : ");
				memberId = sc.next();

				// 입력 받은 아이디를 매개변수로 전달하여
				// 중복여부를 검사하는 서비스 호출 후 결과(1/0) 반환 받기
				int result = service.idDupCheck(memberId);

				System.out.println();

				// 중복이 아닌 경우
				if (result == 0) {
					System.out.println("[+] 사용 가능한 아이디 입니다.");
					break;
				} else { // 중복인 경우
					System.out.println("[!] 이미 사용중인 아이디 입니다.");
				}
				System.out.println();
			}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("\n[!] 회원 가입 중 예외 발생");
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	
}
