package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MainViewControllerC;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import model.MTMainClient;
import model.UserClient;

public class MainViewClient extends JFrame{	

	private static JPanel jpMenu;
	private static CardLayout cardLayout = new CardLayout();
	
	private static JPanel jpLogInCard = new JPanel();
	private static JPanel jpRegisterCard = new JPanel();
	private static JPanel jpInitialMenuCard = new JPanel();
	private static JPanel jpSelectGameCard = new JPanel();
	private static JPanel jpGameCard = new JPanel();
	private static JPanel jpRankingCard = new JPanel();
	
	private JPanel jpButtonMenu;
	
	private static JButton jbRegister = new JButton("Register");
	private static JButton jbNewGame = new JButton("New Game");
	private static JButton jbRanking = new JButton("Show Ranking");
	private static JButton jbLogOut = new JButton("Log Out");
	private static JButton jbBack = new JButton("Back");
	private static JButton jbGoToMenu = new JButton("Go to Menu");
	private static JButton jbStartGame = new JButton("Start Game");
	
	public MainViewClient(){
		setTitle("Memory Torunament -Client-");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(400,300);
		
		createLogInCard();
		createRegisterCard();
		createInitialMenuCard();
		createSelectGameCard();
		createGameCard();
		createRankingCard();
		
		jpMenu = new JPanel();
		jpMenu.setLayout(cardLayout);
		
		jpMenu.add(jpLogInCard, "1");
		jpMenu.add(jpRegisterCard, "2");
		jpMenu.add(jpInitialMenuCard, "3");
		jpMenu.add(jpSelectGameCard, "4");
		jpMenu.add(jpGameCard, "5");
		jpMenu.add(jpRankingCard, "6");
		
		jpButtonMenu = new JPanel();
		
		jpButtonMenu.add(jbGoToMenu);
		jpButtonMenu.add(jbRegister);
		jpButtonMenu.add(jbBack);
		jpButtonMenu.add(jbNewGame);
		jpButtonMenu.add(jbStartGame);
		jpButtonMenu.add(jbRanking);
		jpButtonMenu.add(jbLogOut);
		
		jbBack.setVisible(false);
		jbNewGame.setVisible(false);
		jbStartGame.setVisible(false);
		jbRanking.setVisible(false);
		jbLogOut.setVisible(false);
		
		jpButtonMenu.setVisible(true);
		
		add(jpButtonMenu, BorderLayout.SOUTH);
		add(jpMenu, BorderLayout.NORTH);
		pack();
	}
	
	public void registerController(MainViewControllerC actionListener){
		jbRegister.addActionListener(actionListener);
		//jbLogIn.addActionListener(actionListener);
		//jbGuest.addActionListener(actionListener);
		jbNewGame.addActionListener(actionListener);
		jbRanking.addActionListener(actionListener);
		jbLogOut.addActionListener(actionListener);
	}
	
	public void createRegisterCard(){
		
	}
	
	public void createLogInCard(){
		
	}
	
	public void createInitialMenuCard(){
		
	}
	
	public void createSelectGameCard(){
		
	}
	
	public void createGameCard(){
		
	}
	
	public void createRankingCard(){
		
	}
}
