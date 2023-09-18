package team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baccarat {
	private Deck deck;
	boolean end = true;


    public void start(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
    	List<String> gameHistory = new ArrayList<>();
    	List<Card> bankerCards = new ArrayList<>();
        List<Card> playerCards = new ArrayList<>();
    	
    	BaccaratManager bm = new BaccaratManager();
    	
    	int bankerScore =0;
    	int playerScore =0;
    	while (end) {
    		   deck = bm.initializeGame();
    	bm.showHistory(gameHistory);
        System.out.println("현재 잔액: " + userBalance + "원");           
        int betAmount = bm.hBetting(scan, userBalance);
        String bet = bm.wBetting(scan, userBalance, betAmount);
          
     
        
        System.out.println("덱을 셔플합니다!");
        System.out.println("뱅커가 카드를 뽑습니다.");
        bm.drawCardWithDelay(bankerCards, deck);
        System.out.println("뱅커의 점수는: "+bm.getValue(bankerCards.get(0)));
        bm.delay();
        System.out.println("두번째 카드를 뽑습니다.");
        bm.drawCardWithDelay(bankerCards, deck);
        bankerScore = bm.getScore(bankerCards.get(0), bankerCards.get(1));
        System.out.println("뱅커의 점수는:"+bankerScore);
        System.out.println("첫번째 카드를 뽑으시겠습니까? enter");
        scan.nextLine();
        scan.nextLine();
        bm.printBack();
        playerCards.add(deck.drawCard());
        deck.printCards(bankerCards, playerCards);
        System.out.println("뱅커의 점수는 : " +bankerScore + "   플레이어의점수는: "+bm.getValue(playerCards.get(0)));
        System.out.println("두번쨰 카드를 뽑으시겠습니까? enter");
        scan.nextLine();
        bm.printBack();
        playerCards.add(deck.drawCard());
        deck.printCards(bankerCards, playerCards);
        playerScore = bm.getScore(playerCards.get(0), playerCards.get(1));
        System.out.println("뱅커의 점수는:"+bankerScore+"  플레이어의 스코어:" + playerScore);
        // 플레이어의 3번째 카드 뽑기
        if (playerScore <= 5) {
        	System.out.println("플레이어의 점수가 5이하입니다.");
        	bm.delay();
            System.out.println("플레이어가 세 번째 카드를 뽑습니다.");
            bm.delay();
            bm.printBack();
            playerCards.add(deck.drawCard());
            deck.printCards(bankerCards, playerCards);
            playerScore = (playerScore + bm.getValue(playerCards.get(2))) % 10;
        }

        // 뱅커의 3번째 카드 뽑기 (플레이어의 3번째 카드가 있는 경우에는 추가적인 규칙이 적용됨)
        if (bankerScore <= 5 && (playerCards.size() == 2 || (playerCards.size() == 3 && bm.shouldBankerDrawThirdCard(bankerScore, bm.getValue(playerCards.get(2)))))) {
            System.out.println("뱅커가 세 번째 카드를 뽑습니다.");
            bm.delay();
            bm.printBack();
            bankerCards.add(deck.drawCard());
            deck.printCards(bankerCards, playerCards);
            bankerScore = (bankerScore + bm.getValue(bankerCards.get(2))) % 10;
        }

        // 새로운 점수 표시
        System.out.println("플레이어의 총 점수는: " + playerScore);
        System.out.println("뱅커의 총 점수는: " + bankerScore);	
        String result = bm.getResult(playerScore, bankerScore);
        System.out.println("결과: " + result + " 승!");
        String historyresult = bm.historyResult(playerScore, bankerScore);
        gameHistory.add(historyresult);  // 결과 추가
        
        if ((result.equals("플레이어") && bet.equals("플레이어")) ||
                (result.equals("뱅커") && bet.equals("뱅커"))) {
                userBalance += betAmount;
                System.out.println("축하합니다! 배팅 금액이 2배로 증가했습니다.");
            } else if (result.equals("타이") && bet.equals("타이")) {
                userBalance += betAmount*9; // 타이의 경우, 배팅 금액이 9배로봔환
                System.out.println("타이입니다! 배팅 금액이 9배로 증가됩니다.");
            }else if(result.equals("타이") && !bet.equals("타이")){
            	System.out.println("타이입니다! 배팅금액이 반환됩니다");
            }else {
                userBalance -= betAmount;
                System.out.println("아쉽네요! 배팅 금액을 잃었습니다.");
            }
        u.setUserBalance(id, userBalance);
        u.userUpdate();
        end =  bm.endGame(scan, id, userBalance, u);
        
    	   }//while? 
       } //main   
   	}//class
    
  
