package team;

public class Practice {
	public class TrumpCardsAsciiArt {
	    public static void main(String[] args) {
	        String[] suits = {"♠", "♦", "♣", "♥"};
	        String[] cardValues = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

	        for (String suit : suits) {
	            for (String value : cardValues) {
	                String card = "┌─────┐\n" +
	                              "│ " + value + "   │\n" +
	                              "│  " + suit + "  │\n" +
	                              "│   " + value + " │\n" +
	                              "└─────┘";
	                System.out.println(card);
	            }
	        }
	    }
	}
}
