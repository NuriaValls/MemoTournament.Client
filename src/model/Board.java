package model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Board extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<Card> cards;
    private Card selectedCard;
    private Card c1;
    private Card c2;
    private Timer t;
    private String[] imagename;

    public Board(int difficulty){

        int pairs = assignPairs(difficulty); //pairs depending on the difficulty level
        
        List<Card> cardsList = new ArrayList<Card>();
        List<Integer> cardVals = new ArrayList<Integer>();

        for (int i = 0; i < pairs; i++){ //assigns the numbers as the elements of the cards
            cardVals.add(i);
            cardVals.add(i);
        } //HAY QUE CAMBIARLO POR IMAGENES
        
        switch (difficulty){
        	case 1: setImagesEasy(imagename);
        			break;
        	case 2: setImagesNormal(imagename);
        			break;
        	case 3: setImagesHard(imagename);
        			break;
        	default: System.out.println("An error has ocurred when determining the difficulty level");		
			break;
        }
       
        Collections.shuffle(cardVals); //suffles the list of elements

        for (int val : cardVals){
            Card c = new Card(val, imagename[val]);
            c.addActionListener(new ActionListener(){
            	public void actionPerformed(ActionEvent ae){ //creates the button itself
            		selectedCard = c;
            		doTurn();
            	}  	
            });
            cardsList.add(c);
        }
        this.cards = cardsList;
        
        //sets up the timer
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }
        });

        t.setRepeats(false);

        //creates the board 
        Container pane = getContentPane();
        createGrid(difficulty, pane);  //creates the grid depending on the difficulty level
        
        for (Card c : cards){
            pane.add(c);
        }
        
        setTitle("Memory Match");
    }

    public void doTurn(){
        if (c1 == null && c2 == null){ //player chooses c1
            c1 = selectedCard;
            c1.incrementai();
            c1.showImage();
        }

        if (c1 != null && c1 != selectedCard && c2 == null){ //player chooses c2
            c2 = selectedCard;
            c2.incrementai();
            c2.showImage();
            t.start();

        }
    }

    public void checkCards(){
        if (c1.getId() == c2.getId()){ //if match 
            c1.setEnabled(false);  //disables the button
            c2.setEnabled(false);
            c1.setMatched(true);  //flags the button
            c2.setMatched(true);
            if (this.isGameWon()){
                JOptionPane.showMessageDialog(this, "You win!");
                System.exit(0);
            }
        }

        else{ //if not match 
            c1.hideImage(); 
            c2.hideImage();
        }
        c1 = null; //reset c1 and c2
        c2 = null;
    }

    public boolean isGameWon(){
        return this.cards.stream().allMatch(c -> c.getMatched()); //game finished
    }
    
    public int assignPairs(int difficulty){
    	switch (difficulty){
    		case 1: return 10; //easy 10 pairs
    
    		case 2: return 15; //normal 15 pairs
    				
    		case 3: return 21; //hard 21 pairs
    		
    		default: return 10;
    				
    	}
    }
    
    public void createGrid(int difficulty, Container pane){
    	switch (difficulty){
			case 1: pane.setLayout(new GridLayout(4, 5));//easy 20 
					break;
					
			case 2: pane.setLayout(new GridLayout(5, 6)); //normal 30
					break;
					
			case 3: pane.setLayout(new GridLayout(6, 7)); //hard 42
					break;
					
			default: System.out.println("An error has ocurred when determining the difficulty level");		
					break;
    	}
    }
    
    public void setImagesEasy(String[] imagename){
    	imagename = new String[] {"AUSTRALIA","BRAZIL","CANADA","CHINA","GREATBRITAIN","GREECE",
    			"JAMAICA","JAPAN","SWEDEN","USA"};
    }
    
    public void setImagesNormal(String[] imagename){
    	
    }
    
    public void setImagesHard(String[] imagename){
    	
    }

}

