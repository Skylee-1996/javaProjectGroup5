package team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndianPokerDeck {
	 private List<Card> cards;

	    public IndianPokerDeck() {
	        cards = new ArrayList<>();
	        String[] shapes = {"♠", "♦"};
	        String[] cardValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

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

}
