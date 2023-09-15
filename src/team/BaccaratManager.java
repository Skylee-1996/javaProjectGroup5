package team;

import java.util.Scanner;

public class BaccaratManager {
	public String StartBetting(Scanner scan, int userBalance, int betAmount) {
          if(betAmount > userBalance) {
              System.out.println("잔액이 부족합니다.");
          }
          System.out.println("배팅하실 곳을 선택해주세요 뱅커/플레이어/타이");
          String bet = scan.next();
          return bet;
	}
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
	
	
	
	 public int getScore(Card card1, Card card2) {
	        int score = getValue(card1) + getValue(card2);
	        return score % 10;
	    }

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
	    public String getResult(int playerScore, int bankerScore) {
	        if (playerScore > bankerScore) {
	            return "플레이어 승!";
	        } else if (playerScore < bankerScore) {
	            return "뱅커 승!";
	        } else {
	            return "타이 게임입니다.";
	        }
	    }
	    public String historyResult(int playerScore, int bankerScore) {
	        if (playerScore > bankerScore) {
	            return "○"; // 플레이어 승리
	        } else if (playerScore < bankerScore) {
	            return "■"; // 뱅커 승리
	        } else {
	            return "▲"; // 타이
	        }
	    }
}
