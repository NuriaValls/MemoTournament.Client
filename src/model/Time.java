package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import controller.MainViewControllerC;
import network.ServerComunication;

/**
 * Aquesta classe ens permet inicialitzar i parar tots els timers del programa.
 */
public class Time extends Thread{
	/**
	 * Atribut que instancia un objecte com a controlador.
	 */
	private MainViewControllerC controller;
	
	/**
	 * Atribut que cada un segon pregunta si s'ha iniciat una competicio al servidor.
	 */
	private Timer timerComp;
	/**
	 * Atribut que cada 30 segons demana el ranquing al servidor.
	 */
	private Timer timerRank;
	/**
	 * Atribut que compta el copte enrere per l'inici de la competicio.
	 */
	private Timer countdownTimer;
	/**
	 * Atribut que marca els segons de compte enrere perela competicio.
	 */
	private int countdown;
	/**
	 * Atribut que compta el compte enrere fins que s'acabi la competicio.
	 */
	private Timer competitionTimer;
	/**
	 * Atribut qque marca els segons restants per acabar la competcicio.
	 */
	private int competition;
	/**
	 * Atribut que marca si s'ha iniciat una competicio o estem en un compte enrere.
	 */
	private boolean started = false;
	/**
	 * Atribut que cada segon actualitza el temps de la partida.
	 */
	private static int timeG;
	/**
	 * Atribut que marca e temps que queda de la partida.
	 */
	private static Timer game;
	
	public Time(){
		timerComp = new Timer(1000, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.sendStartServerC("START");
			}
		});
		
		timerComp.start();
		
		timerRank = new Timer(30000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.sendRankServerC("RANK");
			}
		});
		
		countdownTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (countdown > 0){
					countdown--;
					controller.refreshTime(countdown, false);
				}else{
					competitionTimer.start();
					countdownTimer.stop();
					controller.makeDialog("The competition has started!", true);
					started = true;
				}
			}
			
		});
		
		competitionTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (competition > 0){
					competition--;
					controller.refreshTime(competition, true);
				}else{
					competitionTimer.stop();
				}
			}
			
		});
		
		game = new Timer(1000, new ActionListener(){ //timer for the game
            
			@Override
			public void actionPerformed(ActionEvent arg0){
            	if (timeG > 0){
            		timeG--;
            		controller.refreshGameTime(timeG);
            	}else{
            		game.stop();
            		JOptionPane pane = new JOptionPane("Time is over!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    JDialog dialog = pane.createDialog(new JFrame(), "Game ended!");
                    dialog.setModalityType(JDialog.ModalityType.MODELESS);
                    dialog.setVisible(true);
            	}
            }
        });
	}
	
	/**
	 * Metode que inicia el comptador de la partida al temps que rep com a  parametre.
	 */
	public static void startGameTimer(int timegame){
		timeG = timegame/60;
		game.start();
	}
	/**
	 * Metode que atura el comptador del temps de la partida.
	 */
	public static void stopGameTimer(){
		game.stop();
	}
	
	/**
	 * Metode que instancia un ocntrolador com a variable de la classe.
	 */
	public void registerController(MainViewControllerC controller){
		this.controller = controller;
	}
	/**
	 * Metode que inicia el comptador del ranking.
	 */
	public void startTimerRank(){
		timerRank.start();
	}
	
	public void stopTimerComp(){
		timerComp.stop();
	}
	/**
	 * Metode que incia el comptador de la commpta enrere  per la partida alvalor que rep com a paràmetre i tambe
	 * guarda el valor de temps que durara la copeticio.
	 */
	public void startCountdownTimer(int countdown, int competition){
		this.countdown = countdown-3;
		this.competition = competition+2;
		countdownTimer.start(); 
	}
	
	/**
	 * Metode que retorna el temps actual.
	 */
	public String getTime(){
		Date date = new GregorianCalendar().getTime();

		String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(date.getTime());
		
		return time;
	}
	
	public boolean getStarted(){
		return started;
	}
}