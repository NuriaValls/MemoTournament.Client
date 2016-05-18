package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Time;
import model.UserClient;
import network.ServerComunication;
import view.MainViewClient;

public class MainViewControllerC implements ActionListener{

	private static MainViewClient view;
	private ServerComunication serverCom;
	private Time time;
	public UserClient user;
	
	
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
				//message = "LOG:"+view.getLogNickname()+"/"+view.getLogPasword();
				if(serverCom.sendLogUser(new UserClient(view.getLogNickname(), view.getLogPasword()))){
					view.showInitialMenu();
				}
			}else{
				view.makeDialog("You can't Log In while there is no competition", true);
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Enter as a Guest")){
			//entrar directament i no avisar al servidor en cap cas
			user = new UserClient("GUEST","",0);
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
			if(user.getNickname().equals("GUEST") || ( !user.getNickname().equals("GUEST") && time.getStarted()) ){
				view.showSelectGame();
			}else{
				view.makeDialog("You can't play until the competition has started!", true);
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Start Game")){
			//comença la partida
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
	
	public void sendTimeServerC(String message){
		serverCom.sendTime(message);
	}
	
	public void refreshRanking(String rank){
		rank = rank.substring(5);
		view.refreshRanking(rank);
	}
	
	public void refreshTime(String time, boolean comp){
		//view.refreshTime(time,comp);
	}
}
