package team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] shapes = {"♠", "♦", "♣", "♥"};
        String[] cardValues = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        int index = 0; // 카드는 각각의 인덱스를 가지고있음

        for (String shape : shapes) {
            for (String value : cardValues) {
                cards.add(new Card(index, shape, value)); // 모든카드 만들기
                index++;  //인덱스++
            }
        }
    }

    public void shuffle() {   //카드셔플
        Collections.shuffle(cards);
    }

    public Card drawCard() { //카드한장 뽑기  = 카드 받을 곳하나 new로생성해서 받은다음 출력해주면됨
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // 덱이 비어있으면 null 반환
    }

    public int size() {   //덱의 남은 카드수 표시할때 사용
        return cards.size();
    }
    
//    public void printCards(Card card1) {
//    	System.out.println(card1);
//     
//    }
//    public void printCards(Card card1, Card card2) {
//    	 System.out.println(     			 
//    			 
//    			 
//                 "┌─────┐┌─────┐\n" +
//                 "│" + card1.padValue(card1.getValue()) + "  ││"+ card2.padValue(card2.getValue()) + "  │\n" +
//                 "│  " + card1.getShape() + "  ││  " + card2.getShape() + "  │  \n" +
//                 "│  " +card1.padValue(card1.getValue())  + "││  " +card2.padValue(card2.getValue())  + "│\n" +
//                 "└─────┘└─────┘");
//    }
//    public void printCards(Card card1, Card card2, Card card3) {
//        System.out.println(
//                "┌─────┐┌─────┐┌─────┐\n" +
//                "│" + card1.padValue(card1.getValue()) + "  ││" + card2.padValue(card2.getValue()) + "  ││" + card3.padValue(card3.getValue()) + "  │\n" +
//                "│  " + card1.getShape() + "  ││  " + card2.getShape() + "  ││  " + card3.getShape() + "  │\n" +
//                "│  " + card1.padValue(card1.getValue()) + "││  " + card2.padValue(card2.getValue()) + "││  " + card3.padValue(card3.getValue()) + "│\n" +
//                "└─────┘└─────┘└─────┘");
//    }
//
//    public void printCards(Card card1, Card card2, Card card3, Card card4) {
//        System.out.println(
//                "┌─────┐┌─────┐┌─────┐┌─────┐\n" +
//                "│" + card1.padValue(card1.getValue()) + "  ││" + card2.padValue(card2.getValue()) + "  ││" + card3.padValue(card3.getValue()) + "  ││" + card4.padValue(card4.getValue()) + "  │\n" +
//                "│  " + card1.getShape() + "  ││  " + card2.getShape() + "  ││  " + card3.getShape() + "  ││  " + card4.getShape() + "  │\n" +
//                "│  " + card1.padValue(card1.getValue()) + "││  " + card2.padValue(card2.getValue()) + "││  " + card3.padValue(card3.getValue()) + "││  " + card4.padValue(card4.getValue()) + "│\n" +
//                "└─────┘└─────┘└─────┘└─────┘");
//    }
//
//    public void printCards(Card card1, Card card2, Card card3, Card card4, Card card5) {
//        System.out.println(
//                "┌─────┐┌─────┐┌─────┐┌─────┐┌─────┐\n" +
//                "│" + card1.padValue(card1.getValue()) + "  ││" + card2.padValue(card2.getValue()) + "  ││" + card3.padValue(card3.getValue()) + "  ││" + card4.padValue(card4.getValue()) + "  ││" + card5.padValue(card5.getValue()) + "  │\n" +
//                "│  " + card1.getShape() + "  ││  " + card2.getShape() + "  ││  " + card3.getShape() + "  ││  " + card4.getShape() + "  ││  " + card5.getShape() + "  │\n" +
//                "│  " + card1.padValue(card1.getValue()) + "││  " + card2.padValue(card2.getValue()) + "││  " + card3.padValue(card3.getValue()) + "││  " + card4.padValue(card4.getValue()) + "││  " + card5.padValue(card5.getValue()) + "│\n" +
//                "└─────┘└─────┘└─────┘└─────┘└─────┘");
//    }

    public void printCards(List<Card> cards) {
        if (cards.isEmpty()) {
            System.out.println("카드가 없습니다!");
            return;
        }

        StringBuilder topLine = new StringBuilder();
        StringBuilder middleLine1 = new StringBuilder();
        StringBuilder middleLine2 = new StringBuilder();
        StringBuilder bottomLine1 = new StringBuilder();
        StringBuilder bottomLine = new StringBuilder();

        for (Card card : cards) {
            topLine.append("┌─────┐");
            middleLine1.append("│" + card.padValue(card.getValue()) + "  │");
            middleLine2.append("│  " + card.getShape() + "  │");
            bottomLine1.append("│  " + card.padValue(card.getValue()) + "│");
            bottomLine.append("└─────┘");
        }

        System.out.println(topLine.toString());
        System.out.println(middleLine1.toString());
        System.out.println(middleLine2.toString());
        System.out.println(bottomLine1.toString());
        System.out.println(bottomLine.toString());
    }

}