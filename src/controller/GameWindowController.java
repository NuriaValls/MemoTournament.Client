package controller;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Board;
import model.Time;

public class GameWindowController extends WindowAdapter{
	
	public void closeGame(WindowEvent e){
		System.out.println("ruru noob");
		Frame gameFrame = (Frame)e.getSource();
		//actualitzar partida
		System.out.println(gameFrame.getFrames()[0].toString());
		//Board.stopTimer();
		Time.stopGameTimer();
		gameFrame.dispose();
	}
	
	
}
