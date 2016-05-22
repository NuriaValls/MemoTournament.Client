package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Game;
import model.Time;
import model.UserClient;
import network.ServerComunication;
import view.MainViewClient;

public class MainViewControllerC implements ActionListener{

	private static MainViewClient view;
	private static ServerComunication serverCom;
	private Time time;
	public static UserClient user;
	
	
	public MainViewControllerC(MainViewClient view, ServerComunication serverCom, Time time){
		this.view = view;
		this.serverCom = serverCom;
		this.time = time;
	}
	
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
	
	public void makeDialog(String message, boolean type){
		view.makeDialog(message,type);
	}
	
	public void sendStartServerC(String message){
		serverCom.sendStart(message);
	}
	
	public void sendRankServerC(String message){
		serverCom.sendRanking(message);
	}
	
	public void refreshRanking(String rank){
		rank = rank.substring(5);
		view.refreshRanking(rank);
	}
	
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
	
	public void setBlocked(boolean blocked){
		user.setBlocked(blocked);
	}
	
	public static void refreshScore(int score, int aiscore){
		user.setScore(user.getScore()+score);
		view.refreshScore(score, aiscore);
	}
	
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
