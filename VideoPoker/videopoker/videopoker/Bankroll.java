package videopoker;

/**
 * A wrapper class that will encompasss both credit and dollar ..
 * I suppose I could just do some conversion in a money class
 * that goes from credits to dollars but whatever ... this seems
 * a bit more abstaract. 
 * 
 * @author Dan Blossom
 *
 */
public class Bankroll {
	
	private Credit credit;
	private Dollar dollar;
	private final double[] DENOM = {.5, .25, .50, 1.};
	private final int MIN_BET = 1;
	private final int MAX_BET = 5;
	private int bet = MIN_BET;
	private int betDenom;
	
	public Bankroll(Credit credit, Dollar dollar){
		this.credit = credit;
		this.dollar = dollar;
	}
	
	public void increaseBet(int inc){
		if((this.bet + inc) > this.MAX_BET){
			this.bet = this.MAX_BET;
		}else{
			this.bet =+ inc;
		}
	}
	
	public void decreaseBet(int dec){
		if((this.bet - dec) < this.MIN_BET){
			this.bet = this.MIN_BET;
		}else{
			this.bet =- dec;
		}
	}
	
	public void setBetDenom(int i){

	}
	
	public void win(){
		
	}
	
	public void lose(){
		
	}
	
	public Dollar getDollar(){
		return this.dollar;
	}
	
	public Credit getCredit(){
		return this.credit;
	}
	
	public int getBet(){
		return this.bet;
	}
}