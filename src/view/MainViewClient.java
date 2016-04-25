package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import model.MTMainClient;
import model.UserClient;

public class MainViewClient extends JFrame{	

	private static JPanel jpMenu;
	private static CardLayout cardLayout = new CardLayout();
	
	private static JPanel jpRegisterCard = new JPanel();
	private static JPanel jpLogInCard = new JPanel();
	private static JPanel jpGameCard = new JPanel();
	private static JPanel jpRankingCard = new JPanel();
	
	private JPanel jpButtonMenu;
	
	public MainViewClient(){
		setTitle("Memory Torunament -Client-");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400,300);
		
		createRegisterCard();
		createLogInCard();
		createGameCard();
		createRankingCard();
		
		jpMenu = new JPanel();
		jpMenu.setLayout(cardLayout);
		
		//add aux panels
	}
	
	public void createRegisterCard(){
		
	}
	
	public void createLogInCard(){
		
	}
	
	public void createGameCard(){
		
	}
	
	public void createRankingCard(){
		
	}
}
