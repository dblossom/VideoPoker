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
            
            for(int i = 0; i < 5; i++){
                Card card = hand.hand[i];
                String location = "/images/cards/front/" + card.valueToString() + card.suitToChar() + ".png";
                Image image = ImageIO.read(getClass().getResource(location));
                cardLabel[i].setIcon(new ImageIcon(image));
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
            cardLabel[i].setIcon(new ImageIcon(image));
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
			
			Image cardback = ImageIO.read(getClass().getResource("/images/cards/back/b1fv.png"));
			
			for(int i = 0; i < cardLabel.length; i++){
				cardLabel[i] = new JLabel(new ImageIcon(cardback));
				panel.add(cardLabel[i]);
			}

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
