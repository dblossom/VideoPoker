package videopoker;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JacksGUI extends JFrame {

	JButton quit = new JButton("Quit");
	JButton deal = new JButton("Deal");
	Image card1, card2, card3, card4, card5;
	JLabel cardLabel1, cardLabel2, cardLabel3, cardLabel4, cardLabel5;
	GridLayout cardLayout = new GridLayout(0,5);
    final JPanel cardArea = new JPanel();
	    
	public JacksGUI(String name) {
	    super(name);
	    setResizable(false);
	}
	    
	public void addComponentsToPane(final Container pane) {

	    cardArea.setLayout(cardLayout);
	    JPanel controls = new JPanel();
	    controls.setLayout(new GridLayout(0,5));
	    
	    JPanel actionButtons = new JPanel();
	    
	    actionButtons.setLayout(new GridLayout(0,2));
	    actionButtons.add(quit);
	    actionButtons.add(deal);
	    
	    dealButton(deal);
	    
	    //quit
	    quit.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
                System.exit(0);
	        }
	    });
	    
	    loadBacks();
	        
	    //Add controls to set up horizontal and vertical gaps
	    controls.add(new JButton("Hold"));
	    controls.add(new JButton("Hold"));
	    controls.add(new JButton("Hold"));
	    controls.add(new JButton("Hold"));
	    controls.add(new JButton("Hold"));
	        
	    //quit
	    quit.addActionListener(new ActionListener(){
	        public void actionPerformed(ActionEvent e){
                System.exit(0);
	        }
	    });
	    pane.add(cardArea, BorderLayout.NORTH);
	    pane.add(controls, BorderLayout.CENTER);
	    pane.add(actionButtons, BorderLayout.SOUTH);
	}
	    
	private static void createAndShowGUI() {
	    //Create and set up the window.
	    JacksGUI frame = new JacksGUI("Jacks or better");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //Set up the content pane.
	    frame.addComponentsToPane(frame.getContentPane());
	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	}
	
	private void dealButton(JButton button){
		button.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
                try {
                	Hand hand = new Hand();
                	hand.deal();
                	for(int i = 0; i < 5; i++){
                		Card card = hand.hand[i];
                		String location = "/images/cards/front/" + card.valueToString() + card.suitToChar() + ".png";
                		Image image = ImageIO.read(getClass().getResource(location));
                		
                		// have to use an array or something... but for now
                		if(i == 0)
                			cardLabel1.setIcon(new ImageIcon(image));
                		if(i == 1)
                			cardLabel2.setIcon(new ImageIcon(image));
                		if(i == 2)
                			cardLabel3.setIcon(new ImageIcon(image));
                		if(i == 3)
                			cardLabel4.setIcon(new ImageIcon(image));
                		if(i == 4)
                			cardLabel5.setIcon(new ImageIcon(image));
                	}
                }catch(IOException e1) {
                    e1.printStackTrace();
                }
	    	}
	    });
	}
	
	private void loadBacks(){
		try {
			card1 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card2 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card3 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card4 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card5 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			cardLabel1 = new JLabel(new ImageIcon(card1));
			cardArea.add(cardLabel1);

			cardLabel2 = new JLabel(new ImageIcon(card2));
			cardArea.add(cardLabel2);
			
			cardLabel3 = new JLabel(new ImageIcon(card3));
			cardArea.add(cardLabel3);
			
			cardLabel4 = new JLabel(new ImageIcon(card4));
			cardArea.add(cardLabel4);
			
			cardLabel5 = new JLabel(new ImageIcon(card5));
			cardArea.add(cardLabel5);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
		
	    
	public static void main(String[] args){
	
	    /* Use an appropriate Look and Feel */
	    try {
	        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	    } catch (UnsupportedLookAndFeelException ex) {
	        ex.printStackTrace();
	    } catch (IllegalAccessException ex) {
	        ex.printStackTrace();
	    } catch (InstantiationException ex) {
	        ex.printStackTrace();
	    } catch (ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    /* Turn off metal's use of bold fonts */
	    UIManager.put("swing.boldMetal", Boolean.FALSE);
	       
	    //Schedule a job for the event dispatch thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            createAndShowGUI();
	        }
	    });
	}
}
