package videopoker;

public class Dollar {

	private double dollar = 0.0;
	
	public Dollar(){
		this(100.);
	}
	
	public Dollar(double dollar){
		this.dollar = dollar;
	}
	
	public void add(double d){
		this.dollar =+ d;
	}
	
	public void subtract(double d){
		this.dollar =- d;
	}
	
	public double getAmount(){
		return this.dollar;
	}
}