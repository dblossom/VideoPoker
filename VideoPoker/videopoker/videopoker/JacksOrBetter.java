package videopoker;

import java.util.Scanner;

public class JacksOrBetter {
	
	private JacksOrBetter(){ }
	
	public static void go(){
		
		Scanner scan = new Scanner(System.in);
		while(true){
		    
			Hand hand = new Hand();
		    hand.deal();
		    hand.printHand();
		    System.out.println();
		    System.out.println("You have: " + Rank.rank(hand));
		    System.out.print("Which cards would you like to hold (enter 0 for none): ");
		    
		    int cardsToHold = scan.nextInt();

		    if(cardsToHold == 0){
			    for(int i = 0; i < 5; i++){
				    hand.newCard(i);
			    }
		    }else{
			    int heldCards[] = JacksOrBetter.toHoldArray(cardsToHold);
			    for(int i = 0; i < 5; i++){
				    boolean held = false;
				    for(int e: heldCards){
					    if(e==i+1){
						    held = true;
						    break;
					    }
				    }
				    if(!held){
					    hand.newCard(i);
				    }
			    }
			
		    }
		    System.out.println("Now you have: " + Rank.rank(hand));
		    hand.printHand();
		    System.out.println();
		    System.out.println("--------------------------------");
		}
		//scan.close();
	}
	
	private static int[] toHoldArray(int i){
		int s = (int)(Math.log10(i)+1);
		int[] returnArray = new int[s];
		while(i > 0){
			returnArray[--s] = i % 10;
			i = i / 10;
		}
		return returnArray;
	}
	
	public static void main(String[] args){
		JacksOrBetter.go();
	}

}
