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
public class Bankroll implements Credit{
	
	private Dollar dollar;
	private Denomination denomination;
	
	public Bankroll(Dollar dollar, Denomination denomination){
		this.dollar = dollar;
		this.denomination = denomination;
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

	@Override
	public int convertToCredit() {
	    return (int) (dollar.getAmount() / denomination.getDouble());
	}
	

}