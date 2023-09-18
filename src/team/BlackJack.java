package team;

import java.io.IOException;
import java.util.Scanner;


public class BlackJack {
	BlackJackManager BJM = new BlackJackManager();
	Scanner scan;
	
	private String userID;
	private int userBal;
	private UserManager um;
	
	public BlackJack() {}
	
    public void start(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
    	System.out.println();
    	
    	firstTurn();
    	
    	while(true) { 
    		switch(printMenu()) {
    		case 1:
    			BJM.hit();
 //   			if() {
 //   				continue;
 //   			}
    			break;
    		case 2:
    			BJM.stand();
    			break;
    		case 3:
    			BJM.doubledown();
    		}
    		
    	}
    }
    
	//게임 진행에 대한 기술
	public int printMenu() {
    	System.out.println("1.Hit|2.Stand|3.DoubleDown|0.룰설명");
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
    	
    	BJM.DC.get(0).toString();
    	for(int i=0; i<=1; i++) {
    		BJM.setDCSum(BJM.getDCValue(BJM.DC.get(i))); 
    	}
    	
    	System.out.println("--------------------");
    	System.out.println("유저의 카드");
    	
    	BJM.card.printCards(BJM.UC);
    	
    	System.out.println("--------------------");
    	
    	
    }
    
    public void printResult() {
    	
    }
    
}