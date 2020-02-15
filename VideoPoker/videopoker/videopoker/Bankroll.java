package videopoker;

/**
 * A wrapper class that will encompasss both credit and dollar ..
 * I suppose I could just do some conversion in a money class
 * that goes from credits to dollars but whatever ... this seems
 * a bit more abstaract. 
 * can I add this line?
 * 
 * @author Dan Blossom
 *
 */
public class Bankroll implements Credit{
	
	private Dollar dollar;
	private Denomination denomination;
	
	public Bankroll(Dollar dollar, Denomination denomination){
		this.dollar = dollar;
		this.denomination = denomination;
	}
	
	public void setBetDenom(int i){

	}
	
	public void win(Hand hand, Bet bet){
		// Hard to read / understand ?
		// the winning dollar amount will be determine based upon
		// 1) the denomination (the bet amount, quarter, nickel, dollar, etc)
		// 2) the bet size (1-5) which actually correlate to a payout amount defined in payout
		// so: bet gives us the number of denominations to returns, and that arrives at
		//     denomination X bet (which correlates to a payout amount).
		this.dollar.add((Payout.payout(hand, bet.getBet())) * this.denomination.getDouble());
	}
	
	public void lose(){
		
	}
	
	public Dollar getDollar(){
		return this.dollar;
	}
	
	public Denomination getDenomination(){
		return this.denomination;
	}

	@Override
	public int convertToCredit() {
	    return (int) (dollar.getAmount() / denomination.getDouble());
	}
}
