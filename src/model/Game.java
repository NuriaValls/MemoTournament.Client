package model;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Game{
	private static int dimx;
	private static int dimy;
	private static int locx;
	private static int locy;
	
	private int difficulty;
	private boolean concentration;
	private boolean ai;
	private int score;
	
    public Game(){
        int difficulty = 3;
        boolean concentration = false;
        boolean ai = true;
        int score;
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
    	
    	Board b = new Board(difficulty, concentration, ai);
    	b = new Board(difficulty, concentration, ai);
        b.setPreferredSize(new Dimension(dimx, dimy)); //creates JFrame
        b.setLocation(locx, locy); //starting position, must be centered in the screen
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.pack();
        b.setVisible(true);
        score = b.getScore()*difficulty;
    }   
}
