package team;

import java.io.IOException;
import java.util.Scanner;

public class CoinFlipManager implements ICoinFlip{
	Scanner scan;
	CoinFlip user;
	UserManager u = new UserManager();
	public void gameStart(Scanner scan, String id, int userBalance, UserManager uM) throws IOException, InterruptedException {
		this.scan = scan;
		user = new CoinFlip(id, userBalance);
		int menu = 0;
		
		while(menu != 1 || menu != 2) {
			System.out.println("1.게임 시작 | 2.게임 설명 | 3.게임 종료");
			if (scan.hasNextInt()) {
				menu = scan.nextInt();
				if(menu == 1) {
					int frontBack[] = new int[] {0, 0};
					int checkFrontBack = 0;
					System.out.println("게임을 시작합니다.");
					frontBack = round(scan);
					checkFrontBack = circle();
					if(frontBack[1] == checkFrontBack) {
						System.out.println("맞추셨습니다!!");
						user.plusMoney(frontBack[0] * 2);
						u.printInBox("+" + frontBack[0] + "chip(s)");
						u.printInBox("user chip(s): " + user.getMoney());
						uM.setUserBalance(id, user.getMoney());
						uM.userUpdate();
					}else if(frontBack[1] != checkFrontBack) {
						System.out.println("틀리셨습니다!!");
						user.minusMoney(frontBack[0]);
						u.printInBox("-" + frontBack[0] + "chip(s)");
						u.printInBox("user chip(s): " + user.getMoney());
						uM.setUserBalance(id, user.getMoney());
						uM.userUpdate();
					}
					
				}else if(menu == 2) {
					System.out.println("================게임 설명================");
					System.out.println("코인 플립은 코인의 앞면과 뒷면중 하나를 선택하고");
					System.out.println("베팅하려는 칩을 설정 후 코인을 튕깁니다.");
					System.out.println("그 후, 본인이 선택한 코인의 앞 뒤에 따라");
					System.out.println("맞추면 2배, 맞추지 못하면 전부 잃습니다.");
					System.out.println("=======================================");
					
					System.out.println("메뉴로 돌아가려면 아무키 입력: ");
					scan.nextLine();
					String next = scan.nextLine();
					if(next != null) {
						continue;
					}
				}else if(menu == 3) {
					System.out.println("게임 선택 메뉴로 돌아갑니다.");
					break;
				}else {
					System.out.println("알맞은 숫자를 입력하세요!!");
				}
				
			}else {
				System.out.println("알맞은 숫자를 입력하세요!!");
				scan.next(); // 입력 버퍼 비우기
			}
		}
		
		
	}
	
	public int[] round(Scanner scan) {
		this.scan = scan;
		int selectMenu = 0;
		int mySelect[] = new int[2];
		while(true) {
			u.printInBox("▼▼▼▼▼▼▼▼베팅하려는 코인의 앞 뒤를 선택해주세요.▼▼▼▼▼▼▼▼");
			u.printInBox("1.앞 | 2.뒤");
			if(scan.hasNext()) {
				selectMenu = scan.nextInt();
				
				switch(selectMenu) {
				case 1:
					mySelect[0] = bet(scan);
					mySelect[1] = 1;
					return mySelect;
				case 2:
					mySelect[0] = bet(scan);
					mySelect[1] = 2;
					return mySelect;
				default:
					u.printInBox("잘못된 선택");
				}
			}else {
				u.printInBox("숫자를 입력해주세요");
	            scan.next(); // 입력 버퍼 비우기
	        }
		}
		
		
	}
	
	public int bet(Scanner scan) {
		while(true) {
			u.printInBox("베팅하시려는 칩 수를 입력해주세요");
			System.out.printf(">> ");
			int betMoney = 0;
			if (scan.hasNextInt()) {
				betMoney = scan.nextInt();
				if(betMoney > user.getMoney()) {
					System.out.println("사용자의 보유 칩보다 크게 베팅할 수 없습니다.");
				}else {
					System.out.println(betMoney + "칩 베팅했습니다.");
					return betMoney;
				}
			}else {
				System.out.println("숫자를 입력해주세요");
				scan.next(); // 입력 버퍼 비우기
			}
			
		}
	}
	
