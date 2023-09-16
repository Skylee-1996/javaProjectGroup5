package team;

import java.util.ArrayList;
import java.util.List;

public class testMain {

	public static void main(String[] args) {
		 Deck deck = new Deck();
	        deck.shuffle();
	        
	        
	        List<Card> bankerCards = new ArrayList<>();
	        
	        bankerCards.add(deck.drawCard());
	        bankerCards.add(deck.drawCard());
	        bankerCards.add(deck.drawCard());
	        deck.printCards(bankerCards);
	        

	}

}
