package videopoker;

import java.util.ArrayList;

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
		    System.out.print(card.valueToString() + card.suitToChar() + " ");
		}
	}
	
	/**
	 * Determines if the pair is jacks or higher
	 * 
	 * TODO: while it makes sense to be in hand, it should not be ...
	 */
	public boolean isJacksOrHigher(){
		
		ArrayList<Card> sortedHand = Rank.sort(this);
		int position = 0;
		
        while(position < 4){
        	// if the current and the next match, we found the pair...
        	if(sortedHand.get(position).value == sortedHand.get(++position).value){
        		// now is that pair jacks or higher?
        		if(sortedHand.get(position).value >= Card.JACK || sortedHand.get(position).value == Card.ACE){
        			return true;
        		}
        		// other wise it is less than Jacks and this game is JACKS OR BETTER
        		return false;
        	}	
        }
		// should not get here ..
		return false;
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
		System.out.println("");
		h.printHand();
		
	}

}
