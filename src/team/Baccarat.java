package team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baccarat {
	private List<String> gameHistory = new ArrayList<>();
	private int userBalance = 1000; // 사용자의 초기 잔액 설정
    public static void main(String[] args) {
    	Baccarat game = new Baccarat();
        game.start();
    }

    public void start() {
    	List<String> gameHistory = new ArrayList<>();
    	
    	Scanner scan = new Scanner(System.in);
    	BaccaratManager bm = new BaccaratManager();
    	   while (true) {
    		   if (!gameHistory.isEmpty()) {
    	            System.out.println("이전 게임 기록: o:플레이어 승 ■: 뱅커 승 ▲: 타이");
    	            for (String history : gameHistory) {
    	                System.out.println(history);
    	            }
    	        }
               List<Card> bankerCards = new ArrayList<>();
               List<Card> playerCards = new ArrayList<>();
               
               System.out.println("현재 잔액: " + userBalance);
             
                   
                   System.out.println("배팅하실 금액을 입력해주세요.");
                   int betAmount = scan.nextInt();

           String bet = bm.StartBetting(scan, userBalance, betAmount);
     	
        Deck deck = new Deck();
        deck.shuffle();
        
        System.out.println("덱을 셔플합니다!");
        System.out.println("뱅커가 카드를 뽑습니다.");
       
        bankerCards.add(deck.drawCard());
        bankerCards.add(deck.drawCard());
	
        Card firstBank = bankerCards.get(0);
        System.out.println("뱅커의 첫번째 카드는!");
        System.out.println(firstBank);
        Card secondBank= bankerCards.get(1);
        System.out.println("뱅커의 두번째 카드는!");
        System.out.print(secondBank);
        int bankerScore = bm.getScore(bankerCards.get(0), bankerCards.get(1));
        System.out.println("뱅커의 스코어:"+bankerScore);
        System.out.println("첫번쨰 카드를 뽑으시겠습니까? enter");
        scan.nextLine();
        scan.nextLine();
        playerCards.add(deck.drawCard());
        Card firstPlayer = playerCards.get(0);
        System.out.println(firstPlayer);
        System.out.println("두번쨰 카드를 뽑으시겠습니까? enter");
        scan.nextLine();
        playerCards.add(deck.drawCard());
        Card secondPlayer = playerCards.get(1);
        System.out.print(secondPlayer);
        int playerScore = bm.getScore(playerCards.get(0), playerCards.get(1));
        System.out.println("플레이어의 스코어:" + playerScore);
     // 플레이어의 3번째 카드 뽑기
        if (playerScore <= 5) {
            System.out.println("플레이어가 세 번째 카드를 뽑습니다.");
            playerCards.add(deck.drawCard());
            Card thirdPlayerCard = playerCards.get(2);
            System.out.println(thirdPlayerCard);
            playerScore = (playerScore + bm.getValue(thirdPlayerCard)) % 10;
        }

        // 뱅커의 3번째 카드 뽑기 (플레이어의 3번째 카드가 있는 경우에는 추가적인 규칙이 적용됨)
        if (bankerScore <= 5 && (playerCards.size() == 2 || (playerCards.size() == 3 && bm.shouldBankerDrawThirdCard(bankerScore, bm.getValue(playerCards.get(2)))))) {
            System.out.println("뱅커가 세 번째 카드를 뽑습니다.");
            bankerCards.add(deck.drawCard());
            Card thirdBankerCard = bankerCards.get(2);
            System.out.println(thirdBankerCard);
            bankerScore = (bankerScore + bm.getValue(thirdBankerCard)) % 10;
        }

        // 새로운 점수 표시
        System.out.println("플레이어의 새로운 점수는: " + playerScore);
        System.out.println("뱅커의 새로운 점수는: " + bankerScore);	

        String result = bm.getResult(playerScore, bankerScore);
        System.out.println("결과: " + result);
        String historyresult = bm.historyResult(playerScore, bankerScore);
        gameHistory.add(historyresult);  // 결과 추가
        
        if ((result.equals("플레이어 승!") && bet.equals("플레이어")) ||
                (result.equals("뱅커 승!") && bet.equals("뱅커"))) {
                userBalance += betAmount;
                System.out.println("축하합니다! 배팅 금액이 2배로 증가했습니다.");
            } else if (result.equals("타이 게임입니다.") && bet.equals("타이")) {
                userBalance += betAmount; // 타이의 경우, 배팅 금액이 그대로 반환
                System.out.println("타이입니다! 배팅 금액이 반환됩니다.");
            } else {
                userBalance -= betAmount;
                System.out.println("아쉽네요! 배팅 금액을 잃었습니다.");
            }
     
        System.out.println("새 게임을 시작하시겠습니까? y/n");
        String select = scan.next();

        if (select.equals("n")) {
            break;
        }
    	   }//while? 
    	   } //main   
    
    
    
     	}//class
    
  