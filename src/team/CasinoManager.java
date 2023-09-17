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
	            	
	                System.out.println("회원명: " + m.userList.get(i).getName() + "자본금: " + m.userList.get(i).getBalance());
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
		 Baccarat baccarat = new Baccarat();
		 BlackJackManager blackjack = new BlackJackManager();
		 int menu = 0;	
		 do {		
			 System.out.println("게임을 선택해주세요");
				menu = scan.nextInt();
				switch(menu) {
				case 1: baccarat.start(scan, id, userBalance); break;
				case 2: blackjack.start(); break;
				case 3: u.rankUsersByBalance(); break;
				case 4: break;
					default:
						System.out.println("잘못된입력입니다");
						
				}				
			} while(menu != 4);
			System.out.println("프로그램을 종료합니다.");
		 
		 
	 }
	 public void mainImage() {
		 System.out.println("                                                                                                                \r\n"
		 		+ "          .:::i:                                           :i:.                     Qv                             \r\n"
		 		+ "         BQDgQQq                                        XBBQQBBv                    Qv                             \r\n"
		 		+ "         Bv                                           .BB.                                                         \r\n"
		 		+ "         Bv      :QBBQBBu   DBBBBi   BPYQBBB.         BB          JBBBQg   .bBBBQ.  BJ  vBrZBBBv    vBQBBBi        \r\n"
		 		+ "         BBQBBBr      BI  :Qv   .Br  QBi   BB        :B:               QB  BB       BU  YQb   LBi  QB:   rBs       \r\n"
		 		+ "         B1         iBr   BBIEggPBB  Bb    :Q        :Br           rv5uBB  7BQ1:    Bj  vB     B5 .B.     qB       \r\n"
		 		+ "         BL        JB     BB         BP    :B         BB         LBJ:  BB     7ZB:  BJ  YB     Q2 .Q:     QB       \r\n"
		 		+ "         Bd  . .  QB.  .  .BM.   i   BM    rB.         BBP:. .r: BB   7BB  i.  :Qj  BS  UB     BP  PBU...ZQ:       \r\n"
		 		+ "         RBQBBBQ 7BQRBBBq   5BBQBI   D1    :B           .SBBBBg:  uBBE:1g  vBBBd:   Br  rB     QL   :ZBBQ1         \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                         vQBQ7   DQ                                                \r\n"
		 		+ "               r:        :QBQBQBQBB.     QBBBBBBBB:     BR..:BB  MR                                                \r\n"
		 		+ "            :QBBK                .B.             Bi    JB     B. bg                                                \r\n"
		 		+ "            .i B2        iBZPEPEPED              B:    .BJ   PB  Zg                                                \r\n"
		 		+ "               B2        rB                      Q:      XQBBb   gQ                                                \r\n"
		 		+ "               B2         I2UEBX2XP:             B        .      ED                                                \r\n"
		 		+ "               BP   .i  7i...rB:..:i7  rv777777i5Bv7:    Bg                                                        \r\n"
		 		+ "               Bu   EB .DZPbqS1KqPPZd  5DZdZdZbEqIKDr    BBbdgEgEZP                                                \r\n"
		 		+ "                                                          irrrrrrr7                                                \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                            .      :       iri    :              :       :r7     :                                 \r\n"
		 		+ "                        .:..Bs... 7Q     1BZPEBv  Bv   rMEDPPI  JB     :BgEEBg  :B.                                \r\n"
		 		+ "            .ZQBQi      sgPBBBZdd iB    .B    .B  Br   :77rivB  7B     Br    BJ .B                                 \r\n"
		 		+ "            vs  rBi       XXqqP:  iB     qD:.:QX  Bi        sD  rB     BM   :B: .B                                 \r\n"
		 		+ "                 B:      B7    Bi iB    . JBRQ7   Br       vB   iBBBr   ugBBR:   B                                 \r\n"
		 		+ "               uBi       PBviiPB  iB   iBZUjB1JQB Bi      ZB    rB       .       :                                 \r\n"
		 		+ "             5P:           iBX    iB        B  PbQBr   iZB7     rB       Bu::i::jB                                 \r\n"
		 		+ "            BB  ... 7S  EP2sBEuKd:iQ     iB       i    Lv       7Q       BgJUU2JZB                                 \r\n"
		 		+ "            gRRBQBJ uB  7vrr:ir77.rB     :BBRBBBBBMY            sB       BQbPdPPRB                                 \r\n"
		 		+ "                                  .r              .             .r                                                 \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                         ....   ....                                                                               \r\n"
		 		+ "                         KQdPBBBbEBs     rJvvvvvvr.                                                                \r\n"
		 		+ "            rBBBB7         .JP7dr.       7JjJusJYBD                                                                \r\n"
		 		+ "                7B      .EX7    .v2K     PdIKSqSXBJ                                                                \r\n"
		 		+ "             rvJPr     .JJi..YQi.:7us    Bi                                                                        \r\n"
		 		+ "             v5ug2     .Xs72QBQBMY7jI    EBBQgRBBQP                                                                \r\n"
		 		+ "                 B1       vU7v7v7Ii        B   7Q                                                                  \r\n"
		 		+ "            qUYYRB  XB   .B7     PB    DBQBQBQQBBRBBJ                                                              \r\n"
		 		+ "            :IX5:   iU    .qMBBBg2     ...       ...                                                               \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   \r\n"
		 		+ "                                                                                                                   ");
	 }

}
