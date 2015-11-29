package videopoker;

public class Bet {
	
	final int MIN_BET = 1;
	final int MAX_BET = 5;
	private int bet = MIN_BET;
	
	/**
	 * Default constructor, nothing to do without
	 * setting bet here to MIN_BET
	 */
	public Bet(){}
	
	/**
	 * Construct a Bet with a specific bet amount
	 * @param inc
	 */
	public Bet(int bet){
		this.increaseBet(bet);
	}
		
	public void increaseBet(int inc){
		if((this.bet + inc) > this.MAX_BET){
			this.bet = this.MAX_BET;
		}else{
			this.bet += inc;
		}
	}
	
	public void decreaseBet(int dec){
		if((this.bet - dec) < this.MIN_BET){
			this.bet = this.MIN_BET;
		}else{
			this.bet -= dec;
		}
	}
	
	public void setBet(int bet){
		if(bet >= this.MIN_BET && bet <= this.MAX_BET){
			this.bet = bet;
		}else{
			this.bet = this.MIN_BET;
		}
	}

	public int getBet(){
		return this.bet;
	}
}