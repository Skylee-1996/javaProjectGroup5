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

        for (int i = 0; i < m.userList.size(); i++) {
            if (m.userList.get(i).getId().equals(id) && m.userList.get(i).getPassword().equals(pass)) {

            	m.printInBox("회원명: " + m.userList.get(i).getName() + " 자본금: " + m.userList.get(i).getBalance()+"원");

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

                m.printInBox("로그인 성공");

                return m.userList.get(i);
            }
        }

        if (!foundUser) {

            m.printInBox("존재하지않는 유저입니다");

        }
        return null;
    }

    public void admin(Scanner scan, UserManager u) throws IOException {

        u.printInBox("1.회원등록 | 2.회원삭제 | 3.회원수정 | 4.회원검색 | 5.자본금 | 6.종료");

        int menu = 0;
        do {
            menu = scan.nextInt();
            switch (menu) {
                case 1:
                    u.userAdd(scan);
                    break;
                case 2:
                    u.deleteUser(scan);
                    break;
                case 3:
                    u.editUser(scan);
                    break;
                case 4:
                    u.searchUser(scan);
                    break;
                case 5:
                    u.balanceChoice(scan);
                    break;
                case 6:
                    break;
                default:

                	u.printInBox("잘못된입력입니다");

            }
        } while (menu != 6);
        System.out.println("프로그램을 종료합니다.");
    }

    public void selectGame(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {

       u.printInBox("1.바카라 |2.블랙잭 |3.인디언포커 |4.랭킹 |5.종료");
        Baccarat baccarat = new Baccarat();
        BlackJack blackjack = new BlackJack(); 

        IndianPokerManager Indian = new IndianPokerManager();
        int menu = 0;
        do {
            System.out.println("게임을 선택해주세요");
            menu = scan.nextInt();
            switch (menu) {
                case 1:
                    baccarat.start(scan, id, userBalance, u);
                    break;
                case 2:

                    blackjack.start(scan, id, userBalance, u);

                    break;
                case 3:
                    Indian.gameStart(scan, id, userBalance, u);
                    break;
                case 4:
                    u.rankUsersByBalance();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        } while (menu != 5);
        System.out.println("프로그램을 종료합니다.");
    }

    public void mainImage() {

    }
}