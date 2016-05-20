package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Card extends JButton implements Comparable<Card>{
   
	private static final long serialVersionUID = 1L;
	private final int id;
	private int idCard;
	private String name;
    private boolean matched = false;
    private ImageIcon imgFace;
    
    private static ImageIcon imgBack = new ImageIcon("back.png");

    public Card(int id, String imagename, int idCard, int difficulty){
    	this.id = id;
    	this.idCard = idCard;
    	name = imagename;
		imgFace = new ImageIcon(name + ".png");
		if(difficulty == 3){	
			Image img = imgFace.getImage();
			Image newimg = img.getScaledInstance(95, 130,  java.awt.Image.SCALE_SMOOTH);
			imgFace = new ImageIcon(newimg);
		}
    }

    public int getId(){
        return this.id;
    }
    
    public int getIdCard(){
    	return this.idCard;
    }

    public void setMatched(boolean matched){
        this.matched = matched;
    }

    public boolean getMatched(){
        return this.matched; 
    }
    
    public void showImage(){
    	super.setIcon(imgFace);
    }
    
    public void hideImage(){
    	super.setIcon(imgBack);
    }
    
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }

	@Override
	public int compareTo(Card c) {
		if(this.getId() == c.getId()){
			if(this.getIdCard() != c.getIdCard()){
				
				return 1;
			}
		}
		return -1;
	}
	
	@Override
	public String toString(){
		return Integer.toString(id);
	}
	
}