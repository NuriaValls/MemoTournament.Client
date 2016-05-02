package model;

import javax.swing.JButton;

public class Card extends JButton{
   
	private static final long serialVersionUID = 1L;
	private final int id;
	private int ai = 0; //# times shown, for AI purpose
    private boolean matched = false;

    public Card(int id){
    	this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setMatched(boolean matched){
        this.matched = matched;
    }

    public boolean getMatched(){
        return this.matched; 
    }
    
    public void showText(){
    	this.setText(String.valueOf(id));
    }
    
    public void hideText(){
    	this.setText("");
    }
    
    
    public void incrementai(){
    	this.ai = ai++;
    }
}