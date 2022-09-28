package fms.article.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import fms.article.model.service.ArticleService;
import fms.article.model.vo.ArticleVo;
import fms.client.model.vo.ClientVo;
import fms.login.model.vo.LoginVo;

public class ArticleView {
	
	private Scanner sc = new Scanner(System.in);
	
	// 로그인 회원 정보 저장용 변수
	private LoginVo loginMember = null;
	
	// 메뉴 번호를 입력 받기 위한 변수
	private int input = -1;
		
	// 물품 관리 서비스를 제공하는 객체 생성
	private ArticleService service =new ArticleService();
	
	
	
	/** 물품 관리 메뉴
	 * @param loginMember
	 */
	public void articleMeun(LoginVo loginMember ) {
		this.loginMember = loginMember;
	
		do {

			try {
				System.out.println("\n===== FMS Article Meun =====\n");
				System.out.println("1. 물품 등록");
				System.out.println("2. 물품 삭제");
				System.out.println("3. 물품 수정");
				System.out.println("4. 물품 재고조회");
				System.out.println("5. 물품 제공등록");
				System.out.println("6. 물품 제공현황");
//				System.out.println("7. 물품 이관");
//				System.out.println("8. 이관 현황");
				System.out.println("0 메인메뉴로 이동");
				System.out.println("\n============================");
				
				System.out.print("\n[+] 메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거

				System.out.println();
				switch (input) {
				case 1:  registration(loginMember);			 break; // 물품 등록
				case 2:  delete(); 							 break;	// 물품 삭제
				case 3:  updateArticle(); 					 break;	// 물품 수정
				case 4:  stockAll();						 break;	// 물품 재고
				case 5:  offeredRegistration(loginMember); 	 break;	// 물품 제공등록
				case 6:  offeredselectAll();		 		 break; // 물품 제공현황
//				case 7:  break;
//				case 8:  break;
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
	 * 물품 등록 
	 */
	private void registration(LoginVo loginMember) {
		
		
		System.out.println("[물품 등록]");

		String 	articleName = null;		// 물품 명칭
		int 	articleQuantity = 0;	// 수량 
		String 	articleUnit = null;		// 단위
		String 	articleSl = null;		// 유통기한
		String 	articleDom = null;		// 제조일자

		
		try {
		
		System.out.print("\n물품 명칭 : ");
		articleName = sc.nextLine();
		
		
		System.out.print("\n물품 수량 : ");
		articleQuantity = sc.nextInt();
		sc.nextLine();
		
		System.out.print("\n단위 : ");
		articleUnit = sc.nextLine();
		
		
		System.out.print("\n유통기한(YYYY-MM-DD) : ");
		articleSl = sc.nextLine();
		
		
		System.out.print("\n제조일자(YYYY-MM-DD) : ");
		articleDom = sc.nextLine();
		
		
		
		ArticleVo articlevo = new ArticleVo(articleName,articleQuantity,
				articleUnit,articleSl,articleDom, loginMember.getMemberNo());
		
		int result = service.registration(articlevo);
		
		System.out.println();
		if (result > 0) {
			System.out.println("[+] 물품 등록 성공");
		} else {
			System.out.println("[!] 물품 등록 실패");
		}
		System.out.println();
		
		} catch (Exception e) {
			System.out.println("\n[!] 회원 가입 중 예외 발생");
			e.printStackTrace();
		}
	}
		

	
	/**
	 * 물품 삭제 
	 */
	public void delete() {
		System.out.println("\n[물품 삭제]\n");
		
		try {
			System.out.print("물품번호 입력: ");
			int articleNo = sc.nextInt();

			while (true) {
				System.out.print("[!] 삭제 복구 할 수 없습니다 [!] ");
				System.out.print("\n정말 삭제하시겠습니까?(Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);

				if (ch == 'Y') {
					// 서비스 호출 후 결과 반환 받기
					int result = service.delete(articleNo);

					if (result > 0) {
						System.out.println("\n[-] 삭제 되었습니다.\n");

					} else {
						System.out.println("\n[!] 물품번호가 일치하지 않습니다.\n");
					}
					break; // while문 종료

				} else if (ch == 'N') {
					System.out.println("\n[X] 취소 되었습니다.\n");
					break;

				} else {
					System.out.println("\n[!] Y또는 N만 입력해 주세요\n");
				}
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 삭제 중 예외 발생\n");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	/**
	 * 물품 재고 목록
	 */
	private void stockAll() {
		System.out.println("\n[물품 재고 조회]\n");

		// DB에서 고객 목록 조회(삭제 고객 미포함)
		try {
			// 회원 목록 조회 서비스 호출 후 결과 반환
			List<ArticleVo> articletList = service.stockAll();

			// 조회 결과가 있으면 모두 출력
			// 없으면 "조회 결과가 없습니다" 출력
			if (articletList.isEmpty()) {// 비어있는 경우
				System.out.println("\n[!] 조회 결과가 없습니다.\n");
			} else {
				System.out.println(" 물품번호 |  물품명  | 수량 | 단위 |  유통기한  |  제조일자  |  등록일자  | 등록자 | 등록매장");
				System.out.println("-------------------------------------------------------------------------------------------------------");
				// 향상된 for문
				for (ArticleVo articlet : articletList) {
					System.out.printf("    %d       %s       %d     %s    %s   %s   %s   %s  %s\n",
							articlet.getArticleNo(),
							articlet.getArticleName(),
							articlet.getArticleQuantity(),
							articlet.getArticleUnit(),
							articlet.getArticleSl(),
							articlet.getArticleDom(),
							articlet.getArticleRd(),							
							articlet.getMemberName(),
							articlet.getMemberStore());
				}
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 재고 조회 중 예외 발생\n");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 물품 정보 수정 
	 */
	private void updateArticle() {
		try {
			System.out.println("\n[물품 정보 수정]\n");
			
			System.out.print("[+] 변경할 물품 번호 : ");
			int articleNo = sc.nextInt();
			sc.nextLine();
			
			int result = service.noCheck(articleNo);
			if (result == 0) { 
				System.out.println("\n[!] 물품 번호가 일치하지 않습니다.  \n");
			} else {
				do {
				System.out.println("\n===== 물품 정보 수정 목록 =====\n");
				System.out.println("1. 물품 명칭");
				System.out.println("2. 물품 수량,단위");
				System.out.println("3. 유통기한");
				System.out.println("4. 제조일자");
				System.out.println("0. 메인메뉴로 이동");
				System.out.println("\n===============================\n");
				
				System.out.print("\n[+] 메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거
				
				System.out.println();
				switch (input) {
				case 1: updatName(articleNo); break; 		// 물품 명칭
				case 2: updatQuantity(articleNo); break; 	// 물품 수량,단위
				case 3: updatSl(articleNo); break; 			// 유통기한
				case 4: updatDom(articleNo); break; 		// 제조일자
				case 0: System.out.println("[+] 메인 메뉴로 이동합니다.");break;
				default: System.out.println("[!] 메뉴에 작성된 번호만 입력해 주세요.");
				}
				} while (input != 0);
			}

		} catch (Exception e) {
			System.out.println("\n[!] 물품 정보 수정 중 예외 발생\n");
			e.printStackTrace();
		}
	}




	/** 
	 * 물품 정보 수정 (물품 명칭)
	 * @param articleNo
	 */
	private void updatName(int articleNo) {
		try {
			System.out.println("\n[물품 명칭 수정]\n");
			
			System.out.print("변경할 명칭 : ");
			String articleName = sc.next();
			sc.nextLine();
			
			ArticleVo articleVo = new ArticleVo();
			articleVo.setArticleNo(articleNo);
			articleVo.setArticleName(articleName);

			
			// 회원 정보 수정 서비스 메서드 호출 후 결과 반환 받기
			int result = service.updatName(articleVo);
			

			if (result > 0) {				
				System.out.println("\n[+] 물품 정보가 수정되었습니다.\n");
			} else {
				System.out.println("\n[!] 수정 실패\n");
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 정보 수정 중 예외 발생\n");
			e.printStackTrace();
		}
	}
	
	

	/**
	 * 물품 정보 수정 (물품 수량,단위)
	 * @param articleNo
	 */
	private void updatQuantity(int articleNo) {
		try {
			System.out.println("\n[물품 수량,단위 수정]\n");
			
			System.out.print("변경할 수량 : ");
			int articleQuantity = sc.nextInt();
			sc.nextLine();
			
			System.out.print("변경할 단위  : ");
			String articleUnit = sc.next();
			sc.nextLine();
			
			
			ArticleVo articleVo = new ArticleVo();
			articleVo.setArticleNo(articleNo);
			articleVo.setArticleQuantity(articleQuantity);
			articleVo.setArticleUnit(articleUnit);

			
			// 회원 정보 수정 서비스 메서드 호출 후 결과 반환 받기
			int result = service.updatQuantity(articleVo);
			

			if (result > 0) {				
				System.out.println("\n[+] 물품 정보가 수정되었습니다.\n");
			} else {
				System.out.println("\n[!] 수정 실패\n");
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 정보 수정 중 예외 발생\n");
			e.printStackTrace();
		}
	}

	
	
	
	/**
	 * 물품 정보 수정 (물품 유통기한)
	 * @param articleNo
	 */
	private void updatSl(int articleNo) {
		try {
			System.out.println("\n[물품 유통기한 수정]\n");
			
			System.out.print("변경할 유통기한 : ");
			String articleSl = sc.nextLine();

			
			ArticleVo articleVo = new ArticleVo();
			articleVo.setArticleNo(articleNo);
			articleVo.setArticleSl(articleSl);
			
			int result = service.updatSl(articleVo);
			
			if (result > 0) {				
				System.out.println("\n[+] 물품 정보가 수정되었습니다.\n");
			} else {
				System.out.println("\n[!] 수정 실패\n");
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 정보 수정 중 예외 발생\n");
			e.printStackTrace();
		}
	}
	
	
	
	
	





	/** 물품 정보 수정 (물품 제조일자)
	 * @param articleNo
	 */
	private void updatDom(int articleNo) {
		try {
			System.out.println("\n[물품 제조일자 수정]\n");
			
			System.out.print("변경할 제조일자 : ");
			String articleDom = sc.nextLine();

			
			ArticleVo articleVo = new ArticleVo();
			articleVo.setArticleNo(articleNo);
			articleVo.setArticleDom(articleDom);
			
			int result = service.updatDom(articleVo);
			
			if (result > 0) {				
				System.out.println("\n[+] 물품 정보가 수정되었습니다.\n");
			} else {
				System.out.println("\n[!] 수정 실패\n");
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 정보 수정 중 예외 발생\n");
			e.printStackTrace();
		}
		
	}
	
	/** 물품 제공등록
	 * @param loginMember
	 */
	private void offeredRegistration(LoginVo loginMember) {// 등록매장이 같으면 배분가능
		try {
			System.out.println("\n[물품 제공등록]\n");

			System.out.print("배분 물품 번호 : ");
			int articleNo = sc.nextInt();
			sc.nextLine();

			int result = service.noCheck(articleNo); // 물품 번호 확인

			if (result > 0) {

				int result2 = service.storeCheck(articleNo, loginMember.getMemberNo()); // 등록 매장확인
				if (result2 == 0) {
					System.out.println("\n[!] 해당 물품은 등록된 매장이 아닙니다.  \n");
				} else {

					System.out.print("고객 번호 : ");
					int clientNo = sc.nextInt();
					sc.nextLine();

					int client = service.cnoCheck(clientNo); // 고객 번호 확인
				
					if (client > 0) { 
						System.out.print("제공 수량 : ");
						int articleQuantity = sc.nextInt();
						sc.nextLine();
							
						
						
						ArticleVo articleVo = new ArticleVo();
						articleVo.setArticleQuantity(articleQuantity);
						articleVo.setArticleNo(articleNo);
						articleVo.setClientNo(clientNo);
						
						int result3 = service.offeredRegistration(articleVo);
						
						if (result3 > 0) {
							System.out.println("\n[+] 물품 제공등록 완료\n");
						} else {
							System.out.println("\n[!] 물품 제공등록 실패\n");
						}
					} else { 
						System.out.println("\n[!] 고객 번호가 일치하지 않습니다.  \n");
					}
				}
			} else {
				System.out.println("\n[!] 물품 번호가 일치하지 않습니다.  \n");
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 배분등록 중 예외 발생\n");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 물품 제공 현황 
	 */
	private void offeredselectAll() {
		System.out.println("\n[물품 제공현황]\n");
		try {
			
			List<ArticleVo> articleList = service.offeredselectAll();


			if (articleList.isEmpty()) {
				System.out.println("\n[!] 조회 결과가 없습니다.\n");
			} else {
				System.out.println(" 물품번호 | 물품명 | 수량 | 단위 | 유통기한 | 제조일자 | 제공일자| 제공매장 ");
				System.out.println("-------------------------------------------------------------------------------------------------------------");
			
				
				for (ArticleVo article : articleList) {
					System.out.printf("   %d   %s    %d    %s    %s     %s       %s  %s\n", article.getArticleNo(), article.getArticleName(),article.getArticleOffer(),
							article.getArticleUnit(),article.getArticleSl(),article.getArticleDom(),article.getArticleDd(),article.getMemberStore());
				}
			}
		} catch (Exception e) {
			System.out.println("\n[!] 물품 제공현황 조회 중 예외 발생\n");
			e.printStackTrace();
		}
	}
	
	
	
}