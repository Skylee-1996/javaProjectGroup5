package team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Baccarat {
	
    public static void main(String[] args) {
    	Baccarat game = new Baccarat();
        game.start();
    }

    public void start() {
    	Scanner scan = new Scanner(System.in);
    	List<Card> bankerCards = new ArrayList<>();
    	List<Card> playerCards = new ArrayList<>();
     	System.out.println("새 게임을 시작하시겠습니까? y/n");
     	String select = scan.next();
     	while(select.equals("y")) {
     	System.out.println("배팅하실 곳을 선택해주세요 뱅커/플레이어/타이");
     	String bet = scan.next();
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
        System.out.println(secondBank);
        
        System.out.println("첫번쨰 카드를 뽑으시겠습니까? enter");
        scan.nextLine();
        scan.nextLine();
        playerCards.add(deck.drawCard());
        playerCards.add(deck.drawCard());
        int playerScore = getScore(playerCards.get(0), playerCards.get(1));
        int bankerScore = getScore(bankerCards.get(0), bankerCards.get(1));

        System.out.println("플레이어의 점수는: " + playerScore);
        System.out.println("뱅커의 점수는: " + bankerScore);

        String result = getResult(playerScore, bankerScore);
        System.out.println("결과: " + result);
        select = scan.next();
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
}