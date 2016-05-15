package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Time;
import network.ServerComunication;
import view.MainViewClient;

public class MainViewControllerC implements ActionListener{

	private static MainViewClient view;
	private ServerComunication serverCom;
	
	public MainViewControllerC(MainViewClient view, ServerComunication serverCom){
		this.view = view;
		this.serverCom = serverCom;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = new String();
		
		if(((JButton)e.getSource()).getText().equals("Register")){
			view.showRegister();
		}
		
		if(((JButton)e.getSource()).getText().equals("Log In")){
			//comprovar si l'usuari esta registrat
			//si ho esta:
			message = "LOG:"+view.getLogNickname()+"/"+view.getLogPasword();
			if(serverCom.sendLogUser(message)){
				view.showInitialMenu();
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Enter as a Guest")){
			//entrar directament i no avisar al servidor en cap cas
			view.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Register Profile")){
			//registrar l'usuari i entrar com a usuari al menu
			message = "ADD:"+view.getRegNickname()+"/"+view.getRegPasword();
			if (serverCom.sendAddUser(message)){
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
			view.showSelectGame();
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
		
	}
	
	public void refreshRanking(String rank){
		rank = rank.substring(5);
		view.refreshRanking(rank);
	}
}
