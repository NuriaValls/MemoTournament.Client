package controller;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindowController extends WindowAdapter{
	
	public void closeGame(WindowEvent e){
		Frame gameFrame = (Frame)e.getSource();
		//actualitzar partida
		
		gameFrame.dispose();
	}
	
	
}
