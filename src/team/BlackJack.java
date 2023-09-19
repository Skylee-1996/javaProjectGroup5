package team;

import java.io.IOException;
import java.util.Scanner;

public class BlackJack {
    BlackJackManager BJM = new BlackJackManager(); // 블랙잭 게임 매니저 인스턴스

    private String userID;       // 사용자 아이디
    private int userBalance;     // 사용자 자본금

    // 블랙잭 게임 시작 메서드
    public void start(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
    	setUserID(id);
        System.out.println("어서오세요. " + getUserID() + "님");
        
        while(true) {
        BJM = new BlackJackManager();	//게임 초기화
        setUserBalance(userBalance);
        System.out.println("현재 보유 칩은 " + getUserBalance() + "입니다.");
        
        userBet(scan);	// 유저 베팅칩 설정
        
        firstTurn(); // 게임 시작 시 초기 설정

        while (true) {
            if (BJM.getUCSum() == 21 || BJM.getDCSum() == 21) {	// 첫턴에 BlackJack 여부 확인
                printResult(gameResult(u));
                break;
            }
            switch (printMenu(scan)) {
                case 0:
                    BJM.printRule();
                    continue;
                case 1:
                    BJM.hit();
                    if (BJM.getUCSum() < 21) {	// 유저의 합계가 21이하면
                        printUserCard();
                        continue;	// 다시 선택지 부여
                    }
                    break;
                case 2:
                    BJM.stand();
                    break;
                case 3:
                    BJM.doubledown();
                    if (BJM.getUCSum() < 21) {	// hit와 같은 동작
                        printUserCard();
                        continue;
                    }
                    break;
                default:
                    System.out.println("옳바른 값을 입력해주세요.");
                    continue;
            }
            printResult(gameResult(u));
            break;
        }
        u.userUpdate();
        u.endGame(scan, id, userBalance, u);
        }
    }

    // 게임 메뉴 출력 및 선택지 입력
    public int printMenu(Scanner scan) {
    	UserManager.printInBox("1.Hit|2.Stand|3.DoubleDown|0.룰설명");
        System.out.print("선택지를 골라주세요> ");
        int choice = scan.nextInt();

        return choice;
    }

    // 초기 게임 세팅
    public void firstTurn() throws InterruptedException {
        System.out.println("게임을 시작합니다.");
        
        System.out.println("카드를 셔플합니다.");
        BJM.card.shuffle();
        Thread.sleep(2000); // 2초 딜레이
        
        for (int i = 0; i <= 1; i++) {
            BJM.UC.add(BJM.card.drawCard());
            BJM.setUCSum(BJM.getDCValue(BJM.UC.get(i)));
            BJM.DC.add(BJM.card.drawCard());
            BJM.setDCSum(BJM.getDCValue(BJM.DC.get(i)));
        }
        System.out.println("딜러가 판을 깔고있습니다...");
    	Thread.sleep(2000); // 2초 딜레이
    	
        System.out.println("--------------------");
        System.out.println("딜러의 카드");

        System.out.println(BJM.DC.get(0));
        
        Thread.sleep(2000); // 2초 딜레이
        
        printUserCard();
    }

    // 유저 카드 출력
    public void printUserCard() {
        System.out.println("--------------------");
        System.out.println("유저의 카드");

        BJM.card.printCards(BJM.UC);

        System.out.println("유저의 점수 : " + BJM.getUCSum() + " 현재 베팅칩  : " + BJM.getUserBet());
        System.out.println("--------------------");
    }

    // 게임 결과 출력
    public void printResult(int result) throws InterruptedException {
    	System.out.println("결과를 확인중입니다...");
    	Thread.sleep(3000); // 3초 딜레이
        System.out.println("--------------------");
        System.out.println("딜러의 카드");

        BJM.card.printCards(BJM.DC);

        System.out.println("딜러의 점수 : " + BJM.getDCSum());
        printUserCard();

        switch (result) {
            case 0:
             UserManager.printInBox("PUSH");
                break;
            case 1:
            	UserManager.printInBox("유저 WIN "+BJM.getUserBet()+"개의 칩 흭득");
                break;
            case 2:
            	UserManager.printInBox("유저 BlackJack "+((int)BJM.getUserBet()*1.5)+"개의 칩 흭득");
                break;
            case 3:
            	UserManager.printInBox("딜러 BUST, 유저 WIN "+BJM.getUserBet()+"개의 칩 흭득");
                break;
            case 4:
            	UserManager.printInBox("딜러 WIN "+BJM.getUserBet()+"개의 칩 감소");
                break;
            case 5:
            	UserManager.printInBox("딜러 BlackJack "+BJM.getUserBet()+"개의 칩 감소");
                break;
            case 6:
            	UserManager.printInBox("유저 BUST, 딜러 WIN "+BJM.getUserBet()+"개의 칩 감소");
                break;
            default:
                System.out.println("에러");
                break;
        }
    }
    
    // 베팅 금액 설정
    public void userBet(Scanner scan) {
    	while (true) {
            System.out.print("배팅 금액을 설정해주세요> ");
            BJM.setUserBet(scan.nextInt());
            if (BJM.getUserBet() > 0 && getUserBalance() > BJM.getUserBet()) {	// 유저는 본인의 보유 칩 이상 그리고 음수의 칩을 걸 수 없음 
                break;
            } else {
                System.out.println("잘못된 값입니다.");
                continue;
            }
        }
    }
  
    // 게임 결과 계산 및 반환
    public int gameResult(UserManager u) {
        if (BJM.getUCSum() == BJM.getDCSum()) {
            u.setUserBalance(getUserID(), 0);
            return 0;
        } else if ((21 - BJM.getUCSum() < 21 - BJM.getDCSum()) && (BJM.getUCSum() < 21 && BJM.getDCSum() < 21)) {
            u.setUserBalance(getUserID(), BJM.getUserBet());
            return 1;
        } else if (BJM.getUCSum() == 21) {
            u.setUserBalance(getUserID(), (int) (BJM.getUserBet() * 1.5));
            return 2;
        } else if (BJM.getDCSum() > 21) {
            u.setUserBalance(getUserID(), BJM.getUserBet());
            return 3;
        } else if ((21 - BJM.getUCSum() > 21 - BJM.getDCSum()) && (BJM.getUCSum() < 21 && BJM.getDCSum() < 21)) {
            u.setUserBalance(getUserID(), -BJM.getUserBet());
            return 4;
        } else if (BJM.getDCSum() == 21) {
            u.setUserBalance(getUserID(), -BJM.getUserBet());
            return 5;
        } else if (BJM.getUCSum() > 21) {
            u.setUserBalance(getUserID(), -BJM.getUserBet());
            return 6;
        } else
            return -1;
    }

    
    
    //getter/setter
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(int userBalance) {
		this.userBalance = userBalance;
	}

}