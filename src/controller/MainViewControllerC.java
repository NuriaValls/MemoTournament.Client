package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;

import model.Game;
import model.Time;
import model.UserClient;
import network.ServerComunication;
import view.MainViewClient;
/**
 * Classe que s'encarrega de gestionar els listeners de la vista i respondre amb diferentses accions a les peticicons d'aquests.
 */
public class MainViewControllerC implements ActionListener{
	/**
	 * Atribut que instancia un objecte de la classe MainViewClient.
	 */
	private static MainViewClient view;
	/**
	 * Atribut que instancia un objecte per la comunicacio amb el servidor.
	 */
	private static ServerComunication serverCom;
	/**
	 * Atribut que instancia un objecte per controlar els timers.
	 */
	private Time time;
	/**
	 * Atribut que guard al ainformacio de l'usuari actual connectat al client.
	 */
	public static UserClient user;
	
	
	public MainViewControllerC(MainViewClient view, ServerComunication serverCom, Time time){
		this.view = view;
		this.serverCom = serverCom;
		this.time = time;
	}
	/**
	 * Metode que escolta els listeners de la vista i gestiona cadascun dels events.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = new String();
		
		if(((JButton)e.getSource()).getText().equals("Register")){
			if (serverCom.getCompetition()){
				view.showRegister();
			}else{
				view.makeDialog("You can't Register while there is no competition", true);
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Log In")){
			//comprovar si l'usuari esta registrat
			//si ho esta:
			if (serverCom.getCompetition()){
				if(serverCom.sendLogUser(new UserClient(view.getLogNickname(), view.getLogPasword()))){
					view.refreshUser(user);
					view.showInitialMenu();
				}
			}else{
				view.makeDialog("You can't Log In while there is no competition", true);
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Enter as a Guest")){
			//entrar directament i no avisar al servidor en cap cas
			user = new UserClient("GUEST","",0);
			view.setGuest(true);
			view.refreshUser(user);
			view.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Register Profile")){
			//registrar l'usuari i entrar com a usuari al menu
			message = "ADD:"+view.getRegNickname()+"/"+view.getRegPasword();
			if (serverCom.sendAddUser(message)){
				user = new UserClient(view.getRegNickname(),view.getRegPasword(),0);
				view.showInitialMenu();
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Show Ranking")){
			view.showRanking();
		}
		
		if(((JButton)e.getSource()).getText().equals("Menu")){
			view.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Log Out")){
			view.showLogIn();
		}
		
		if(((JButton)e.getSource()).getText().equals("New Game")){
			if(user.getNickname().equals("GUEST")){
				view.showSelectGame();
			}else{
				if(time.getStarted()){
					serverCom.sendBlockedUser("BLOCKED:"+user.getNickname());
					if(!user.isBlocked()){
						view.showSelectGame();
					}else{
						view.makeDialog("You can't play until until you're unlocked.", false);
					}
				}else{
					view.makeDialog("You can't play until the competition has started!", true);
				}
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Start Game")){
			//comença la partida
			Game game = new Game(new GameWindowController(), view.getmodeCon(), view.getIA(), view.getDifficulty());
			view.refreshMode();
			view.showGame();
		}
		
		if(((JButton)e.getSource()).getText().equals("End Game")){
			//sortir de la partida sense acabarla
			view.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Back")){
			view.showLogIn();
		}
		
		if(((JButton)e.getSource()).getText().equals("Back to Menu")){
			view.showInitialMenu();
		}
	}
	/**
	 * Medote que llança un diàleg a lal vista amb el missatge  per referència i del tipus del bolea.
	 */
	public void makeDialog(String message, boolean type){
		view.makeDialog(message,type);
	}
	
	/**
	 * metode que pregunta al servidor si s'ha  iniciat una competicio.
	 */
	public void sendStartServerC(String message){
		serverCom.sendStart(message);
	}
	/**
	 * Metode que pregunta al servidor el ranquing actuall de la competicio.
	 */
	public void sendRankServerC(String message){
		serverCom.sendRanking(message);
	}
	/**
	 * Metode que actualitza la informacio del ranquing que rep per referència a la vista.
	 */
	public void refreshRanking(String rank){
		rank = rank.substring(5);
		view.refreshRanking(rank);
	}
	/**
	 * mtode que actualitza  el temps de competicio o de compta enrere segons el bolea a la vista.
	 */
	public void refreshTime(int time, boolean comp){
		String print = new String();
		String hour = new String();
		String min = new String();
		String sec = new String();
		if(time/3600<10){
			hour = "0"+time/3600+":";
		}else{
			hour = time/3600+":";
		}
		if((time%3600)/60<10){
			min = "0"+(time%3600)/60+":";
		}else{
			min = (time%3600)/60+":";
		}
		if((time%3600)%60<10){
			sec = "0"+(time%3600)%60+" ";
		}else{
			sec = (time%3600)%60+" ";
		}
		
		if(comp){
			print = "The competition will end in: "+min+sec+"minutes.";
		}else{
			print = "The competition will start in: "+hour+min+sec+"hours.";
		}
		view.refreshTime(print);
	}
	
	/**
	 * Metode quemarca un usuair com a bloquejat o desbloquejat segons boolea.
	 */
	public void setBlocked(boolean blocked){
		user.setBlocked(blocked);
	}
	/**
	 * Metode que actualitza la puntuacio de l'usuari actual a la vista.
	 */
	public static void refreshScore(int score, int aiscore){
		user.setScore(user.getScore()+score);
		view.refreshScore(score, aiscore);
	}
	/**
	 * Metode que actualitza el temps actual del joc a la vista, segons el temps que rep per referencia.
	 */
	public static void refreshGameTime(int gameTime){
		String print = new String();
		String min = new String();
		String sec = new String();
		
		if((gameTime%3600)/60<10){
			min = "0"+(gameTime%3600)/60+":";
		}else{
			min = (gameTime%3600)/60+":";
		}
		if((gameTime%3600)%60<10){
			sec = "0"+(gameTime%3600)%60+" ";
		}else{
			sec = (gameTime%3600)%60+" ";
		}
		
		print = min+sec+" ";

		view.refreshGameTime(print);
	}
	/**
	 * Metode que envia la informacio de la partida un cop acabada al servidor si l'usuari acutal no ha entrat com a  convidat.
	 */
	public static void gameEnded(int score){
		if (!user.getNickname().equals("GUEST")){
			String mode = new String();
			if (view.getmodeCon()){
				mode = "concentracio";
			}else{
				mode = "memoria";
			}
			serverCom.sendUpdate("UPDATE:"+user.getNickname()+"/"+mode+"/"+score);
		}
	}
}
