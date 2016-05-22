package model;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameWindowController;
import controller.MainViewControllerC;

/**
 * Classe mes alta a la jerarquia de la partida.
 * @author Luis Recolons
 *
 */
public class Game{
	private static int dimx;
	private static int dimy;
	private static int locx;
	private static int locy;
	
	private static int difficulty;
	private boolean concentration;
	private boolean ai;
	private int score;
	/**
	 * El procediment "main" de la partida, crea el JFrame i fa les crides a la classe Board. 
	 * @param difficulty indica la dificultat amb la que es jugara el nivell per generar les característiques apropiades.
	 * @param concentration indica si es jugara Concentracia.
	 * @param ai indica si es jugara contra la maquina.
	 */
    public Game(GameWindowController controller, boolean concentration, boolean ai, int difficulty){
    	this.difficulty = difficulty;
    	switch (difficulty){
        	case 1: dimx = 600; //easy dimensions
        			dimy = 600;
        			locx = 450;
        			locy = 150;
        			break;
        	case 2: dimx = 750; //normal dimensions
        			dimy = 750;
        			locx = 350;
        			locy = 75;
        			break;
        	case 3: dimx = 900; //hard dimensions
        			dimy = 900;
        			locx = 270;
        			locy = 0;
        }
    	
    	Board b = new Board(difficulty, concentration, ai, controller);
        b.setPreferredSize(new Dimension(dimx, dimy)); //creates JFrame
        b.setLocation(locx, locy); //starting position, must be centered in the screen
        b.pack();
        b.setVisible(true);
        b.addWindowListener(controller);
        score = b.getScore()*difficulty;
    }
    
    public static void setScore(int score, int aiScore){
    	MainViewControllerC.refreshScore(score*difficulty, aiScore*difficulty);
    }
}
