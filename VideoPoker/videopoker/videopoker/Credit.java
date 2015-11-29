package videopoker;

/**
 * A class to represent "credits"
 *  
 * @author Dan Blossom
 */

public class Credit {
	
	private int credit = 0;
	
	/**
	 * Default starts with 100 credits ? 
	 */
	public Credit(){
		this(100);
	}
	
	/**
	 * However many credits you want
	 * @param credit
	 */
	public Credit(int credit){
		this.credit = credit;
	}
	
	public int getCredits(){
		return this.credit;
	}
	
	public void addCredits(int c){
		this.credit =+ c;
	}
	
	public void subtractCredits(int c){
		this.credit =-c;
	}
}