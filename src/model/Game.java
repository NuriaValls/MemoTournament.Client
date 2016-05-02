package model;

import java.awt.Dimension;
import javax.swing.JFrame;


public class Game{
	private static int dimx;
	private static int dimy;
	private static int locx;
	private static int locy;
	
    public static void main(String[] args){
        int difficulty = 3;
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
        	case 3: dimx = 1000; //hard dimensions
        			dimy = 800;
        			locx = 220;
        			locy = 25;
        }
    	
    	Board b = new Board(difficulty);
        b.setPreferredSize(new Dimension(dimx, dimy)); //creates JFrame
        b.setLocation(locx, locy); //starting position, must be centered in the screen
        b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.pack();
        b.setVisible(true);
    }   
}
