package team;

public class testMain {

	public static void main(String[] args) {
		 Deck deck = new Deck();
	        deck.shuffle();

	        // 카드를 섞은 후, 카드를 하나씩 뽑아서 출력
	        while (deck.size() > 0) {
	            Card card = deck.drawCard();
	            System.out.println(card.getBack());
	        }
	        
	        
	        
	}

}
