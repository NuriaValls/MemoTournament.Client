package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import network.ServerComunication;
import view.MainViewClient;

public class MainViewControllerC implements ActionListener{

	private static MainViewClient view;
	
	public MainViewControllerC(MainViewClient view){
		this.view = view;
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
			message = "LOG:Ruru/:3";
			if(ServerComunication.sendLogUser(message)){
				view.showInitialMenu();
			}
		}
		
		if(((JButton)e.getSource()).getText().equals("Enter as a Guest")){
			//entrar directament i no avisar al servidor en cap cas
			view.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Register Profile")){
			//registrar l'usuari i entrar com a usuari al menu
			message = "ADD:Julia/hola guapa";
			if (ServerComunication.sendAddUser(message)){
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
	
	public static void makeDialog(String message, boolean type){
		view.makeDialog(message,type);
	}
}
