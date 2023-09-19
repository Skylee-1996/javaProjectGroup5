package team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaccaratManager implements IBaccaratManager {
	UserManager u = new UserManager();
	
		//게임시작 초기화
	public Deck initializeGame() {
			
	        Deck deck = new Deck();
	        deck.shuffle();
	        System.out.println("바카라를 시작합니다.");
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
		   
	       System.out.println("┌───────────────────────┐");
	       System.out.println("│ 플레이어 o 뱅커 ■  타이 ▲  ");
	       System.out.println("└───────────────────────┘");
	        
		if(betAmount > userBalance) {
              System.out.println("잔액이 부족합니다.");
          }
       System.out.println("배팅할 곳을 입력해주세요");
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
	//카드 뒷면 출력
	public void printBack() throws InterruptedException {
		Card back = new Card(1,"1","1");
		System.out.println(back.getBack());
		System.out.println("카드를 뒤집습니다...");
	    Thread.sleep(2000); // 2초 딜레이
		
	}
	//게임기록
	public void showHistory(List<String> gameHistory) {
		  if (!gameHistory.isEmpty()) {
	            System.out.println("이전 게임 기록: o:플레이어 승 ■: 뱅커 승 ▲: 타이");
	            System.out.println("┌───────────────────────────────────────────────┐");
	            System.out.print("│ ");
	            for (String history : gameHistory) {
	            System.out.print(history + "|");
	            }
	            System.out.println();
	            System.out.println("└───────────────────────────────────────────────┘");
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
	//게임룰
	public void gameRule(Scanner scan) {
		UserManager.printInBox("1.게임룰 보기 | 2.게임시작(아무키)");
		scan.nextLine();
		String rule = scan.nextLine();
		if(rule.equals("1")) {
		System.out.println("\r\n"
				+ "바카라는 간단한 카지노 카드 게임으로, 플레이어와 뱅커(Banker) 두 가지 사이드 중 어느 한 쪽에 베팅을 합니다. "
				+ "목표는 선택한 사이드의 카드 합계가 9에 가장 가까워지도록 하는 것입니다. 게임은 다음과 같은 단계로 진행됩니다:\r\n"
				+ "\r\n"
				+ "베팅: 플레이어는 '플레이어', '뱅커' 또는 '무승부(Tie)' 중 하나에 베팅을 합니다.\r\n"
				+ "\r\n"
				+ "카드 분배: 플레이어와 뱅커 모두에게 두 장의 카드가 분배됩니다. 카드는 뒤집어져 있어, 값이 보이지 않습니다.\r\n"
				+ "\r\n"
				+ "카드 값 계산: 카드의 값을 계산하여 합계를 구합니다. 10, J, Q, K는 0점으로 계산되고, 에이스는 1점, "
				+ "나머지 카드는 표시된 숫자만큼의 점수를 가집니다. 카드 합계가 10을 초과하면 10을 뺀 값을 사용합니다. 예를 들어, 12점이면 2점이 됩니다.\r\n"
				+ "\r\n"
				+ "추가 카드: 특정 조건에 따라 플레이어 또는 뱅커가 한 장의 추가 카드를 받을 수 있습니다.\r\n"
				+ "\r\n"
				+ "승자 결정: 카드의 합이 9에 가장 가까운 사이드가 승리합니다. 무승부의 경우, 플레이어와 뱅커의 카드 합계가 같아야 합니다.\r\n"
				+ "\r\n"
				+ "베팅한 사이드가 이기면 베팅 금액을 돌려받고, 더불어 이긴 사이드에 따라 일정한 비율의 이길 돈을 얻습니다. 무승부에 베팅한 경우에는 높은 배당률을 얻습니다.");
		scan.nextLine();
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
	    
	    //1.5초 딜레이주기
	    public void delay() {
	        try {
	            Thread.sleep(1500); 
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
