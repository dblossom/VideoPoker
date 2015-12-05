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
	private double denomination;
	
	public Bankroll(Dollar dollar, double denomination){
		this.dollar = dollar;
		setDenom(denomination);
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
	
	public void setDenom(double d){
		denomination = d;
	}

	@Override
	public int convertToCredit() {
	    return (int) (dollar.getAmount() / denomination);
	}
	

}