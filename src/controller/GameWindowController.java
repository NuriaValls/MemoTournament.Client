package controller;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Board;
import model.Time;
/**
 * Classe que permet gestionar el listener vingulat al frame de la pantlala de joc.
 */
public class GameWindowController extends WindowAdapter{
	/**
	 * Metode que tanca el frame si s'ha acabat la partida o l'usuari prem el boto de tancar.
	 */
	public void closeGame(WindowEvent e){
		Frame gameFrame = (Frame)e.getSource();
		Time.stopGameTimer();
		gameFrame.dispose();
	}
	
	
}
