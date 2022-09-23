package fms.client.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import fms.client.model.service.ClientService;
import fms.login.model.vo.LoginVo;

public class ClientView {
	
	private Scanner sc = new Scanner(System.in);

	// 로그인 회원 정보 저장용 변수
	private LoginVo loginMember = null;
	
	// 고객 관리 서비스를 제공하는 객체 생성
	private ClientService service = new ClientService();
	
	// 메뉴 번호를 입력 받기 위한 변수
	private int input = -1;
	
	
	
	

	/** 고객 관리 메뉴
	 * @param loginMember로그인된 회원 정보)
	 */
	public void clientMeun(LoginVo loginMember ) {
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = loginMember;
		
		do {

			try {
				System.out.println("\n===== FMS Client Meun =====\n");
				System.out.println("1. 고객 등록");
				System.out.println("2. 고객 삭제");
				System.out.println("3. 고객 수정");
				System.out.println("4. 고객 목록");
				System.out.println("0 메인메뉴로 이동");
				System.out.println("\n============================");
				
				System.out.print("\n 메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거

				System.out.println();
				switch (input) {
				case 1: registration(); break;
				case 2:  break;
				case 3:  break;
				case 4:  break;
				case 0: System.out.println("[+] 메인 메뉴로 이동합니다.");break;
				default: System.out.println("[!] 메뉴에 작성된 번호만 입력해 주세요.");
				}
				System.out.println();

			} catch (InputMismatchException e) {
				System.out.println("\n[!] 입력 형식이 올바르지 않습니다.");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거
			}
		} while (input != 0);
	}
	
	

}
