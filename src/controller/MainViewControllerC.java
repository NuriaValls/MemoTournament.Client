package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.MainViewClient;

public class MainViewControllerC implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String message = new String();
		
		if(((JButton)e.getSource()).getText().equals("Register")){
			MainViewClient.showRegister();
		}
		
		if(((JButton)e.getSource()).getText().equals("Log In")){
			//comprovar si l'usuari esta registrat
			//si ho esta:
			MainViewClient.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Enter as a Guest")){
			//entrar directament i no avisar al servidor en cap cas
			MainViewClient.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Register Profile")){
			//registrar l'usuari i entrar com a usuari al menu
			message = "ADD:Ruru/nunu";
			MainViewClient.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Show Ranking")){
			MainViewClient.showRanking();
		}
		
		if(((JButton)e.getSource()).getText().equals("Menu")){
			MainViewClient.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Log Out")){
			MainViewClient.showLogIn();
		}
		
		if(((JButton)e.getSource()).getText().equals("New Game")){
			MainViewClient.showSelectGame();
		}
		
		if(((JButton)e.getSource()).getText().equals("Start Game")){
			//comença la partida
			MainViewClient.showGame();
		}
		
		if(((JButton)e.getSource()).getText().equals("End Game")){
			//sortir de la partida sense acabarla
			MainViewClient.showInitialMenu();
		}
		
		if(((JButton)e.getSource()).getText().equals("Back")){
			MainViewClient.showLogIn();
		}
		
		if(((JButton)e.getSource()).getText().equals("Back to Menu")){
			MainViewClient.showInitialMenu();
		}
	}
	
}
