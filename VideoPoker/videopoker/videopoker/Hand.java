package videopoker;

/**
 * A class that represents a 5 card hand
 * 
 * @author Dan Blossom
 *
 */
public class Hand {
	
	// A deck of (suffled) cards
	Deck deck = new Deck();
	
	// An array to hold this hand
	Card[] hand = new Card[5];
	
	/**
	 * Default constructor
	 */
	public Hand(){}
	
	/**
	 * Deals out the deck - fills the hand array
	 */
	public void deal(){		
		for(int i = 0; i < hand.length; i++){
			hand[i] = deck.shuffled.remove();
		}
	}
	
	/**
	 * Replaces a card in the hand with the next card
	 * @param card the card position to be changed
	 */
	public void newCard(int card){
		hand[card] = deck.shuffled.remove();
	}
	
	/**
	 * Prints out the hand, for testing really
	 */
	public void printHand(){
	    
		for(Card card: hand){
		    System.out.println(card.valueToString() + card.suitToChar());
		}
	}
	
	/**
	 * Main for testing ... 
	 * @param args
	 */
	public static void main(String[] args){
		
		Hand h = new Hand();
		
		h.deal();
		
		h.printHand();
		
		h.newCard(2);
		
		System.out.println("-----");
		
		h.printHand();
		
	}

}
