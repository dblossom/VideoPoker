package videopoker;

public class Card {
	
	/**
	 * variables needed to represent a face card
	 */
	final static int ACE = 1;
	final static int JACK = 11;
	final static int QUEEN = 12;
	final static int KING = 13;
	
	/**
	 * variables needed to represent this cards value
	 */
	final int value;
	final Suit suit;
	
	/**
	 * Construct a card given a suit and value
	 * @param suit - the suit of the card
	 * @param value - the value of the card
	 */
	public Card(Suit suit, int value){
        this.value = value;
	    this.suit = suit;
	}
	
	/**
	 * Constructor creates a card from a card (like a copy)
	 * 
	 * @param card - the card to "copy"
	 */
	public Card(Card card){
		this.value = card.value;
		this.suit = card.suit;
	}
	
	/**
	 * Convert the cards value to a string
	 * This will print 2-10 or A/J/Q/K
	 */
	String valueToString(){
		
		if(value < 11 && value > 1){
			return value + "";
		}
		
		if(value == Card.ACE){
			return "A";
		}
		
		if(value == Card.JACK){
			return "J";
		}
		
		if(value == Card.QUEEN){
			return "Q";
		}
		
		if(value == Card.KING){
			return "K";
		}
		
		return "?";
	}
	
	/**
	 * Convert the suit to a single letter for printing.
	 */
	String suitToChar(){
		
		String retStr = "";
		
		switch(suit){
		
		    case CLUB:
		    	retStr = "C";
		    	break;
		    
		    case DIAMOND:
		    	retStr = "D";
		    	break;
		    
		    case HEART:
		    	retStr = "H";
		    	break;
		    	
		    case SPADE:
		    	retStr = "S";
		    	break;
		    	
		    default:
		    	retStr = "?";
		    	break;
		}
		return retStr;
	}
}
