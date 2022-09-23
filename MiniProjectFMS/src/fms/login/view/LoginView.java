package fms.login.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import fms.client.view.ClientView;
import fms.login.model.service.LoginService;
import fms.login.model.vo.LoginVo;

public class LoginView {

	private Scanner sc = new Scanner(System.in);
	
	// 로그인서비스 객체 생성
	private LoginService service = new LoginService();
	
	// 고객 관리 메뉴 객체 생성
	private ClientView clientView = new ClientView();
	
	
	// 로그인된 회원 정보를 저장한 객체를 참조하는 참조변수
		public static LoginVo loginMember = null;
	
	
	
	
	
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
// 시간나면구현		System.out.println("3. 아이디 찾기 ");
// 시간나면구현		System.out.println("4. 비밀번호 찾기");    
					System.out.println("0. 프로그램 종료");
					System.out.println("\n======================\n");
					
					System.out.print("[+] 메뉴 선택 : ");
					input = sc.nextInt();
					sc.nextLine();
					System.out.println();
					
					switch (input) {
					case 1: login(); break; // 로그인
					case 2: signUp(); break; // 회원 가입
//	시간나면구현	case 3:	break; // 아이디 찾기
//	시간나면구현	case 4:	break; // 비밀번호 찾기 
					case 0: System.out.println("~~~~~ 프로그램 종료 ~~~~~"); break;
					default: System.out.println("[!] 번호가 잘못되었습니다.");
					}
				} else { // 로그인 성공시 화면 
					System.out.println("\n===== FMS Loing Menu =====\n");
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
					case 1: clientView.clientMeun(loginMember); break;// 고객 관리
					case 2: break; // 물품 관리
// 시간나면구현 	case 3: break; // 매장 관리
					case 0: loginMember = null;
					System.out.println("\n[-] 로그아웃 되었습니다.\n");
					input = -1; break; // 로그아웃
					default: System.out.println("[!] 번호가 잘못되었습니다.");
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\n[!] 입력 형식이 올바르지 않습니다.");
				sc.nextLine();
			}
		} while (input != 0);
	}
	
	
	
	
	
	/**
	 * 회원가입
	 */
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
			
			// 비밀번호 입력
			// 비밀번호/비밀번호 확인이 일치 할 때 까지 무한 반복
			while (true) {
				System.out.print("\n비밀번호 : ");
				memberPw1 = sc.next();

				System.out.print("비밀번호 확인 : ");
				memberPw2 = sc.next();

				System.out.println();
				if (memberPw1.equals(memberPw2)) { // 일치할 경우
					System.out.println("[!] 비밀번호가 일치합니다.");
					break;
				} else { // 일치하지 않을 경우
					System.out.println("[!] 비밀번호가 일치하지 않습니다. 다시 입력 해주세요.");
				}
				System.out.println();
			}
			
			// 이름 입력
			System.out.print("\n이름 입력 : ");
			memberName = sc.next();
			sc.nextLine();
			System.out.println();
			
		

			// 주민번호 입력
			while (true) {
				System.out.print("주민번호 입력(-포함) : ");
				memberSsn = sc.next();
				sc.nextLine();
				System.out.println();

				if (memberSsn.length() == 14) { // length을사용해서 memberSsn입력된 문자열길이 확인
					System.out.println("[!] 주민번호가 등록되었습니다.");
					break;
				} else {
					System.out.println("[!] 주민번호가 잘못되었습니다. 다시 입력 해주세요.");
				}
				System.out.println();
			}
			
			
			
			
			// 전화번호
			while (true) {
				System.out.print("\n전화번호 입력(-미포함) : ");
				memberPho = sc.next();
				sc.nextLine();
				System.out.println();

				boolean isNumeric = memberPho.matches("[+-]?\\d*(\\.\\d+)?");

				if (isNumeric == true) {
					System.out.println("[!] 전화번호가 등록되었습니다.");
					break;
				} else {
					System.out.println("[!] 전화번호가 잘못되었습니다. 다시 입력 해주세요.");
				}
			
			}
			// 주소 입력
			System.out.print("\n주소 입력 : ");
			memberAddress = sc.nextLine();
			System.out.println();
	     
			// 담당매장 입력
			System.out.print("\n담당매장 입력 : ");
			memberStore = sc.next();
			System.out.println();
			
			
			
			
		
			// -> 하나의 VO에 담아서 서비스 호출 후 결과 반환 받기
			LoginVo loginvo = new LoginVo(memberId, memberPw2, memberName, memberSsn, memberPho, memberAddress,
					memberStore);

			int result = service.signUp(loginvo);

			// 서비스 처리 결과에 따른 출력 화면 제어
			System.out.println();
			if (result > 0) {
				System.out.println("[+] ---회원 가입 성공---");
			} else {
				System.out.println("[!] 회원 가입 실패");
			}
			System.out.println();

		} catch (Exception e) {
			System.out.println("\n[!] 회원 가입 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	/**
	 * 로그인 화면
	 */
	private void login() {
		System.out.println("[로그인]");
		
		System.out.print("아이디 : "); 
		String memberId = sc.next();
		
		System.out.print("비밀번호 : "); 
		String memberPw = sc.next();
		
		try {
			// 로그인 서비스 호출 후 조회 결과를 loginMember에 저장
			loginMember = service.login(memberId, memberPw);
		
			System.out.println();
			if(loginMember != null) { // 로그인 성공 시
				System.out.println("[+] " + loginMember.getMemberName() + "님 환영합니다.");
				
			} else { // 로그인 실패 시
				System.out.println("[!] 아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			System.out.println();
		
		}catch(Exception e) {
			System.out.println("\n[!] 로그인 중 예외 발생\n");
		}
		
	}
	
	
	
}
