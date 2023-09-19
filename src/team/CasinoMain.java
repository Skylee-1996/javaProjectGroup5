package team;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CasinoMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		//1.로그인 | 2.회원가입 | 3.관리자페이지 | 4.종료 |
					Scanner scan = new Scanner(System.in);
					UserManager u = new UserManager();
					CasinoManager m = new CasinoManager();
					try {
						u.downList();
					} catch (FileNotFoundException e) {
						System.out.println("회원가입이 필요합니다");
					}
					
					User playingUser;
					String id;
					int userbalance;
					int menu = 0;	
					do {
						m.mainImage();
						System.out.println("╔═══════════════════════════════════════════════╗");
						System.out.println("           1.로그인 | 2.회원가입 | 3.종료 ");
					    System.out.println("╚═══════════════════════════════════════════════╝");
						System.out.println("메뉴를 선택하세요:");
						
						menu = scan.nextInt();
						switch(menu) {
						case 1: playingUser = m.login(scan, u); 
								id = playingUser.getId();
								userbalance = playingUser.getBalance();
								if(id == null) {
								 break;	
								}
								m.selectGame(scan, id, userbalance, u);
							break;
						case 2: u.userAdd(scan); break;
						case 3: break;
						default:
							System.out.println("잘못된 입력입니다.");
						}				
					} while(menu != 4);
					System.out.println("프로그램을 종료합니다.");
				}

	}


