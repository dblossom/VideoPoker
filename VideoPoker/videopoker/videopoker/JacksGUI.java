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
	
	/**
	 * 
	 */
	
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
		
		// JUST FOR TESTING
		cards(g);
	}
	
	/**
	 * Payout printout
	 */
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
	private void cards(Graphics g) {
		
		String location = "/images/cards/back/b1fv.png";
		String location1 = "/images/cards/front/10H.png";
		Image img;
		Image img1;
		int cWidth = 120;
		int cHeight = 170;
		int yLoc = 325;
		int xLoc = 20;
		int xLocGap = 135;
		
		try {
			img = ImageIO.read(getClass().getResource(location));
			img1 = ImageIO.read(getClass().getResource(location1));
			//g.drawImage(img1, 25, 300, null);
			g.drawImage(img, xLoc, yLoc, 120, 170, null);
			g.drawImage(img, xLoc+xLocGap, yLoc, 120, 170, null);
			g.drawImage(img, xLoc+(xLocGap*2), yLoc, 120, 170, null);
			g.drawImage(img, xLoc+(xLocGap*3), yLoc, 120, 170, null);
			g.drawImage(img1, xLoc+(xLocGap*4), yLoc, 120, 170, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
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
	 * The buttons on the bottom
	 */
	private void buttons(Graphics g){
		
	}
	
	
	
	
	
	
	
	/**
	 * The main method to get us started - calls init();
	 * @param args
	 */
	public static void main(String[] args){
		new JacksGUI().init();
	}
}
