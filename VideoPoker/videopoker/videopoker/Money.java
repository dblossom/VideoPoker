package videopoker;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A class to keep track of the money won or lost
 * 
 * @author Dan Blossom
 *
 */
public class Money {
	
	// keeps track of our dollar amount
	private double amount;
	
	/**
	 * Default constructor starts your bankroll with zero
	 */
	public Money(){
		amount = 0;
	}
	
	/**
	 * Allows the caller to set their own starting bankroll
	 * @param amount the amount to start with
	 */
	public Money(double amount){
		this.amount = amount; 
	}
	
	/**
	 * The amount lost
	 * @param amt amount to deduct from bankroll
	 */
	void lose(double amt){
		amount = amount - amt; 
	}
	
	/**
	 * The amount won
	 * @param amt the amount to add to a bankroll
	 */
	void win(double amt) {
		amount = amount + amt; 
	}
	
	/**
	 * Displays the amount in bankroll utilizing the
	 * NumberFormat api to ensure we get ".XX" at the end
	 * TODO: add currency locale rather than "$" + format()
	 * 
	 * @return decimal converted to string in the format $x.xx
	 */
	String cash(){
		NumberFormat f = new DecimalFormat("#0.00");
		return "$" + f.format(amount);
	}
	
	/**
	 * For testing, like the other classes need to remove all these eventually.
	 * @param args
	 */
	public static void main(String[] args){
		Money m = new Money(45.50);
		m.lose(.50);
		m.win(34);
		//m.win(.50);
		System.out.println(m.cash());
	}
}
