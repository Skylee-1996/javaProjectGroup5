package team;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BaccaratManager {
	
		//게임시작 초기화
	public Deck initializeGame() {
	        Deck deck = new Deck();
	        deck.shuffle();
	        System.out.println("게임을 초기화합니다.");
	        System.out.println("덱을 셔플합니다!");
	        return deck;
	    }
	//얼마배팅
	public int hBetting(Scanner scan,int userBalance) {
		 int betAmount = 0;
         while (true) {
             try {
                 System.out.println("배팅하실 금액을 입력해주세요.");
                 betAmount = scan.nextInt();
                 if (betAmount <= 0 || betAmount>userBalance) {
                     throw new IllegalArgumentException();
                 }
                 break;
             } catch (Exception e) {
                 System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                 scan.nextLine();
             }
         }
         return betAmount;
	}
	
	
	//어디배팅
	public String wBetting(Scanner scan, int userBalance, int betAmount) {
          if(betAmount > userBalance) {
              System.out.println("잔액이 부족합니다.");
          }
          System.out.println("배팅하실 곳을 선택해주세요 뱅커/플레이어/타이");
          String bet = scan.next();
          return bet;
	}
	//카드 뽑기 딜레이주기
	public void drawCardWithDelay(List<Card> cards, Deck deck) throws InterruptedException {
	    
	    Card newCard = deck.drawCard();
	    System.out.println(newCard.getBack()); // 카드의 뒷면을 보여줌
	    System.out.println("카드를 뒤집습니다...");
	    Thread.sleep(2000); // 2초 딜레이
	    cards.add(newCard);
	    deck.printCards(cards); // 카드의 앞면을 보여줌
	}
	
	//게임기록
	public void showHistory(List<String> gameHistory) {
		  if (!gameHistory.isEmpty()) {
	            System.out.println("이전 게임 기록: o:플레이어 승 ■: 뱅커 승 ▲: 타이");
	            for (String history : gameHistory) {
	                System.out.println(history);
	            }
	        }
	}
	//뱅커 3번쨰카드 뽑아햐하나
	public boolean shouldBankerDrawThirdCard(int bankerScore, int playerThirdCardValue) {
	    switch (bankerScore) {
	        case 0:
	        case 1:
	        case 2:
	            return true;
	        case 3:
	            return playerThirdCardValue != 8;
	        case 4:
	            return playerThirdCardValue >= 2 && playerThirdCardValue <= 7;
	        case 5:
	            return playerThirdCardValue >= 4 && playerThirdCardValue <= 7;
	        case 6:
	            return playerThirdCardValue == 6 || playerThirdCardValue == 7;
	        default:
	            return false;
	    }
	}
	
	
	//카드 두개의 스코어값 계산
	 public int getScore(Card card1, Card card2) {
	        int score = getValue(card1) + getValue(card2);
	        return score % 10;
	    }
	 //카드별 점수 계산
	    public int getValue(Card card) {
	        String cardValue = card.getValue();
	        switch (cardValue) {
	            case "A": return 1;
	            case "2": return 2;
	            case "3": return 3;
	            case "4": return 4;
	            case "5": return 5;
	            case "6": return 6;
	            case "7": return 7;
	            case "8": return 8;
	            case "9": return 9;
	            default: return 0;
	        }
	        
	    }
	    
	    //최종비교
	    public String getResult(int playerScore, int bankerScore) {
	        if (playerScore > bankerScore) {
	            return "플레이어";
	        } else if (playerScore < bankerScore) {
	            return "뱅커";
	        } else {
	            return "타이";
	        }
	    }
	    
	    //게임기록표시
	    public String historyResult(int playerScore, int bankerScore) {
	        if (playerScore > bankerScore) {
	            return "○"; // 플레이어 승리
	        } else if (playerScore < bankerScore) {
	            return "■"; // 뱅커 승리
	        } else {
	            return "▲"; // 타이
	        }
	    }
	    
	    //1초 딜레이주기
	    public void delay() {
	        try {
	            Thread.sleep(1000); 
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt(); 
	        }
	    }
	   //게임종료
	    public boolean endGame(Scanner scan, String id, int userBalance, UserManager u) throws InterruptedException, IOException {
	    	CasinoManager m = new CasinoManager();
	    	  System.out.println("1.다음게임시작 2.게임선택 3.종료");
	          int select = scan.nextInt();

	          switch(select) {
	           case 1: break;
	           case 2: m.selectGame(scan, id, userBalance, u); break;
	           case 3: 
	        	   	return false;
	          
	          }
	          return true;
	    }
}
