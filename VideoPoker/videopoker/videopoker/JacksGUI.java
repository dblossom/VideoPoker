/**
 * A class that be the GUI for the video poker game
 * It will for better or worse, attempt to look like
 * the traditional video poker screen seen in most Casinos.
 * 
 * @author Dan Blossom
 */

/**
 * TODO LIST:
 * 
 * 1) Create a default font
 * 2) Create "bet" word and a variable for bet amount
 * 3) Create "deal/draw" button
 * 4) Download/Find new cards that will not look pixelated
 * 5) Make some simple buttons for Draw, Deal, etc...
 * 6) Layout cards after user clicks deal / change text to say draw ...
 * 7) For 6 let's just make a deal boolean, call same method.
 * 8)
 * ...
 * 20) REFACTOR THIS MESS!
 */

package videopoker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JacksGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	/**
	 * The size of the frame
	 */
	private final int WIDTH = 700;
	private final int HEIGHT = 600;
	
	// Is this a new deal
	private boolean deal = true;
	
	// is this the first deal
	private boolean firstGame = true;
	
	// our hand
	private Hand hand;
	
	// held array
	private boolean[] held = {false, false, false ,false, false};
	
	// our hand ranking
	private String handRank = "";
	
	// the bet
	private Bet bet = new Bet();
	
	// players bankroll
	private Bankroll bankroll = new Bankroll(new Dollar(), Denomination.QUARTER);
	
	// display credits or dollars
	private boolean credits = true;
	
	// The default font
