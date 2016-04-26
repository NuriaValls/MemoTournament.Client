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
	private static JButton jbGuest = new JButton("Enter as a Guest");
	private static JButton jbNewGame = new JButton("New Game");
	private static JButton jbRanking = new JButton("Show Ranking");
	private static JButton jbLogOut = new JButton("Log Out");
	private static JButton jbBack = new JButton("Back");
	private static JButton jbGoToMenu = new JButton("Log In");
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
		jpButtonMenu.add(jbGuest);
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
	}
	
	public void registerController(MainViewControllerC actionListener){
		jbGoToMenu.addActionListener(actionListener);
		jbRegister.addActionListener(actionListener);
		jbGuest.addActionListener(actionListener);
		jbBack.addActionListener(actionListener);
		jbNewGame.addActionListener(actionListener);
		jbStartGame.addActionListener(actionListener);
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
	
	public static void showRegister(){
		cardLayout.show(jpMenu, "2");
		
		jbBack.setVisible(true);
		jbGoToMenu.setText("Register Profile");
		
		jbGuest.setVisible(false);
		jbRegister.setVisible(false);
	}
	public static void showInitialMenu(){
		cardLayout.show(jpMenu, "3");
		
		jbBack.setVisible(false);
		jbGoToMenu.setVisible(false);
		jbGuest.setVisible(false);
		jbRegister.setVisible(false);
		jbStartGame.setVisible(false);
		
		jbNewGame.setVisible(true);
		jbRanking.setVisible(true);
		jbLogOut.setVisible(true);
	}
	
	public static void showRanking(){
		cardLayout.show(jpMenu, "6");
		
		jbNewGame.setVisible(false);
		jbRanking.setVisible(false);
		jbLogOut.setVisible(false);
		
		jbGoToMenu.setText("Menu");
		jbGoToMenu.setVisible(true);
	}
	
	public static void showLogIn(){
		cardLayout.show(jpMenu, "1");
		
		jbNewGame.setVisible(false);
		jbRanking.setVisible(false);
		jbLogOut.setVisible(false);
		jbBack.setVisible(false);
		
		jbGoToMenu.setText("Log In");
		jbGoToMenu.setVisible(true);
		jbRegister.setVisible(true);
		jbGuest.setVisible(true);
	}
	
	public static void showSelectGame(){
		cardLayout.show(jpMenu, "4");
		
		jbNewGame.setVisible(false);
		jbRanking.setVisible(false);
		jbLogOut.setVisible(false);
		
		jbGoToMenu.setText("Back to Menu");
		jbGoToMenu.setVisible(true);
		jbStartGame.setVisible(true);
	}
	
	public static void showGame(){
		cardLayout.show(jpMenu, "5");
		
		jbStartGame.setVisible(false);
		
		jbGoToMenu.setText("End Game");
		jbGoToMenu.setVisible(true);
	}
}
