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
@SuppressWarnings("serial") // nothing to serialize ... 
public class JacksGUI extends JFrame {
	
	// needed to create a hand for game play
	Hand hand = new Hand();

	// The button used for quitting the game
	JButton quit = new JButton("Quit");
	
	// The deal button, also the "draw" button
	// The text will change during game play
	JButton deal = new JButton("Deal");
	
    // hold buttons used for player to "hold" card
	JButton[] hold = new JButton[5];
	
	// used for displaying the ranking of a hand.
	JLabel handrank = new JLabel("");
	
	// booleans used to determine if a card is held or not
	boolean[] held = new boolean[5];
	
	// used for displaying the card to the player
	JLabel[] cardLabel = new JLabel[5];
	
	/**
	 * One of the layouts and the panel --- this might be moved?
	 */
	GridLayout cardLayout = new GridLayout(0,5);

	/**
	 * Basic constructor that calls its super and setsResiable to false..
	 * @param name for the title bar
	 */
	public JacksGUI(String name) {
	    super(name);
	    setResizable(false);
	}
	    
	/**
	 * Adds components to the main pane
	 * @param pane the pane to add the componets too.
	 */
	public void addComponentsToPane(final Container pane) {
        
		// Panels needed for game layout
	    JPanel controls = new JPanel();
	    JPanel actionButtons = new JPanel();
	    final JPanel cardArea = new JPanel(); // can I remove final?
		
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
	    
	/**
	 * Exactly what is says, calls constructor, sets default close and adds
	 * the getContentPane to the pane ... pack it up and setVisible
	 */
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
	
	/**
	 * The action listener for the deal button
	 * @param button the button to listen
	 */
	private void dealButton(JButton button){
		
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	// so, if the text is deal than game is new so ..
        		if(button.getText().equalsIgnoreCase("Deal")){
        			// call out deal method and return
        			deal(button);
        			return;
        		}
        		// if the text is draw, the player has the option to
        		// change a up to 5 cards -- or none at all so ..
        		if(button.getText().equalsIgnoreCase("Draw")){
        			// call our draw method and return
        			draw(button);
        			return;
        		}
            }
        });
	}
	
	/**
	 * Our deal button, called when the deal button is clicked and the text
	 * displayed is "deal"
	 * @param button the deal button
	 */
	private void deal(JButton button){
		
		// first we want to clear any held card positions from a previous game (or maybe this is the first game)
		setHeldsFalse();
		// then we also want to set the text on the buttons to say "Hold" rather than held, if it was previously held
		setHoldButtonTextHold();

		// here we want to set up the hand to play
		hand = new Hand();
        try{
        	// "deal()" basically fills our array with the first 5 cards from the top of the queue - all hidden tho.
            hand.deal();
            
            // print the hand to the screen
            for(int i = 0; i < 5; i++){
                Card card = hand.hand[i];
                String location = "/images/cards/front/" + card.valueToString() + card.suitToChar() + ".png";
                Image image = ImageIO.read(getClass().getResource(location));
                cardLabel[i].setIcon(new ImageIcon(image));
             }
            // change the text of the deal button to draw
             deal.setText("Draw");
         }catch(IOException e1) {
             e1.printStackTrace();
         }
        // gets the players rank and prints it to the screen
		ranks r = Rank.rank(hand);
		handrank.setText(r+"");
	}
	
	/**
	 * Our draw button, when called it will reset the text back to "Deal"
	 * then it will provide new cards the player requested by not saying hold
	 * @param button the draw / deal button
	 */
	private void draw(JButton button){
		// reset the deal button text
		button.setText("Deal");
		
		// loop over the held booleans and if we
		// do not hold a card, give the player another
		for(int i = 0; i < held.length; i++){
			if(!held[i])
				changeCards(i);
		}
		// get the rank of the hand
		ranks r = Rank.rank(hand);
		// display that rank to player
		handrank.setText(r+"");
	}
	
	/**
	 * Given a ZERO based card in an hand, it will replace that card with the
	 * the next card in the hand
	 * @param i the zero-based card number... IE: n-1, IE: card 1 is 0 (zero).
	 */
	private void changeCards(int i){
		// replace the card at position i
		hand.newCard(i);
		// get that new card
		Card card = hand.hand[i];
		// get an image that visually represents that card
		String location = "/images/cards/front/" + card.valueToString() + card.suitToChar() + ".png";
		// update the image on the GUI
		try{
		    Image image = ImageIO.read(getClass().getResource(location));
            cardLabel[i].setIcon(new ImageIcon(image));
		}catch(IOException el){
			el.printStackTrace();
		}
	}
	
	/**
	 * A quit button, not much to say
	 * @param button the quit button
	 */
	private void quitButtonClick(JButton button){
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
	}
	
	/**
	 * Loads the five cards to display their backs, used when starting the first game
	 * @param panel the panel to display the cards on
	 */
	private void loadBacks(JPanel panel){
		try {
			// get the card back image
			Image cardback = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			// for each label that represents a card, have it display the above image
			for(int i = 0; i < cardLabel.length; i++){
				cardLabel[i] = new JLabel(new ImageIcon(cardback));
				panel.add(cardLabel[i]);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * The action listener for the hold buttons
	 * @param button what hold button was clicked.
	 */
	private void holdButtonClick(JButton button){
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	// if the button text is Hold that implies it has
            	// not been selected - call the hold function
            	if(button.getText().equalsIgnoreCase("Hold")){
            		// call the hold function
            		hold(button);
            		return;
            	}
            	// if the text says "Held" then player might have
            	// changed his/her mind, so call unhold(button)
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
	 * @param button the button that was clicked to indicate hold this card
	 */
	private void hold(JButton button){
		// loop over the hold button array to
		// determine which button was clicked
		// set the corresponding "held" to true
		// button text to Held and break.
		for(int i = 0; i < held.length; i++){
			if(button == hold[i]){
				held[i] = true;
				hold[i].setText("Held");
				break;
			}
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
		// this will loop over the hold button array
		// and find the match to the passed in button
		// it will set its corresponding held boolean
		// to false and change the button text to Hold
		for(int i = 0; i < held.length; i++){
			if(button == hold[i]){
				held[i] = false;
				hold[i].setText("Hold");
				break;
			}
		}
		
	}
	
	/**
	 * This will set all the "held" booleans in an
	 * array to false used mainly at a new deal...
	 */
	private void setHeldsFalse(){
		for(int i = 0; i < held.length; i++){
			held[i] = false;
		}
	}
	
	/**
	 * This will set the hold buttons test to the default "hold"
	 */
	private void setHoldButtonTextHold(){
		for(int i = 0; i < hold.length; i++){
			hold[i].setText("Hold");
		}
	}
	
	/**
	 * Adds the action buttons to the action panel
	 * @parm panel the panel to add stuff
	 */
	private void addActionButtons(JPanel panel){
		panel.add(new JLabel("Your hand is: "));
		panel.add(handrank);
		panel.add(quit);
		panel.add(deal);
	}
	
	/**
	 * Adds the hold buttons to the action panel
	 * @param panel the panel to add stuff
	 */
	private void addControlButtons(JPanel panel){
		for(int i = 0; i < hold.length; i++){
			hold[i] = new JButton("Hold");
			panel.add(hold[i]);
		}
	}
	
	/**
	 * Sets all action listeners needed for all the buttons
	 */
	private void setActionListeners(){
		// since hold buttons is an array
		setHoldActionListeners();
		// call the deal and quit action listeners
        dealButton(deal);
        quitButtonClick(quit);
	}
	
	/**
	 * Sets the hold button action listeners
	 */
	private void setHoldActionListeners(){
		for(int i = 0; i < hold.length; i++){
			holdButtonClick(hold[i]);
		}
	}
	    
	/**
	 * Main
	 * @param args
	 */
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
