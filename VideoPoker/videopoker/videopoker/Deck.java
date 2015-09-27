package videopoker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * This class represents a deck of cards of standard size (52 cards)
 * 
 * TODO: add support for an unshuffled deck.
 * 
 * @author Dan Blossom
 *
 */
public class Deck {
	
	/**
	 * The original deck
	 */
	List<Card> unshuffled;
	
	/**
	 * The deck after being shuffled
	 */
	Queue<Card> shuffled;
	
	/**
	 * Build a default deck of 52 cards and shuffle that deck
	 */
	public Deck(){
		unshuffled = new ArrayList<Card>();
		shuffled = new ArrayDeque<>();
		buildDeck();
		shuffle();
	}
	
	/**
	 * Builds a standard deck of 52 cards
	 */
	void buildDeck(){
		// for each suit
		for(Suit suit: Suit.values()){
		    // for each value of that suit
			int value = 1;
			for(int i = 0; i < 13; i++){
			    unshuffled.add(new Card(suit, value++));
			}
		}
	}
	
	/**
	 * Shuffles a deck
	 */
	void shuffle(){
		Random shuffle = new Random(new GregorianCalendar().getTimeInMillis());
		while(unshuffled.size() != 0){
			int next = shuffle.nextInt(unshuffled.size());
			shuffled.add(unshuffled.remove(next));
		}
	}
	
	/**
	 * Simple tests
	 * @param args
	 */
	public static void main(String[] args){
		Deck d = new Deck();
		
		for(Card card: d.shuffled){
			System.out.println(card.valueToString() + card.suitToChar());
		}
		
		System.out.println("");
	}
}
