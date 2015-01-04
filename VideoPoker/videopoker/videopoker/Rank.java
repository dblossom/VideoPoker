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
	
	// What we are returning, the "rank" of the hand...
	// TODO: Not too excited about all the "rank" names...
	enum ranks { HIGH, PAIR, TWO, THREE, STRAIGHT, FLUSH, FULL, FOUR, STRAIGHT_FLUSH, ROYAL }
	
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
	
	public static ranks rank(Hand hand){
	
		// so working backwards to find the best hand possible
		if(Rank.isRoyal(hand)){
			return ranks.ROYAL;
		}
		
		if(Rank.isStraightFlush(hand)){
			return ranks.STRAIGHT_FLUSH;
		}
		
		if(Rank.isFour(hand)){
			return ranks.FOUR;
		}
		
		if(Rank.isFull(hand)){
			return ranks.FULL;
		}
		
		if(Rank.isFlush(hand)){
			return ranks.FLUSH;
		}
		
		if(Rank.isStraight(hand)){
			return ranks.STRAIGHT;
		}
		
		if(Rank.isThreeKind(hand)){
			return ranks.THREE;
		}
		
		if(Rank.isTwoPair(hand)){
			return ranks.TWO;
		}
		
		if(Rank.isPair(hand)){
			return ranks.PAIR;
		}
		
		return ranks.HIGH;	
	}
    
	/**
	 * Main method used for testing
	 * @param args
	 */
	public static void main(String[] args){
    	Hand h = new Hand();
//    	h.hand[0] = new Card(Suit.CLUB, Card.KING);
//    	h.hand[1] = new Card(Suit.SPADE, Card.KING);
//    	h.hand[2] = new Card(Suit.CLUB, 10);
//    	h.hand[3] = new Card(Suit.CLUB, Card.KING);
//    	h.hand[4] = new Card(Suit.CLUB, Card.KING);
    	
    	h.deal();
    	
    	ranks r = Rank.rank(h);
    	
    	System.out.println(r);
    	
    	h.printHand();
    }

	/**
	 * Returns if a hand contains a pair
	 * 
	 * @param hand the hand to be tested
	 * @return if the hand contains a pair
	 */
    private static boolean isPair(Hand hand){
    	
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
    private static boolean isTwoPair(Hand hand){
    	
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
    private static boolean isThreeKind(Hand hand){
    	
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
    
    /**
     * Returns if a hand contains a straight
     * 
     * @param hand the hand to be tested
     * @return if the hand contains a straight
     */
    private static boolean isStraight(Hand hand){
    	// a sorted hand to test
    	ArrayList<Card> sortedhand = Rank.sort(hand);
    	
    	// Ace needs to be handled differently...
    	// either ACE, 2, 3, 4, 5 or 10, J, Q, K, A
    	if(sortedhand.get(0).value == Card.ACE && sortedhand.get(4).value == Card.KING){
    		if(sortedhand.get(1).value == 10 && sortedhand.get(2).value == Card.JACK && sortedhand.get(3).value == Card.QUEEN){
    		    // well we should have the straight 10, J, Q, K, A... The below check will catch Ace as a 1 for A, 2, 3, 4, 5
    			return true;
    		}
    	}
    	
    	// starting position in the hand to start with
    	int position = 0;
    	// so basically a card should be 1 number off from the one before it
    	while(position < 4){
    	    if(!((sortedhand.get(position + 1).value - sortedhand.get(position).value) == 1)){
    	    	// if at any point we are not off by 1 then cards are not consecutive and no straight exists
    	    	return false;
    	    }
    	    position++;
    	}
    	// if we get this far, we must have a straight.
    	return true;
    }

    /**
     * Returns if a hand contains a flush
     * 
     * @param hand the hand to be tested
     * @return if the hand contains a flush
     */
    private static boolean isFlush(Hand hand){
    	// the hand to test needs to be sorted
    	ArrayList<Card> sortedhand = Rank.sort(hand);
    	// starting position to test
    	int position = 0;
    	// make sure the next suit is the same as the current suit
    	while(position < 4){
    		if(sortedhand.get(position).suit != sortedhand.get(++position).suit){
    			// these two do not match so we are not a flush
    			return false;
    		}
    	}
    	// if we got this far then we are a flush
    	return true;
    }
    
    /**
     * Returns if a hand contains a full house
     * 
     * @param hand the hand to be tested
     * @return if the hand contains a full house
     */
    private static boolean isFull(Hand hand){
    	
    	// if there is not a three of a kind there can be no full house
    	if(!Rank.isThreeKind(hand)){
    		return false;
    	}
    	
    	ArrayList<Card> sortedhand = Rank.sort(hand);
    	
    	// so we have two situations to look for ...
    	// AABBB or BBBAA, assuming and since the list is sorted
    	//-------------------------------------------------------
    	// so is the three of a kind in locations 2, 3, 4
    	if(sortedhand.get(2).value == sortedhand.get(4).value){
    		if(sortedhand.get(0).value == sortedhand.get(1).value){
    			return true;
    		}
    		return false;
    	}
    	
    	// so is the three of a kind in locations 0, 1, 2
    	if(sortedhand.get(0).value == sortedhand.get(2).value){
    		if(sortedhand.get(3).value == sortedhand.get(4).value){
    			return true;
    		}
    		return false;
    	}
    	// should not get here, but if we do we do not have a full boat
    	return false;
    }
    
    /**
     * Returns if a hand contains 4 of a kind
     * 
     * @param hand the hand to be tested
     * @return if the hand contains a 4 of a kind
     */
    private static boolean isFour(Hand hand){
    
    	// start off with .. no pair, no four... 
    	if(!Rank.isPair(hand)){
    		return false;
    	}
    	
    	// sorted hand will make this easier.
    	ArrayList<Card> sortedhand = Rank.sort(hand);
    	
    	// given a sorted hand a 4 of a kind will be present when...
    	// AAAAX or XAAAA so given that info we can check first and last
    	if(sortedhand.get(0).value == sortedhand.get(3).value){
    		return true;
    	}
    	if(sortedhand.get(1).value == sortedhand.get(4).value){
    		return true;
    	}
    	
    	// so we shouldn't have 4 of a kind
    	return false;
    }

    /**
     * Returns if a hand contains a straight flush
     * 
     * @param hand the hand to be tested
     * @return if the hand contains a straight flush
     */
    private static boolean isStraightFlush(Hand hand){
    	// well, if we are a straight AND a flush, we are a straight flush!
    	return (Rank.isStraight(hand) && Rank.isFlush(hand));
    }

    /**
     * Returns if a hand contains a Royal flush
     * 
     * @param hand the hand to be tested
     * @return if the hand contains a royal flush
     */
    private static boolean isRoyal(Hand hand){
        // a royal flush IS a straight flush, just higher...
    	if(isStraightFlush(hand)){
    		ArrayList<Card> sortedhand = Rank.sort(hand);
    		// the order here should be 1, 10, 11, 12, 13
    		// I guess 4 tests? must be a better way
    		if(sortedhand.get(0).value != Card.ACE)
    			return false;
    		if(sortedhand.get(1).value != 10)
    			return false;
    		if(sortedhand.get(2).value != Card.JACK)
    			return false;
    		if(sortedhand.get(3).value != Card.QUEEN)
    			return false;
    		if(sortedhand.get(4).value != Card.KING)
    			return false;
    		
    		// well, we made it past all those checks
    		return true;
    	}
    	// we are not even a straight flush?
    	return false;
    }

}