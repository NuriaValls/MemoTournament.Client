package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * Classe que representa cadascuna de les cartes de la partida. Cada carta te una serie d'atributs propi que la diferencien de les altres.
 * @author Luis Recolons
 *
 */
public class Card extends JButton implements Comparable<Card>{
   
	private static final long serialVersionUID = 1L;
	private final int id;
	private int idCard;
	private String name;
    private boolean matched = false;
    private ImageIcon imgFace;
    
    private ImageIcon imgBack = new ImageIcon(getClass().getResource("/images/back.png"));
    /**
     * El constructor de carta, s'encarrega d'afegir-li els seus atributs, coordinats des de la classe Board.
     * @param id identificacio de la parella de cartes, segons el número de parelles determinat per la dificultat de la partida.
     * @param imagename nom de la imatge que li correspon.
     * @param idCard identificacio propia de la carta, independent de la parella.
     * @param difficulty nivell amb el que es jugara la partida.
     */
    public Card(int id, String imagename, int idCard, int difficulty){
    	this.id = id;
    	this.idCard = idCard;
    	name = imagename;
		
    	switch (difficulty){
    	case 1:
    		imgFace = new ImageIcon(getClass().getResource("/images/easy/"+name+".png"));
    		break;
    	case 2:
    		System.out.println("/images/normal/"+name+".png");
    		imgFace = new ImageIcon(getClass().getResource("/images/normal/"+name+".png"));
    		break;
    	case 3:
    		imgFace = new ImageIcon(getClass().getResource("/images/hard/"+name+".png"));
    		break;
    	default:
    		break;
    	}
    	
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
    /**
     * Canvia l'estat de la carta a endevinada.
     * @param matched l'estat al que es vol canviar la carta (es passa com true).
     */
    public void setMatched(boolean matched){
        this.matched = matched;
    }
    /**
     * getter de l'estat de la carta.
     * @return si la carta esta endevinada o no.
     */
    public boolean getMatched(){
        return this.matched; 
    }
    /**
     * Gira la carta per mostrar la seva imatge.
     */
    public void showImage(){
    	super.setIcon(imgFace);
    }
    /**
     * Gira la carta per amagar l'imatge.
     */
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
    /**
	 * Compara aquesta carta amb la que es passa i diu si son iguals.
	 * @c la carta que es vol comparar.
	 * @return un 1 si son iguals o un -1 si no ho son.
	 */
	@Override
	public int compareTo(Card c) {
		if(this.getId() == c.getId()){
			if(this.getIdCard() != c.getIdCard()){
				
				return 1;
			}
		}
		return -1;
	}
	/**
	 * Canvia un integer a String.
	 * @return el String amb la id de la carta.
	 */
	@Override
	public String toString(){
		return Integer.toString(id);
	}
	
}