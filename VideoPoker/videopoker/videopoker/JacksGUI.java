package videopoker;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import videopoker.Rank.ranks; // why ?

/**
 * A class that will load a GUI so you can play Jacks or better
 * 
 * TODO: Refactor most of this code, it is all bandaid to get it
 *       to work first.
 *       
 * @author Dan Blossom
 *
 */
public class JacksGUI extends JFrame {
	
	// needed to create a hand for game play
	Hand hand = new Hand();

	// The button used for quitting the game
	JButton quit = new JButton("Quit");
	
	// The deal button, also the "draw" button
	// The text will change during game play
	JButton deal = new JButton("Deal");
	
	/**
	 * Set of buttons used for determining which cards to "hold"
	 * Also text changes to "held" when a card is held
	 * TODO: they need this "global" ? 
	 */
	JButton hold1 = new JButton("Hold");
	JButton hold2 = new JButton("Hold");
	JButton hold3 = new JButton("Hold");
	JButton hold4 = new JButton("Hold");
	JButton hold5 = new JButton("Hold");
	
	// used for displaying the ranking of a hand.
	JLabel handrank = new JLabel("");
	
	// booleans used to determine if a card is held or not
	boolean held1, held2, held3, held4, held5;
	
	// used for displaying the card to the player
	JLabel cardLabel1, cardLabel2, cardLabel3, cardLabel4, cardLabel5;
	
	/**
	 * One of the layouts and the panel --- this might be moved?
	 */
	GridLayout cardLayout = new GridLayout(0,5);

	    
	public JacksGUI(String name) {
	    super(name);
	    setResizable(false);
	}
	    
	public void addComponentsToPane(final Container pane) {
        
		// Panels needed for game layout
	    JPanel controls = new JPanel();
	    JPanel actionButtons = new JPanel();
	    final JPanel cardArea = new JPanel();
		
	    // set the layouts of the three panels
	    cardArea.setLayout(cardLayout);
	    controls.setLayout(new GridLayout(0,5));
	    actionButtons.setLayout(new GridLayout(2,2));
	    
	    // add the buttons to their respective layouts
	    addActionButtons(actionButtons);
	    addControlButtons(controls);
	    
	    // setup the action listeners for the buttons
	    setActionListeners();
	    
	    // load the back of the card design for inital play
	    loadBacks(cardArea);
	    
	    // add the panels to content pane
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
        		if(button.getText().equalsIgnoreCase("Deal")){
        			deal(button);
        			return;
        		}
        		if(button.getText().equalsIgnoreCase("Draw")){
        			draw(button);
        			return;
        		}
            }
        });
	}
	
	private void deal(JButton button){
		
		setHeldsFalse();
		setHoldButtonTextHold();

		hand = new Hand();
        try{
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
             deal.setText("Draw");
         }catch(IOException e1) {
             e1.printStackTrace();
         }
        
		ranks r = Rank.rank(hand);
		
		handrank.setText(r+"");
	}
	
	private void draw(JButton button){
		button.setText("Deal");
		
		// hmm is there a better way?
		if(!held1){
			changeCards(0);
		}
		if(!held2){
			changeCards(1);
		}
		if(!held3){
			changeCards(2);
		}
		if(!held4){
			changeCards(3);
		}
		if(!held5){
			changeCards(4);
		}
		
		ranks r = Rank.rank(hand);
		
		handrank.setText(r+"");
	}
	
	private void changeCards(int i){
		hand.newCard(i);
		Card card = hand.hand[i];
		String location = "/images/cards/front/" + card.valueToString() + card.suitToChar() + ".png";
		
		try{
		    Image image = ImageIO.read(getClass().getResource(location));
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
		}catch(IOException el){
			el.printStackTrace();
		}
	}
	
	private void quitButtonClick(JButton button){

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
	}
	
	private void loadBacks(JPanel panel){
		try {
			
			Image card1, card2, card3, card4, card5;
			card1 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card2 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card3 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card4 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			card5 = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			
			cardLabel1 = new JLabel(new ImageIcon(card1));
			panel.add(cardLabel1);

			cardLabel2 = new JLabel(new ImageIcon(card2));
			panel.add(cardLabel2);
			
			cardLabel3 = new JLabel(new ImageIcon(card3));
			panel.add(cardLabel3);
			
			cardLabel4 = new JLabel(new ImageIcon(card4));
			panel.add(cardLabel4);
			
			cardLabel5 = new JLabel(new ImageIcon(card5));
			panel.add(cardLabel5);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void holdButtonClick(JButton button){
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	
            	if(button.getText().equalsIgnoreCase("Hold")){
            		hold(button);
            		return;
            	}
            	
            	if(button.getText().equalsIgnoreCase("Held")){
            		unhold(button);
            		return;
            	}

            }
        });
	}
	
	/**
	 * Given a button it will hold that card so it will not
	 * be discarded when the player is given the opportunity
	 * to hold cards and draw
	 * 
	 * TODO: is there another solution, I do not like the 5 if's
	 * 
	 * @param button the button that was clicked to indicate hold this card
	 */
	private void hold(JButton button){

    	if(button == hold1){
            held1 = true;
            hold1.setText("Held");
        }
        if(button == hold2){
        	held2 = true;
            hold2.setText("Held");
        }
        if(button == hold3){
        	held3 = true;
            hold3.setText("Held");
        }
        if(button == hold4){
        	held4 = true;
            hold4.setText("Held");
        }
        if(button == hold5){
        	held5 = true;
            hold5.setText("Held");
        }
	}
	
	/**
	 * Given a button it will determine what button it is
	 * Then set the that buttons corresponding held flag to false
	 * and make the text say "hold" to indicate the card will
	 * not be held
	 * 
	 * TODO: Is there another solution than 4 if statements?
	 *       add returns after each if so we break out faster
	 * @param button the button to unhold
	 */
	private void unhold(JButton button){
    	if(button == hold1){
            held1 = false;
            hold1.setText("Hold");
        }
        if(button == hold2){
        	held2 = false;
            hold2.setText("Hold");
        }
        if(button == hold3){
        	held3 = false;
            hold3.setText("Hold");
        }
        if(button == hold4){
        	held4 = false;
            hold4.setText("Hold");
        }
        if(button == hold5){
        	held5 = false;
            hold5.setText("Hold");
        }
		
	}
	
	/**
	 * This will set all the "held" booleans to false
	 * used mainly at a new deal...
	 */
	private void setHeldsFalse(){
		held1 = false;
		held2 = false;
		held3 = false;
		held4 = false;
		held5 = false;
	}
	
	/**
	 * This will set the hold buttons test to the default "hold"
	 */
	private void setHoldButtonTextHold(){
		hold1.setText("Hold");
		hold2.setText("Hold");
		hold3.setText("Hold");
		hold4.setText("Hold");
		hold5.setText("Hold");
	}
	
	/**
	 * Adds the action buttons to the action panel
	 */
	private void addActionButtons(JPanel panel){
		panel.add(new JLabel("Your hand is: "));
		panel.add(handrank);
		panel.add(quit);
		panel.add(deal);
	}
	
	private void addControlButtons(JPanel panel){
		panel.add(hold1);
		panel.add(hold2);
	    panel.add(hold3);
	    panel.add(hold4);
	    panel.add(hold5);
	}
	
	private void setActionListeners(){
	    holdButtonClick(hold1);
	    holdButtonClick(hold2);
	    holdButtonClick(hold3);
	    holdButtonClick(hold4);
	    holdButtonClick(hold5);
        dealButton(deal);
        quitButtonClick(quit);
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

	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            createAndShowGUI();
	        }
	    });
	}
}
