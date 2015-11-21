package videopoker;

import videopoker.Rank.ranks;

public class Payout {
	
	private Payout(){}
	
	private static int[] jacks = {1,2,3,4,5};
	private static int[] two = {2,4,6,8,10};
	private static int[] three = {3,6,9,12,15};
	private static int[] straight = {4,8,12,16,20};
	private static int[] flush = {6,12,18,24,30};
	private static int[] full = {9,18,29,36,45};
    private static int[] four = {25,50,75,100,125};
	private static int[] straight_flush = {50,100,150,200,250};
	private static int[] royal = {250,500,750,1000,4000};
	
	/**
	 * TODO: implement a proper "bet" 1-5
	 * @param hand
	 * @param bet
	 * @return
	 */
	public static int payout(Hand hand, int bet){
        // there must be a better way!
		if(Rank.rank(hand) == Rank.ranks.ROYAL){
        	return royal[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.STRAIGHT_FLUSH){
        	return straight_flush[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.FOUR){
        	return four[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.FULL){
        	return full[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.FLUSH){
        	return flush[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.STRAIGHT){
        	return straight[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.THREE){
        	return three[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.TWO){
        	return two[bet];
        }
        if(Rank.rank(hand) == Rank.ranks.PAIR){
        	if(hand.isJacksOrHigher()){
        		return jacks[bet];	
        	}else{
        		return -1;
        	}
        }
        
        return -1;
	}

}
