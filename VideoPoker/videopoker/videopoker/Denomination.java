package videopoker;

public enum Denomination {
	
	NICKLE(.05),
	QUARTER(.25),
	FIFTY(.50),
	DOLLAR(1.);
	
	private double realValue;
	
	private Denomination(double d){
		this.realValue = d;
	}
	
	public double getDouble(){ return this.realValue; }

}