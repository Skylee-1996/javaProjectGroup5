package team;

import java.io.IOException;
import java.util.Scanner;

public class CasinoManager {

	
	 public User login(Scanner scan, UserManager m) {
	        System.out.print("회원ID: ");
	        String id = scan.next();
	        System.out.print("비밀번호: ");
	        String pass = scan.next();

	        boolean foundUser = false;
       for(int i = 0; i < m.userList.size(); i++) {
	            if (m.userList.get(i).getId().equals(id) && m.userList.get(i).getPassword().equals(pass)) {
	            	
	                System.out.println("회원명: " + m.userList.get(i).getName() + " 자본금: " + m.userList.get(i).getBalance());
	                foundUser = true;
	                Thread loginThread = new Thread(() -> {
	                    try {
	                        System.out.print("로그인 중");
	                        for (int j = 0; j < 3; j++) {
	                            Thread.sleep(500);
	                            System.out.print(".");
	                        }
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                });
	                loginThread.start();

	                try {
	                    loginThread.join();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                System.out.println();
	                System.out.println("로그인성공");
	                return m.userList.get(i);
	            }
	        }

	        if (!foundUser) {
	            System.out.println("존재하지 않는 회원입니다.");
	        }
	        return null;
	    }
	 
	 public void admin(Scanner scan, UserManager u) throws IOException {
		//관리자페이지
		//1.회원등록 | 2.회원삭제 | 3.회원수정 | 4.회원검색 | 5.자본금 | 6.종료
		 System.out.println("1.회원등록 | 2.회원삭제 | 3.회원수정 | 4.회원검색 | 5.자본금 | 6.종료");
		 int menu = 0;	
			do {		
				menu = scan.nextInt();
				switch(menu) {
				case 1: u.userAdd(scan); break;
				case 2: u.deleteUser(scan); break;
				case 3: u.editUser(scan); break;
				case 4: u.searchUser(scan); break;
				case 5: u.balanceChoice(scan); break;
				case 6: break;
					default:
						System.out.println("잘못된입력입니다");
						
				}				
			} while(menu != 6);
			System.out.println("프로그램을 종료합니다.");
		 
	 }
	 public void selectGame(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
		 System.out.println("플레이할 게임을 선택해주세요.");
		 System.out.println("1.바카라 |2.블랙잭 |3.인디언포커 |4.랭킹 |5.종료");
		 Baccarat baccarat = new Baccarat();
		 BlackJackManager blackjack = new BlackJackManager();
		 IndianPokerManager Indian = new IndianPokerManager();
		 int menu = 0;	
		 do {		
			 System.out.println("게임을 선택해주세요");
				menu = scan.nextInt();
				switch(menu) {
				case 1: baccarat.start(scan, id, userBalance, u); break;
				case 2: blackjack.start(); break;
				case 3: Indian.gameStart(id, userBalance, u); break;
				case 4: u.rankUsersByBalance(); break;
				case 5: break;
					default:
						System.out.println("잘못된 입력입니다.");
						
				}				
			} while(menu != 5);
			System.out.println("프로그램을 종료합니다.");
		 
		 
	 }

	 public void mainImage() {
//		 System.out.println(                                                                       "\r\n"
//		 		+ "            ,UBBBEi    LXS,     :UBBBDs ,QZwXQv .HS1   rZv    cQBBg:            \r\n"
//		 		+ "           BBBgwHBB   :BBBB    BQB;:rMQ  aBBBS: ;BBBB  RBB  vBBBs2QBB.          \r\n"
//		 		+ "          GBB         BB.KBP   BBBr,      1BB   :BBXBB vBg  BBs    BBB          \r\n"
//		 		+ "          BBB        EBB .BB.   7BBBBBB   2BQ   ,BB LBBgBg :QB.    pBB          \r\n"
//		 		+ "          7BBJ   ,a :BBBBQBQB  7    vBBr  HBB   :BB  cBBBR  BBB   ,BBK          \r\n"
//		 		+ "           iBBBBBBB BBK    BBP BBBBBQB1 :BBBBBS ;BB   rBBB   DBBBBBBL           \r\n"
//		 		+ "                                                                                 ");
//System.out.println("             .,.                                                       \r\n"
//		+ "                  JBBBBBBBBQP:                                                  \r\n"
//		+ "                7BBBBBBBBBBBBBB1                                                \r\n"
//		+ "               rBBBBBBBBBBBBBQBBBv                  :r7rr,                      \r\n"
//		+ "               BBBQBZwrr7SQBBBMQBBQ,            ;pQBD6aKZRQMa;                  \r\n"
//		+ "            ,:HBQi         :BBBRQQBB7         1QRr          rDBS                \r\n"
//		+ "          .H;. :w7s1,        BBBgMMBBa      ;BB:               PBv              \r\n"
//		+ "          62:   sM rBM        BBQRMRBBZ    GBw        :sHPa7,   .BX             \r\n"
//		+ "          sEBBQBgc   MQ       :BQQgRRBBG  BB:       UBQBBBBBBBs   B2            \r\n"
//		+ "           ;wp6s;     BK       EBMRgRRBBHBB       LBBBBQQQBBBBBB: .B.          ,\r\n"
//		+ "                      .B.      ,QBRRgMMBBB       MBBQMRBBBBBQBBBBr Ga          .\r\n"
//		+ "                ,isHgRBBr       BQMgRRBBB      :BQBRMRBBBU:  .sQBB:,B           \r\n"
//		+ "          :JZBQBBBBBBBBBr       JBRMgQBB.     rBBBRQMBQr        :BBJQ           \r\n"
//		+ "       7DBBBBBBBBQBQBMBB:       rBQgMBBr     rBBBBBQBBa           BBBi          \r\n"
//		+ "     pBBQBBBBBBBBBBBBBQB        ;BMRQBX     :BBBBQaLr7L6QBMS:    ;r  .57        \r\n"
//		+ "   ;BQBQQMBBBBMSsr7v26BS        :BBRBQ      BBB7          ;XBB5  O;. .rQ,       \r\n"
//		+ "   BBBMMMBBO:         L:    .OR6MBBBBr  ,:,RBr               :EB:UEBBBgQ        \r\n"
//		+ "  :BBRRRBB:           :    ;B1   ;BBB LBRPpBBi   ,JaUc:        rB;rsUJJ         \r\n"
//		+ "   BBBRBQ.             RBBSB,      GBBw      SQsKBBBBBBBZ       6E              \r\n"
//		+ "   rBBBQw             2B ,B7        Er         RBBBQQMBBBB:     rB              \r\n"
//		+ "    XBBB7    SQRRDU7. Bc  2:.,.           .,:.  rBBBQBRQBBB     LB              \r\n"
//		+ "     EBBR   7B    7BBBB   PR65EQB;     rBBZaGQE  ;BBEQBBQBBL    BK              \r\n"
//		+ "      MBBL  ag   .Br.,,         6B     Bp         .. :QBQBBM   ,B:              \r\n"
//		+ "       GBB; sO   ;BK          Jr:QL   2B,rJ          sB:pQBB   6B               \r\n"
//		+ "        5BQ::B    BB1      EPMHBBs:   ,cBBURXP      wBB  BBB   B:               \r\n"
//		+ "         :BBBQ:    gBg    iBB, rJS     p57 :BB:    gBO   PBB  DR                \r\n"
//		+ "          5:  :a.   RBL7: JSr.:.         .:,rX5 ;;vBM    ;BB :Q.                \r\n"
//		+ "         SU.  :UZ   :B:rB:                     :B:7B:     BBrBr                 \r\n"
//		+ "         7QBBBBR7    B5 Zg      :2     H:      g5 XB      QBBE                  \r\n"
//		+ "          ;sHaJ;     ,BpBB       sD   O2       BQ6B.    .5:  .5.                \r\n"
//		+ "                         EO  Pi   UB,BJ   iS  QP        DP,  :w6                \r\n"
//		+ "                          B. rB;   SBX   iBi ,B6        cQBBBBD7                \r\n"
//		+ "  i;;.                   :BP  SP,:;LHs;:,Ep  6RQO        ,7517,    ;:;i         \r\n"
//		+ " ,c :ar..       ..:;sSOMBP6Q  ;B::rir;r::B:  Q, OBH:            ,rgJ. aH        \r\n"
//		+ " :BBBUPZgBQBBBBBQBRGSL:.   Bs  BH  ,:,  HE  wB   .JBQBRgGEpEDQBBg11pQBQL        \r\n"
//		+ "  ;rBE                     ,B;  66     G6  rB,       .,::::..      rBr.         \r\n"
//		+ "     RBKi:,.                :BJ  MBBOBBg  JB:                  :;:;BJ           \r\n"
//		+ "      ;RBGpKasc;:,.           B:  SBBBL  ;B                .:c2ssGBi            \r\n"
//		+ "         rsXEOKPBBi.          aB      .: BS            2UZgBBQggU:              \r\n"
//		+ "               EB ,.           BM :;r7: OQ             2BJ:::.                  \r\n"
//		+ "              iB :;        :    BB,   .BM              .gX                      \r\n"
//		+ "              B; S      .,QR     rQQRQg;  5Q,          :rB                      \r\n"
//		+ "             rB X.  :;7PQQBs .           EB6B7         :;Bv                     \r\n"
//		+ "             DB U;s6MgBp: Bs 7        ,,BQ  :BG,:       JPE                     \r\n"
//		+ "           ;v..r1DQDs:    aB S.     :r;BO     gB7;;.    SPg                     \r\n"
//		+ "           OXLLKZ.         B; K   .L;1B7       :BBsrv:  awB                     \r\n"
//		+ "           ;6BB5           ;Q,,w.7c7QB.          7RMa27rRUB:                    \r\n"
//		+ "                            rBr7Q7UBr              ,SggRX  ;2                   \r\n"
//		+ "                             :B. LR                    LXMQQZ.                  \r\n"
//		+ "                             :prr7P                     ;1Ps,                   \r\n"
//		+ "                              UBBQ7                                             \r\n");
//		 System.out.println("                                                  \r\n"
//		 		+ "                     ,s57    :HDa:                \r\n"
//		 		+ "            .:,   gBBBBBBBBRBBv.7BBBBv            \r\n"
//		 		+ "          ,BBEBBQBP:      .BQ  r;  JBBBr          \r\n"
//		 		+ "         :BK:pBOBO   B.   BB .B1XB    1BBBi       \r\n"
//		 		+ "        :BBBOr  BE  BBB  BB  QB,OB       XQBBi    \r\n"
//		 		+ "       RBK      BG GBBBGBB    LMZ          .KBBH  \r\n"
//		 		+ "      BB  rBQO  Bg  .  BQ   sB                 BB \r\n"
//		 		+ "     :BJ gB  BR BO   DBQ                       BB \r\n"
//		 		+ "    :BOB; gBpBM Qg   BQ     7URBBBBK          DB. \r\n"
//		 		+ "   :Bs .B: J1   BE  BB    PBD27:. SB         gB.  \r\n"
//		 		+ "  ,B1 ;EBB:   B;BU BB     BB       BB       gB.   \r\n"
//		 		+ "  BM SB7 PBr    BZBB      .gQMBB;   Bv     gB.    \r\n"
//		 		+ "  BB  7BMBBB7   BBB        .XBQBBr  BB    gB.     \r\n"
//		 		+ "   RBQ:  . :B7  BB        rQBZBB:BBBO    gB.      \r\n"
//		 		+ "     7BBQ:  ,B;BB            2BB        pB.       \r\n"
//		 		+ "        cBBB:vBB                       aB         \r\n"
//		 		+ "           7BBB             rBQBJ     wBB:        \r\n"
//		 		+ "             ,Bs           BBi.rBBr  wBBBBD       \r\n"
//		 		+ "              ,BBO;        BB    cBB5BBM  BG      \r\n"
//		 		+ "                BBMBB;     :BB     pBBB;  5B      \r\n"
//		 		+ "                BB  iBBp,    DBZ     RBR  :B.     \r\n"
//		 		+ "                 GBBMOBBBBE:   gB;    ;r  ;Q.     \r\n"
//		 		+ "                    sBBJUBBBBa.:BBR       ;B      \r\n"
//		 		+ "                      7XH;   BBLBB.       ;B.     \r\n"
//		 		+ "                             B, B5        ;B      \r\n"
//		 		+ "                             B; BK        rB.     \r\n"
//		 		+ "                             BQ 1B,       7B,     \r\n"
//		 		+ "                              2  sr        w      ");
//		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	 }

}
