package videopoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Rank {
		
	private Rank(){}
	
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
    public static void main(String[] args){
    	Hand h = new Hand();
    	h.deal();
    	
    	System.out.println(Rank.isPair(h));
    	System.out.println(Rank.isTwoPair(h));
    	h.printHand();
    }
    
    public static boolean isPair(Hand hand){
    	
    	ArrayList<Card> sortedHand = Rank.sort(hand);
    	
        int position = 0;
        while(position < 4){
        	if(sortedHand.get(position).value == sortedHand.get(++position).value){
        		return true;
        	}	
        }
        return false;
    }
    
    public static boolean isTwoPair(Hand hand){
    	if(!Rank.isPair(hand)){
    		return false;
    	}
    	boolean passTwo = false;
    	
    	return true;
    }
}