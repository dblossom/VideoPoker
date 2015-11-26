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
 */

package videopoker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class JacksGUI extends JFrame{
	
	/**
	 * The size of the frame
	 */
	private final int WIDTH = 700;
	private final int HEIGHT = 600;
	
	// A bet amount
	private int betAmount = 0;
	
	// The default font
//	private final Font DEFAULT_FONT; //TODO
	
	/**
	 * The default constructor for this frame
	 */
	public JacksGUI(){
		super();
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
		// this should be in init() but init doesn;t have Graphics parameter...
		loadBacks(g);
	}
	
	private void handLabel(Graphics g){
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.RED);
		// this is just for testing....
		g.drawString("JACKS OR BETTER", (this.WIDTH / 2) - 125, 280);
		
	}
	
	private void betLabel(Graphics g){
		g.setFont(new Font("default", Font.BOLD, 16));
		g.setColor(Color.WHITE);
		g.drawString("Bet " + this.betAmount, (this.WIDTH / 2) - 35, 515);
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
				Card card = hand.hand[i];
			    String location = "/images/cards/front/" + card.valueToString() + card.suitToChar() + ".png";	
				Image img = ImageIO.read(getClass().getResource(location));
				g.drawImage(img, xLoc[i], yLoc, cWidth, cHeight, null);	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used for printing the backs
	 */
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
	
	/**
	 * The buttons on the bottom
	 */
	private void buttons(Graphics g){
		
	}
	
	/**
	 * Method used for initalizing this frame
	 */
	private void init(){
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setResizable(false);
		this.setTitle("Jacks or better");
		this.setVisible(true);
	}
	
	/**
	 * The main method to get us started - calls init();
	 * @param args
	 */
	public static void main(String[] args){
		new JacksGUI().init();
	}
}
