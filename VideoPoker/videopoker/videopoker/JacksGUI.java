package videopoker;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

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
	
	Hand hand = new Hand();

	JButton quit = new JButton("Quit");
	JButton deal = new JButton("Deal");
	
	JButton hold1 = new JButton("Hold");
	JButton hold2 = new JButton("Hold");
	JButton hold3 = new JButton("Hold");
	JButton hold4 = new JButton("Hold");
	JButton hold5 = new JButton("Hold");
	
	boolean held1, held2, held3, held4, held5;
	
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
	    
	    quitButtonClick(quit);
	    
	    loadBacks();
	        
	    //Add controls to set up horizontal and vertical gaps
	    controls.add(hold1);
	    controls.add(hold2);
	    controls.add(hold3);
	    controls.add(hold4);
	    controls.add(hold5);
	    
	    holdButtonClick(hold1);
	    holdButtonClick(hold2);
	    holdButtonClick(hold3);
	    holdButtonClick(hold4);
	    holdButtonClick(hold5);
	    

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
		held1 = false;
		held2 = false;
		held3 = false;
		held4 = false;
		held5 = false;
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
	
	private void holdButtonClick(JButton button){
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // UGH!
            	if(button == hold1){
                    held1 = true;
                }
                if(button == hold2){
                	held2 = true;
                }
                if(button == hold3){
                	held3 = true;
                }
                if(button == hold4){
                	held4 = true;
                }
                if(button == hold5){
                	held5 = true;
                }
            }
        });
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
