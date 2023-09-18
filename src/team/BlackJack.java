package team;

import java.io.IOException;
import java.util.Scanner;


public class BlackJack {
	BlackJackManager BJM = new BlackJackManager();
	
	private String userID;
	private int userBalance;

	
	
    public void start(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
    	setUserBalance(userBalance);
    	setUserID(id);
    	System.out.println("어서오세요. "+getUserID()+"님");
    	System.out.println("현재 자본금액은 "+getUserBalance()+"입니다.");

    	while(true) {
    		System.out.print("배팅 금액을 설정해주세요> ");
    		BJM.setUserBet(scan.nextInt());
    		if(BJM.getUserBet()>0&&getUserBalance()>BJM.getUserBet()) {
    			break;
    		}else {
    			System.out.println("잘못된 값입니다.");
    			continue;    			
    		}
    	}
    	
    	firstTurn();
    	
    	while(true) { 
    		if(BJM.getDCSum()==21||BJM.getDCSum()==21) {
    			break;
    		}
    		switch(printMenu(scan)) {
    		case 0:
    			BJM.printRule();
    			continue;
    		case 1:
    			BJM.hit();
    			if(BJM.getUCSum()<21) {
    				printUserCard();
    				continue;
    			}
    			break;
    		case 2:
    			BJM.stand();
    			break;
    		case 3:
    			BJM.doubledown();
    			if(BJM.getUCSum()<21) {
    				printUserCard();
    				continue;
    			}
    			break;
    			default:
    				System.out.println("옳바른 값을 입력해주세요.");
    				continue;
    		}
    		printResult(gameResult(u));
    		u.userUpdate();
    		break;
    	}
    	
    }
    
	//게임 진행에 대한 기술
	public int printMenu(Scanner scan) {
		printInBox("1.Hit|2.Stand|3.DoubleDown|0.룰설명");
    	System.out.print("선택지를 골라주세요> ");
    	int choice = scan.nextInt();
    	
    	return choice;
    }
    
    public void firstTurn() {
    	System.out.println("게임을 시작합니다.");
    	
    	System.out.println("카드를 셔플합니다.");
    	BJM.card.shuffle();
    	
    	for(int i=0; i<=1; i++) {    		
    		BJM.UC.add(BJM.card.drawCard());
    		BJM.DC.add(BJM.card.drawCard());
    	}
    	
    	System.out.println("--------------------");
    	System.out.println("딜러의 카드");
    	
    	System.out.println(BJM.DC.get(0));
    	for(int i=0; i<=1; i++) {
    		BJM.setDCSum(BJM.getDCValue(BJM.DC.get(i))); 
    	}
    	
    	System.out.println("--------------------");
    	System.out.println("유저의 카드");
    	
    	BJM.card.printCards(BJM.UC);
    	for(int i=0; i<=1; i++) {
    		BJM.setUCSum(BJM.getDCValue(BJM.UC.get(i))); 		
  		}
    	
    	System.out.println("유저의 점수 : "+BJM.getUCSum()+" 현재 베팅금 : "+BJM.getUserBet());
    	System.out.println("--------------------");
    	
    }
    
    public void printUserCard() {
    	System.out.println("--------------------");
    	System.out.println("유저의 카드");
    	
    	BJM.card.printCards(BJM.UC);
    	
    	System.out.println("유저의 점수 : "+BJM.getUCSum()+" 현재 베팅금 : "+BJM.getUserBet());
    	System.out.println("--------------------");
    }
    
    public void printResult(int result) {
    	System.out.println("--------------------");
    	System.out.println("딜러의 카드");
    	
    	BJM.card.printCards(BJM.DC);
    	
    	System.out.println("딜러의 점수 : "+BJM.getDCSum());
    	printUserCard();
    	
    	switch(result){
    	case 0:{
    		System.out.println("PUSH");
    		break;    		
    	}
    	case 1:
    		System.out.println("유저 WIN");
    		break;
    	case 2:
    		System.out.println("유저 BlackJack");
    		break;
    	case 3:
    		System.out.println("딜러 BUST, 유저 WIN");
    		break;
    	case 4:
    		System.out.println("딜러 WIN");
    		break;
    	case 5:
    		System.out.println("딜러 BlackJack");
    		break;
    	case 6:
    		System.out.println("유저 BUST, 딜러 WIN");
    		break;
    		default:
    			System.out.println("에러");
    			break;
    	}
    }
    
    public int gameResult(UserManager u) {
    	if(BJM.getUCSum()==BJM.getDCSum()) {
    		u.setUserBalance(getUserID(), getUserBalance());
    		return 0;    		
    	}else if((21-BJM.getUCSum()<21-BJM.getDCSum())&&(BJM.getUCSum()<21&&BJM.getDCSum()<21)) {
    		u.setUserBalance(getUserID(), getUserBalance()+BJM.getUserBet());
    		return 1;
    	}else if(BJM.getUCSum()==21) {
    		u.setUserBalance(getUserID(), getUserBalance()+(int)(BJM.getUserBet()*1.5));
    		return 2;
    	}else if(BJM.getDCSum()>21) {
    		u.setUserBalance(getUserID(), getUserBalance()+BJM.getUserBet());
    		return 3;
    	}else if((21-BJM.getUCSum()>21-BJM.getDCSum())&&(BJM.getUCSum()<21&&BJM.getDCSum()<21)) {
    		u.setUserBalance(getUserID(), getUserBalance()-BJM.getUserBet());
    		return 4;
    	}else if(BJM.getDCSum()==21) {
    		u.setUserBalance(getUserID(), getUserBalance()-(int)(BJM.getUserBet()*1.5));
    		return 5;
    	}else if(BJM.getUCSum()>21) {
    		u.setUserBalance(getUserID(), getUserBalance()-BJM.getUserBet());
    		return 6;
    	}else
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

	
	public void printInBox(String s) {
 	   System.out.println("┌───────────────────────────────────┐");
	   System.out.println("│ "+s+" │");
	   System.out.println("└───────────────────────────────────┘");
   }
}