	public int circle() throws InterruptedException {
		for(int i = 0; i < 3; i++) {
			printFront();
			Thread.sleep(100);
			print1();
			Thread.sleep(100);
			print2();
			Thread.sleep(100);
			print3();
			Thread.sleep(100);
			print4();
			Thread.sleep(100);
			print5();
			Thread.sleep(100);
			print6();
			Thread.sleep(100);
			print7();
			Thread.sleep(100);
			printBack();
		}
		printFront();
		Thread.sleep(100);
		print1();
		Thread.sleep(100);
		print2();
		Thread.sleep(100);
		print3();
		Thread.sleep(100);
		print4();
		Thread.sleep(100);
		print5();
		Thread.sleep(100);
		print6();
		Thread.sleep(100);
		print7();
		Thread.sleep(100);
		
		int destiny = (int)(Math.random() * 2) + 1;
		if(destiny == 1) {
			u.printInBox("=====================결과=====================");
			printFront();
			u.printInBox("코인: 앞면");
			return destiny;
		}else if(destiny == 2) {
			u.printInBox("=====================결과=====================");
			printBack();
			u.printInBox("코인: 뒷면");
			return destiny;
		}
		return 0;
	}
	
	public void printFront() {
		System.out.println("                               \r\n"
				+ "             :....:            \r\n"
				+ "         :7BZPPSbqPgBr:        \r\n"
				+ "       igg2i:. ....irDBQ:      \r\n"
				+ "      dd:.::::7777i::. iBb     \r\n"
				+ "     Bi.:i.i7vrrrv7vi. ..IB    \r\n"
				+ "    B7:r::7r::::i:. :r7::.KB   \r\n"
				+ "   BU.::iji:::ii:..::iY7:.:MB  \r\n"
				+ "  .B ..iui::iii...:ii::Li.::B  \r\n"
				+ "   B .:rJi:ii...:riiii.Yi:.iB  \r\n"
				+ "  .B ::isr:...:iiii::.:vr..:B  \r\n"
				+ "   vB ::iL. .iii:i:..iJi..:B7  \r\n"
				+ "    EB.:..rrri:::.:i7r:.::Bq   \r\n"
				+ "     BP: .:r7vr7rr7v:..:rBQ    \r\n"
				+ "      rgqri..:::ii: .irEB:     \r\n"
				+ "        sBRQJ.::::.UMQBL       \r\n"
				+ "           v5BBQBBBSY          \r\n"
				+ "                               \r\n"
				+ "                               ");
		
	}
	public void print1() {
		System.out.println("                               \r\n"
				+ "                               \r\n"
				+ "                               \r\n"
				+ "                 .::::i.       \r\n"
				+ "                IBQKqXqRX      \r\n"
				+ "              rBBY....::KB7    \r\n"
				+ "            .PB5:..i7svr..IQ.  \r\n"
				+ "           .BDg .:7riirrr..dB  \r\n"
				+ "           BZD .:jrii:. iL:.gQ \r\n"
				+ "          vbB. rjii:..:ii.v:.B \r\n"
				+ "          rZB:.YY:...:iii.Y:.B \r\n"
				+ "          rgB:.rL:.:rii:iii.:Q \r\n"
				+ "           gEBi  j7iii::ir.:B1 \r\n"
				+ "            BBB .:77i:iri.iBB  \r\n"
				+ "             JBBU..rrri..2QL   \r\n"
				+ "               gBQY::..7Bd     \r\n"
				+ "                .BBQQBBB:      \r\n"
				+ "                               \r\n"
				+ "                               ");
	}
	public void print2() {
		System.out.println("                               \r\n"
				+ "                               \r\n"
				+ "                               \r\n"
				+ "                  rE5USSY      \r\n"
				+ "                :5BBr7i7SB.    \r\n"
				+ "               SIIZ..:ii:SB.   \r\n"
				+ "              BijR :rv7v7:rB.  \r\n"
				+ "             QjrQ rLi:i:7j.sB  \r\n"
				+ "            :B B.iJi:iri..7.BB \r\n"
				+ "            :Q B.rJ:ii:.:.7:BZ \r\n"
				+ "            .B B.rL::..ir.7:BR \r\n"
				+ "            .B:Bi:jr.:rr::7.Bq \r\n"
				+ "             YQiB: vri::ir.Dg  \r\n"
				+ "              EQgB:.77rri.bB   \r\n"
				+ "               :gBQ:.:...PB    \r\n"
				+ "                 7BBQRQQB1     \r\n"
				+ "                   ....:       \r\n"
				+ "                               \r\n"
				+ "                               ");
	}
	public void print3() {
		System.out.println("                               \r\n"
				+ "                               \r\n"
				+ "                 .gbBBMBD.     \r\n"
				+ "                bB 1B7.:7BI    \r\n"
				+ "               7B.:B7 :. LBi   \r\n"
				+ "              .B. Qj :i7:.IB   \r\n"
				+ "              B. BI :77rsr.UB  \r\n"
				+ "             Bi Bs .7v:irr.:IB \r\n"
				+ "             B: Br vs::: :J.sQ \r\n"
				+ "             B. B7 svi...:L.YB \r\n"
				+ "             B. Br vs::i.:s.JB \r\n"
				+ "             Bi B7 7vi:i.:Y.sB \r\n"
				+ "             Bi Bi 7si::::7 LB \r\n"
				+ "             PE:gb..vYi.:L.:QR \r\n"
				+ "              Bg:QI :7r:i.:DB  \r\n"
				+ "               BP:B2 :::..ZB   \r\n"
				+ "                BQUBL   :gB    \r\n"
				+ "                .QBBBQuKQQ.    \r\n"
				+ "                  .::ir7.      ");
	}
	public void print4() {
		System.out.println("                               \r\n"
				+ "                               \r\n"
				+ "                         vgDi  \r\n"
				+ "                        PB  Br \r\n"
				+ "                        PB  B: \r\n"
				+ "                        SB  B: \r\n"
				+ "                        SB  B: \r\n"
				+ "                        SB  B: \r\n"
				+ "                        XB  B: \r\n"
				+ "                        SQ  B: \r\n"
				+ "                        SB  B: \r\n"
				+ "                       1B..Q: \r\n"
				+ "                        uB:iB. \r\n"
				+ "                        JB:iB: \r\n"
				+ "                        jQvYB. \r\n"
				+ "                        IBIKB: \r\n"
				+ "                         IBQY  \r\n"
				+ "                               \r\n"
				+ "                               ");
	}
	public void print5() {
		System.out.println("                               \r\n"
				+ "                  .   .        \r\n"
				+ "                DQgZBBqQi      \r\n"
				+ "               Bv....B: BP     \r\n"
				+ "              Bj.:::.YBi B5    \r\n"
				+ "             QQ ::iii.EB .Br   \r\n"
				+ "            UB ::ii:Li BQ YB.  \r\n"
				+ "           iB .rrii::7: Bg dB  \r\n"
				+ "           iQ :2ri:: vi.Qg dB  \r\n"
				+ "           :B :jr:.:.7r.BP KB  \r\n"
				+ "           :B :ur::i.vi.Qb PQ  \r\n"
				+ "           :B :ur:ii.7r.Bg gB  \r\n"
				+ "           rB :sri::.v: BQ QB  \r\n"
				+ "            ZQ :u7:.s: 2BrUBi  \r\n"
				+ "             B1.:vir..vB1sBL   \r\n"
				+ "              Bi..:..:BbiBq    \r\n"
				+ "               B1....BQUBq     \r\n"
				+ "               .BQMEBBBBv      \r\n"
				+ "                 ...  .        ");
	}
	public void print6() {
		System.out.println("                               \r\n"
				+ "                               \r\n"
				+ "             .gBQQQDBBu        \r\n"
				+ "            PBi.:...JBbSJ      \r\n"
				+ "           7Bi.i:.::.5Q:LRi    \r\n"
				+ "          BR..i77Y7r:.iBv BE   \r\n"
				+ "         gM..rviii::ri.rBi Bj  \r\n"
				+ "        1B:.vv:::i:i.r: uB::Q7 \r\n"
				+ "        Br 77:::iii:. ri BB gQ \r\n"
				+ "        B7 L7::iii....r7.BE qB \r\n"
				+ "        B7.L7iii:..:r.rr.BR gB \r\n"
				+ "        Br Y7i:..:iii.r7 BB QB \r\n"
				+ "        BS.:Y7..iiii.:7.:BS.Bb \r\n"
				+ "        B2 :v7i::::i7..BE BP   \r\n"
				+ "          Bv :rvr:iri. MB:BM   \r\n"
				+ "           BY..:iri. .gBKB2    \r\n"
				+ "           iMbi.:...i5BBd      \r\n"
				+ "             jBBBBBBBBQ:       \r\n"
				+ "                               ");
	}
	public void print7() {
		System.out.println("                               \r\n"
				+ "                               \r\n"
				+ "            sBBQQRQBQr         \r\n"
				+ "          :Qq:.....ZQBP:       \r\n"
				+ "        .DMi..:::::.rbBg2.     \r\n"
				+ "       IML...rLLvrr:..7PRg7    \r\n"
				+ "      KB ..:v7i:i.i7:  .BrBu   \r\n"
				+ "     LB ..i1r::iii..Yi..:BiBv  \r\n"
				+ "    sB. :rLi::ii:...:rr:.iBrQ7 \r\n"
				+ "    SB :r1i::i:...:i:.Lr..B:QU \r\n"
				+ "    IB :rJi:i...:iiii Yi:.BiBs \r\n"
				+ "    KB :is7i ..iri:i.:7i..BiB5 \r\n"
				+ "     EB :.is::iirii.77:. BvBP  \r\n"
				+ "     .BE  .vvr:::::r7...dRqR   \r\n"
				+ "      :QJ ::i7i:ii7:...LBDB.   \r\n"
				+ "        IQv..:irri...7QBBj     \r\n"
				+ "         .gZv.... .:EBBX       \r\n"
				+ "           iBBQQMQRBBQ.        \r\n"
				+ "                               ");
	}
	
	public void printBack() {
		System.out.println("                               \r\n"
				+ "                               \r\n"
				+ "                               \r\n"
				+ "            .ZgdZdM:           \r\n"
				+ "         1PBQjYuJs7BBDq        \r\n"
				+ "       iBPU::iiiiii::5MBj      \r\n"
				+ "      gQr::iiiiiirii:::iBB.    \r\n"
				+ "     BB.iiiiiiriiiiirii::DB    \r\n"
				+ "    QB iiriririririririi::MB   \r\n"
				+ "   UB iiririririririririi::QB  \r\n"
				+ "   IQ iriririririririririi.BB  \r\n"
				+ "   SB riririiiririiiririi:.BB  \r\n"
				+ "   rQr.riririririiiriiiri:iBE  \r\n"
				+ "    IB::riiiiiririiiriii::BQ   \r\n"
				+ "     QB.:iiiiiiiiiiiiii::BB    \r\n"
				+ "      7QX:::iiiiiii::::IBU     \r\n"
				+ "        bBQUv::::::7YBBB       \r\n"
				+ "         .:BBBBBBBBBBr.        \r\n"
				+ "             .    .            ");
	}
}
