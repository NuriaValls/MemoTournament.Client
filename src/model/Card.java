package model;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton{
   
	private static final long serialVersionUID = 1L;
	private final int id;
	private String name;
	private int ai = 0; //# times shown, for AI purpose
    private boolean matched = false;
    private boolean faceUp = false;
    private ImageIcon imgFace;
    
    private static ImageIcon imgBack = new ImageIcon("back.gif");

    public Card(int id, String imagename){
    	this.id = id;
    	name = imagename;
		imgFace = new ImageIcon(name + ".gif");
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
    
    public void showImage(){
    	super.setIcon(imgFace);
		faceUp = true;
    }
    
    public void hideImage(){
    	super.setIcon(imgBack);
		faceUp=false;
    }
    
    public void incrementai(){
    	this.ai = ai++;
    }
}