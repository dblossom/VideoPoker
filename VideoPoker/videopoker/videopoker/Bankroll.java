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

	
	public Bankroll(Credit credit, Dollar dollar){
		this.credit = credit;
		this.dollar = dollar;
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

}