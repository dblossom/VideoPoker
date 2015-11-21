package videopoker;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class JacksGUI extends JFrame{
	
	/**
	 * The size of the frame
	 */
	private final int WIDTH = 700;
	private final int HEIGHT = 500;
	
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
	}
	
	/**
	 * Method used for initalizing this frame
	 */
	private void init(){
		this.setSize(this.WIDTH, this.HEIGHT);
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
