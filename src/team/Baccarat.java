package team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baccarat implements IBaccarat {
	private Deck deck;
	boolean end = true;


    public void start(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
    	List<String> gameHistory = new ArrayList<>();
    	BaccaratManager bm = new BaccaratManager();
    	
    	int bankerScore =0;
    	int playerScore =0;
    	
    	bm.gameRule(scan);
    	
    	while (end) {
    	deck = bm.initializeGame();
    	List<Card> bankerCards = new ArrayList<>();
        List<Card> playerCards = new ArrayList<>();
    	bm.showHistory(gameHistory);
    	UserManager.printInBox(id+"님 "+"현재 보유 칩: " + userBalance + "개");        
        int betAmount = bm.hBetting(scan, userBalance);
        String bet = bm.wBetting(scan, userBalance, betAmount);
          
        System.out.println("덱을 셔플합니다!");
        System.out.println("뱅커가 카드를 뽑습니다.");
        bm.drawCardWithDelay(bankerCards, deck);
        UserManager.printInBox("뱅커의 점수는: "+bm.getValue(bankerCards.get(0)));
        bm.delay();
        System.out.println("두번째 카드를 뽑습니다.");
        bm.drawCardWithDelay(bankerCards, deck);
        bankerScore = bm.getScore(bankerCards.get(0), bankerCards.get(1));
        UserManager.printInBox("뱅커의 점수는:"+bankerScore);
        System.out.println("첫번째 카드를 뽑으시겠습니까? enter");
        scan.nextLine();
        scan.nextLine();
        bm.printBack();
        playerCards.add(deck.drawCard());
        deck.printCards(bankerCards, playerCards);
        UserManager.printInBox("뱅커의 점수는 : " +bankerScore + "   플레이어의점수는: "+bm.getValue(playerCards.get(0)));
        System.out.println("두번쨰 카드를 뽑으시겠습니까? enter");
        scan.nextLine();
        bm.printBack();
        playerCards.add(deck.drawCard());
        deck.printCards(bankerCards, playerCards);
        playerScore = bm.getScore(playerCards.get(0), playerCards.get(1));
        UserManager.printInBox("뱅커의 점수는:"+bankerScore+"  플레이어의 스코어:" + playerScore);
     // 플레이어의 3번째 카드 뽑기
        if (playerScore < 8) { // 플레이어가 내츄럴이 아닌 경우만 3번째 카드를 뽑을 수 있습니다.
            if (playerScore <= 5 && bankerScore < 8) { // 뱅커도 내츄럴이 아니어야 합니다.
                System.out.println("플레이어의 점수가 5이하입니다.");
                bm.delay();
                System.out.println("플레이어가 세 번째 카드를 뽑습니다.");
                bm.delay();
                bm.printBack();
                playerCards.add(deck.drawCard());
                deck.printCards(bankerCards, playerCards);
                playerScore = (playerScore + bm.getValue(playerCards.get(2))) % 10;
            }
        } else {
            System.out.println("플레이어는 네츄럴 " + playerScore + "이므로 세 번째 카드를 뽑지 않습니다.");
        }

        // 뱅커의 3번째 카드 뽑기 (플레이어의 3번째 카드가 있는 경우에는 추가적인 규칙이 적용됨)
        if (bankerScore < 8) { // 뱅커가 내츄럴이 아닌 경우만 3번째 카드를 뽑을 수 있습니다.
            if (bankerScore <= 5 && playerScore < 8) { // 플레이어도 내츄럴이 아니어야 합니다.
                if (playerCards.size() == 2 || (playerCards.size() == 3 && bm.shouldBankerDrawThirdCard(bankerScore, bm.getValue(playerCards.get(2))))) {
                    System.out.println("뱅커가 세 번째 카드를 뽑습니다.");
                    bm.delay();
                    bm.printBack();
                    bankerCards.add(deck.drawCard());
                    deck.printCards(bankerCards, playerCards);
                    bankerScore = (bankerScore + bm.getValue(bankerCards.get(2))) % 10;
                }
            }
        } else {
            System.out.println("뱅커는 네츄럴 " + bankerScore + "이므로 세 번째 카드를 뽑지 않습니다.");
        }
        
        bm.delay();
        // 총 점수 표시
        UserManager.printInBox("뱅커의 총 점수는: " + bankerScore+"   플레이어의 총 점수는: " + playerScore);
        String result = bm.getResult(playerScore, bankerScore);
        UserManager.printInBox("결과: " + result + " 승!");
        String historyresult = bm.historyResult(playerScore, bankerScore);
        gameHistory.add(historyresult);  // 결과 추가
        
        if ((result.equals("플레이어") && bet.equals("플레이어")) ||
                (result.equals("뱅커") && bet.equals("뱅커"))) {
                userBalance += betAmount;
                UserManager.printInBox("축하합니다! 배팅 금액이 2배로 반환되었습니다.");
            } else if (result.equals("타이") && bet.equals("타이")) {
                userBalance += betAmount*9; // 타이의 경우, 배팅 금액이 9배로봔환
                UserManager.printInBox("타이입니다! 배팅 금액이 9배로 반환됩니다.");
            }else if(result.equals("타이") && !bet.equals("타이")){
            	UserManager.printInBox("타이입니다! 배팅금액이 그대로 반환됩니다");
            }else {
                userBalance -= betAmount;
                UserManager.printInBox("아쉽네요! 배팅 금액을 잃었습니다.");
            }
        UserManager.printInBox("현재 보유 칩:" + userBalance + "개");
        u.setUserBalance(id, userBalance);
        u.userUpdate();
        end =  bm.endGame(scan, id, userBalance, u);
    	   }//while? 
       } //main   
   	}//class
    
  
