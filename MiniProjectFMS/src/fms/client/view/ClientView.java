package fms.client.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import static fms.common.JDBCTemplate.*;

import fms.client.model.service.ClientService;
import fms.client.model.vo.ClientVo;
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





	/**
	 * 고객등록
	 */
	private void registration() {
		System.out.println("[고객 등록]");
		

		String clientName = null;		// 이름
		String clientSsn = null;		// 주민번호
		String clientPho = null;		// 전화번호
		String clientAddress = null;	// 주소

		// 주민번호 입력

		try {
			
			while (true) {
				System.out.print("주민번호 입력(-포함) : ");
				clientSsn = sc.next();
				sc.nextLine();
				System.out.println();

				if (clientSsn.length() == 14) { // length을사용해서 clientSsn입력된 문자열길이 확인

					int result = service.ssnCheck(clientSsn);
					if (result == 0) { // 중복 아닐경우
						System.out.println("[+] 등록 가능한 고객 입니다.");
						break;
					} else { // 중복인 경우
						System.out.println("[!] 이미 등록 되어있는 고객 입니다.");
					}
				} else {
					System.out.println("[!] 주민번호가 잘못되었습니다. 다시 입력 해주세요.");
				}
			}
			
			// 이름 입력
			System.out.print("\n이름 입력 : ");
			clientName = sc.next();
			sc.nextLine();
			System.out.println();
			
			// 전화번호
			while (true) {
				System.out.print("\n전화번호 입력(-미포함) : ");
				clientPho = sc.next();
				sc.nextLine();
				System.out.println();

				boolean isNumeric = clientPho.matches("[+-]?\\d*(\\.\\d+)?");

				if (isNumeric == true) {
					System.out.println("[!] 전화번호가 등록되었습니다.");
					break;
				} else {
					System.out.println("[!] 전화번호가 잘못되었습니다. 다시 입력 해주세요.");
				}
			
			}
			// 주소 입력
			System.out.print("\n주소 입력 : ");
			clientAddress = sc.nextLine();
			System.out.println();
			
			// -> 하나의 VO에 담아서 서비스 호출 후 결과 반환 받기
			ClientVo clientvo = new ClientVo(clientSsn,clientName,clientPho,clientAddress);

			int result = service.registration(clientvo);

			// 서비스 처리 결과에 따른 출력 화면 제어
			System.out.println();
			if (result > 0) {
				System.out.println("[+] ---회원 가입 성공---");
			} else {
				System.out.println("[!] 회원 가입 실패");
			}
			System.out.println();
			
		} catch (Exception e) {
			System.out.println("\n[!] 고객 등록 중 예외 발생");
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}
	
	

}
