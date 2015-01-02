package videopoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * A class to determine a hands ranks...
 * Static methods are used to determine a hands rank.
 * 
 * @author Dan Blossom
 *
 */
public class Rank {
		
	/**
	 * Prevent default constructor
	 */
	private Rank(){}
	
	/**
	 * Method used to sort a hand
	 * 
	 * @param hand the hand to be sorted
	 * @return the sorted hand
	 */
	static ArrayList<Card> sort(Hand hand){
		
    	ArrayList<Card> unsortedhand = new ArrayList<>(Arrays.asList(hand.hand));
		
		Collections.sort(unsortedhand, new Comparator<Card>(){
			
			@Override
			public int compare(Card one, Card two){
				if(one.value < two.value){
					return -1;
				}
				if(one.value > two.value){
					return 1;
				}
				return 0;
			}
		});
		return unsortedhand;
	}
    
	/**
	 * Main method used for testing
	 * @param args
	 */
	public static void main(String[] args){
    	Hand h = new Hand();
    	h.hand[0] = new Card(Suit.CLUB, 5);
    	h.hand[1] = new Card(Suit.CLUB, 2);
    	h.hand[2] = new Card(Suit.DIAMOND, 5);
    	h.hand[3] = new Card(Suit.SPADE, Card.ACE);
    	h.hand[4] = new Card(Suit.HEART, 5);
    	
    	System.out.println(Rank.isPair(h));
    	System.out.println(Rank.isTwoPair(h));
    	System.out.println(Rank.isThreeKind(h));
    	h.printHand();
    }

	/**
	 * Returns if a hand contains a pair
	 * 
	 * @param hand the hand to be tested
	 * @return if the hand contains a pair
	 */
    public static boolean isPair(Hand hand){
    	
    	// first we need to sort the hand for easy lookup
    	ArrayList<Card> sortedHand = Rank.sort(hand);
    	
    	// the starting position (first card) to check
        int position = 0;
        
        // we only need n-1 checks, or for a poker hand 4 checks (0 - 3)
        while(position < 4){
        	// if the current and the next match, we have a pair...
        	if(sortedHand.get(position).value == sortedHand.get(++position).value){
        		return true;
        	}	
        }
        // if we do not return true then there is no pair...
        return false;
    }
    
    /**
     * Returns if a hand contains 2 pairs
     * 
     * @param hand the hand to be tested
     * @return if the hand contains 2 pairs
     */
    public static boolean isTwoPair(Hand hand){
    	
    	// if there is not even 1 pair, why test for 2
    	if(!Rank.isPair(hand)){
    		return false;
    	}
    	
    	// sort the hand
    	ArrayList<Card> sortedHand = Rank.sort(hand);
    	
    	// used to ensure we do not say "true" when we might have only found the known 1 pair
    	boolean passTwo = false;
    	
    	// the starting position to start testing
    	int position = 0;
    	// we only need to test elements 0 - 3 since card 5 will be tested on last round
    	while(position < 4){
    		if(sortedHand.get(position).value == sortedHand.get(++position).value){
    			// we have found the first pair so let us keep checking
    			if(!passTwo)
    			    passTwo = true;
    			// well, well, we found another pair!
    			else
    				return true;
    		}
    	}
    	// only one pair seems to exist.
    	return false;
    }

    /**
     * Returns if a hand contains 3 of a kind
     * 
     * @param hand the hand to be tested
     * @return if the hand contains 3 of a kind
     */
    public static boolean isThreeKind(Hand hand){
    	
    	// if there is not a pair, there is not 3 of a kind
    	if(!isPair(hand)){
    		return false;
    	}
    	
    	// the sorted hand
    	ArrayList<Card> sortedhand = Rank.sort(hand);
    	// starting position in the hand to check
    	int position = 0;
    	// only need to check 3 places since the hand is sorted (0,1,2 / 1,2,3 / 2,3,4)
    	while(position < 3){
            if(sortedhand.get(position).value == sortedhand.get(position + 1).value && sortedhand.get(position + 1).value == sortedhand.get(position + 2).value){
            	return true;
            }
            position++;
    	}
    	return false;
    }
}