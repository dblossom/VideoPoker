package videopoker;

import java.util.Scanner;

public class JacksOrBetter {
	
	private JacksOrBetter(){ }
	
	public static void go(){
		
		Hand hand = new Hand();
		hand.deal();
		hand.printHand();
		System.out.println();
		System.out.println("You have: " + Rank.rank(hand));
		System.out.print("How many cards would you like to change? ");
		Scanner scan = new Scanner(System.in);
		int swapAmount = scan.nextInt();
		
		if(swapAmount > 0){
			System.out.print("Which cards would you like to swap? ");
			int cards = scan.nextInt();
			int elements[] = JacksOrBetter.makeToSwapArray(cards, swapAmount);
			for(int i: elements){
				hand.newCard(i-1);
			}
		}
		scan.close();
		
		System.out.println("Now you have: " + Rank.rank(hand));
		System.out.println();
		hand.printHand();
	}
	
	private static int[] makeToSwapArray(int i, int s){
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