//	private final Font DEFAULT_FONT; //TODO
	
	/**
	 * The default constructor for this frame
	 */
	public JacksGUI(){
		super();
	}
	
	/**
	 * The main method to get us started - calls init();
	 * @param args
	 */
	public static void main(String[] args){
		new JacksGUI().init();
	}
	
	/**
	 * Method used for initalizing this frame
	 */
	private void init(){
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setResizable(false);
		this.setTitle("Jacks or better");
		this.setVisible(true);
		this.mouseIntegration();
	}
	
	/**
	 * Stuff painted to the frame
	 */
	public void paint(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, this.WIDTH, this.HEIGHT);
		payout(g);
		handLabel(g);
		betLabel(g);
		if(this.firstGame){
			loadBacks(g);
		}else{
			loadBacks(g);
			this.cards(g, this.hand);
		}
		dealButton(g);
		maxButton(g);
		betButton(g);
		if(!deal){
		    paintHeld(g);
		}
		credit(g);
	}
	
	private void handLabel(Graphics g){
		//System.out.println(this.hand.isJacksOrHigher());
		if(!firstGame){
		    this.handRank = Rank.rank(this.hand).toString();
		    if(this.handRank.equalsIgnoreCase("PAIR")){
		    	if(this.hand.isJacksOrHigher()){
		    		this.handRank = "JACKS OR BETTER";	
		    	}else{
		    		this.handRank = "";
		    	}
		    }
		}
		g.setFont(new Font("default", Font.BOLD, 20));
		g.setColor(Color.RED);
		g.drawString(this.handRank, (this.WIDTH / 2) - 100, 300);
		System.out.println(this.handRank);
	}
	
	private void betLabel(Graphics g){
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.WHITE);
		g.drawString("Bet " + this.bet.getBet(), (this.WIDTH / 2) - 35, 515);
	}
	
	private void payout(Graphics g){
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.YELLOW);
		// top line
		g.drawLine(20, 50, this.WIDTH - 20, 50);
		// bottom line
		g.drawLine(20, 250, this.WIDTH - 20, 250);
		// left side
		g.drawLine(20, 50, 20, 250);
		//right side
		g.drawLine(this.WIDTH - 20, 50, this.WIDTH - 20, 250);
		
		//the text stuff ... 
		int y = 67;
		int x = 25;
		int gap = 20;
		
		g.drawString("ROYAL FLUSH" + padDots(17),x ,y);
		g.drawString("STRAIGHT FLUSH" + padDots(12),x ,y+gap);
		g.drawString("FOUR OF A KIND" + padDots(13),x ,y+(gap*2));
		g.drawString("FULL HOUSE" + padDots(19),x ,y+(gap*3));
		g.drawString("FLUSH" + padDots(27),x ,y+(gap*4));
		g.drawString("STRAIGHT" + padDots(22),x ,y+(gap*5));
		g.drawString("THREE OF A KIND" + padDots(11),x ,y+(gap*6));
		g.drawString("TWO PAIR" + padDots(21),x ,y+(gap*7));
		g.drawString("JACKS OR BETTER" + padDots(10),x ,y+(gap*8));
	}
	
	private String padDots(int numDots){
		StringBuilder sb = new StringBuilder();	
		while(numDots > 0){
			sb.append(".");
			numDots--;
		}	
		return sb.toString();
	}
	
	/**
	 * Lays out the cards
	 * @throws IOException 
	 */
	private void cards(Graphics g, Hand hand) {
		
		int cWidth = 120;
		int cHeight = 170;
		int yLoc = 325;
        // (135*n)+20
		int xLoc[] = {20, 155, 290, 425, 560};
		try {
			// wanted to do a for-each but then I'd still need a counter for the xLoc array.
			for(int i = 0; i < hand.hand.length; i++){
			//	Thread.sleep(1000);
				Card card = hand.hand[i];
			    String location = "/images/cards/front/" + card.valueToString() + card.suitToChar() + ".png";	
				Image img = ImageIO.read(getClass().getResource(location));
				g.drawImage(img, xLoc[i], yLoc, cWidth, cHeight, null);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}// catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
	}
	
	private void credit(Graphics g){
	    g.setFont(new Font("default", Font.BOLD, 16));
	    g.setColor(Color.RED);
	    if(credits){
	        g.drawString("CREDITS:    " + bankroll.convertToCredit(), this.WIDTH - 200, 515);
	    }else{
	    	g.drawString("CASH:    " + bankroll.getDollar().getAmount(), this.WIDTH - 200, 515);
	    }
	}
	
	private void loadBacks(Graphics g){
		int cWidth = 120;
		int cHeight = 170;
		int yLoc = 325;
        // (135*n)+20
		int xLoc[] = {20, 155, 290, 425, 560};
		
		try {
			// wanted to do a for-each but then I'd still need a counter for the xLoc array.
			for(int i = 0; i < 5; i++){
			    String location = "/images/cards/back/b1fv.png";	
				Image img = ImageIO.read(getClass().getResource(location));
				g.drawImage(img, xLoc[i], yLoc, cWidth, cHeight, null);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dealButton(Graphics g){
		g.setFont(new Font("default", Font.BOLD, 12));
		// attempt a custom yeller....
		g.setColor(new Color(255,255,102));
		g.fill3DRect(this.HEIGHT-10, this.HEIGHT-35, 100, 25, true);
		// wanted white but it was hard to see.
		g.setColor(Color.BLACK);
		if(deal){
			g.drawString("DEAL", this.HEIGHT+20, this.HEIGHT-18);
		}else{
			g.drawString("DRAW", this.HEIGHT+20, this.HEIGHT-18);
		}
		
	}
	
	private void maxButton(Graphics g){
		g.setFont(new Font("default", Font.BOLD, 12));
		// attempt a custom yeller....
		g.setColor(new Color(255,255,102));
		g.fill3DRect(this.HEIGHT-118, this.HEIGHT-35, 100, 25, true);
		// wanted white but it was hard to see.
		g.setColor(Color.BLACK);
		if(deal){
			g.drawString("BET MAX", this.HEIGHT-98, this.HEIGHT-18);
		}else{
			g.drawString("", 0, 0);
		}
	}
	
	private void betButton(Graphics g){
		g.setFont(new Font("default", Font.BOLD, 12));
		// attempt a custom yeller....
		g.setColor(new Color(255,255,102));
		g.fill3DRect(this.HEIGHT-225, this.HEIGHT-35, 100, 25, true);
		// wanted white but it was hard to see.
		g.setColor(Color.BLACK);
		if(deal){
			g.drawString("BET 1", this.HEIGHT-190, this.HEIGHT-18);
		}
	}
	
	private void dealClicked(){
		if(deal){
			deal = false;
			firstGame = false;
			this.hand = new Hand();
			hand.deal();
			held = new boolean[] {false, false, false, false, false};
			bankroll.getDollar().subtract(bankroll.getDenomination().getDouble() * bet.getBet());
			this.handRank = "";
		}else{
		    deal = true;
		    this.draw(this.hand, held);
		    if(Payout.payout(this.hand, this.bet.getBet()) != -1){
		    	bankroll.win(this.hand, this.bet);
		    }
		}
		repaint();
	}
	
	private void payoutClicked(){
		this.credits = !credits;
		repaint();
	}
	
	private void betOneClicked(){
		if(deal){
		    if(this.bet.getBet() == 5){
			    this.bet.setBet(1);
		    }else{
			    this.bet.increaseBet(1);
		    }
		    repaint();
		}
	}
	
	private void betMaxClicked(){
		if(deal){
		    this.bet.setBet(bet.MAX_BET);
		    repaint();
		    this.dealClicked();
		}
	}
	
	private void draw(Hand hand, boolean[] held){
		for(int i = 0; i < held.length; i++){
			if(held[i]==false){
				hand.newCard(i);
			}
		}
	}

	private void mouseIntegration(){
        JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
		     public void mousePressed(MouseEvent e) {
		    	 if((e.getX() > 587 && e.getX() < 684) && (e.getY() > 548 && e.getY() < 570)){
		    		 System.out.println("dealClicked()");
		    		 dealClicked();
		    		 return;
		    	 }else if(isCardButton(e.getX(), e.getY()) != -1){
		    		 held[(isCardButton(e.getX(), e.getY())) - 1] = !held[(isCardButton(e.getX(), e.getY())) - 1];
		    		 System.out.println("card " + isCardButton(e.getX(), e.getY()) + " clicked");
                     repaint();
		    	 }else if((e.getX() > 370 && e.getY() > 547) && (e.getX() < 469 && e.getY() < 570)){
		    		 System.out.println("betOneClicked()");
		    		 betOneClicked();
		    	 }else if((e.getX() > 478 && e.getY() > 547) && (e.getX() < 577 && e.getY() < 570)){
		    		 System.out.println("betMaxClicked()");
		    		 betMaxClicked();
		    	 }else if((e.getX() > 500 && e.getY() > 480) && (e.getX() < 605 && e.getY() < 495)){
		    		 payoutClicked();
		    	 }else{
		    		 JOptionPane.showMessageDialog(null,  "x="+e.getX() +"\n" +"y="+e.getY());	 
		    	 } 
		     }
		  });
		this.add(panel);
	}
	
	private int isCardButton(int x, int y){
		
		final int yStart = 308;
		final int yEnd = 476;
		
		// card 1 location:
		if((x > 19 && y > yStart) && (x < 135 && y < yEnd)){
			return 1;
		}
		
		// card 2 location:
		if((x > 153 && y > yStart) && (x < 270 && y < yEnd)){
			return 2;
		}
		
		// card 3 location
		if((x > 287 && y > yStart) && (x < 403 && y < yEnd)){
			return 3;
		}
		
		// card 4 location
		if((x > 422 && y > yStart) && (x < 539 && y < yEnd)){
			return 4;
		}
		
		// card 5 location
		if((x > 558 && y > yStart) && (x < 675 && y < yEnd)){
			return 5;
		}
		return -1;
	}
	
	private void paintHeld(Graphics g){
		String heldString = "HELD";		
		g.setFont(new Font("default", Font.BOLD, 18));
		g.setColor(Color.RED);
		// 20, 155, 209, 560
		int xLoc[] = {55, 190, 325, 460, 590};
		int yLoc = 320;
		for(int i = 0; i < this.held.length; i++){
			if(held[i]==true){
		  	    g.drawString(heldString,xLoc[i],yLoc);
			}
		}

	}
}