package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.MainViewControllerC;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
		setSize(1000,550);
		
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
		
		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("Register");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 40));
		titol.add(Box.createVerticalStrut(15));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(65));
		
		JPanel register = new JPanel();
		register.setLayout(new BoxLayout(register, BoxLayout.PAGE_AXIS));
		
		JLabel jlname = new JLabel("Name");
		jlname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfname = new JTextField();
		JPanel jpname = new JPanel();
		jpname.setLayout(new GridLayout(1,4));
		jpname.add(new JPanel());
		jpname.add(jlname);
		jpname.add(jtfname);
		jpname.add(new JPanel());
		
		JLabel jllastname = new JLabel("Last Name");
		jllastname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtflastname = new JTextField();
		JPanel jplastname = new JPanel();
		jplastname.setLayout(new GridLayout(1,4));
		jplastname.add(new JPanel());
		jplastname.add(jllastname);
		jplastname.add(jtflastname);
		jplastname.add(new JPanel());
		
		JLabel jlnickname = new JLabel("Nickname");
		jlnickname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfnickname = new JTextField();
		JPanel jpnickname = new JPanel();
		jpnickname.setLayout(new GridLayout(1,4));
		jpnickname.add(new JPanel());
		jpnickname.add(jlnickname);
		jpnickname.add(jtfnickname);
		jpnickname.add(new JPanel());
		
		JLabel jlpassword = new JLabel("Password");
		jlpassword.setFont(new java.awt.Font("Geneva", 1, 14));
		JPasswordField jpfpassword = new JPasswordField();
		JPanel jppassword = new JPanel();
		jppassword.setLayout(new GridLayout(1,4));
		jppassword.add(new JPanel());
		jppassword.add(jlpassword);
		jppassword.add(jpfpassword);
		jppassword.add(new JPanel());
		
		JLabel jlage = new JLabel("Age");
		String [] agestring = { "1-10", "11-20", "21-30", "31-40", "41-50", "51-60", "61-70", "71-80", "81-90", "91-99"};
		JComboBox agelist = new JComboBox(agestring);
		JPanel jpage = new JPanel();
		jpage.setLayout(new GridLayout(1,4));
		jpage.add(new JPanel());
		jpage.add(jlage);
		jpage.add(agelist);
		jpage.add(new JPanel());
		
		jpnickname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jplastname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jppassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		register.add(jpname);
		register.add(jplastname);
		register.add(jpnickname);
		register.add(jppassword);
		register.add(jpage);
		
		register.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titol.add(register);
		
		jpRegisterCard.add(titol);
	}
	
	public void createLogInCard(){
		
		//jpLogInCard.setBackground( Color.BLUE );
		
		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("MemoTournament");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 50));
		titol.add(Box.createVerticalStrut(15));
		titol.add(nomtitol);	
		JLabel textinfo = new JLabel("Test your memory and see how quickly you can clear the board by matching up the pairs.");
		textinfo.setFont(new java.awt.Font("Geneva", 3, 16));
		textinfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(Box.createVerticalStrut(25));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		titol.add(textinfo);
		titol.add(Box.createVerticalStrut(75));
		
	
		JPanel login = new JPanel();
		//login.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		login.setLayout(new BoxLayout(login, BoxLayout.PAGE_AXIS));
		
		JLabel jlnickname = new JLabel("Nickname");
		jlnickname.setFont(new java.awt.Font("Geneva", 1, 14));
		JTextField jtfnickname = new JTextField();
		JPanel jpnickname = new JPanel();
		jpnickname.setLayout(new GridLayout(1,6));
		jpnickname.add(new JPanel());
		jpnickname.add(new JPanel());
		jpnickname.add(jlnickname);
		jpnickname.add(jtfnickname);
		jpnickname.add(new JPanel());
		jpnickname.add(new JPanel());
		
		
		JLabel jlpassword = new JLabel("Password");
		jlpassword.setFont(new java.awt.Font("Geneva", 1, 14));
		JPasswordField jpfpassword = new JPasswordField();
		JPanel jppassword = new JPanel();
		jppassword.setLayout(new GridLayout(1,6));
		jppassword.add(new JPanel());
		jppassword.add(new JPanel());
		jppassword.add(jlpassword);
		jppassword.add(jpfpassword);
		jppassword.add(new JPanel());
		jppassword.add(new JPanel());
		
		jpnickname.setAlignmentX(Component.CENTER_ALIGNMENT);
		jppassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.add(jpnickname);
		login.add(jppassword);
		
		login.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		titol.add(login);
		
		jpLogInCard.add(titol);
		
	}
	
	public void createInitialMenuCard(){

		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("Menu");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 40));
		titol.add(Box.createVerticalStrut(15));
		titol.add(nomtitol);
		
		jpInitialMenuCard.add(titol);
	}
	
	public void createSelectGameCard(){
		
		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("New Game");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 40));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(Box.createVerticalStrut(15));
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(65));
		
		JPanel mode = new JPanel();
		mode.setLayout(new BoxLayout(mode, BoxLayout.PAGE_AXIS));
		
		JLabel jlselectmode = new JLabel("Select Mode");
		jlselectmode.setFont(new java.awt.Font("Geneva", 1, 14));
		JRadioButton jrbmemoria = new JRadioButton("Memoria");
		JPanel jpselectmode = new JPanel();
		jpselectmode.setLayout(new GridLayout(1,4));
		jpselectmode.add(new JPanel());
		jpselectmode.add(jlselectmode);
		jpselectmode.add(jrbmemoria);
		jpselectmode.add(new JPanel());
		
		
		JRadioButton jrbcontra = new JRadioButton("Contrarellotge");
		JPanel jpselect = new JPanel();
		jpselect.setLayout(new GridLayout(1,4));
		jpselect.add(new JPanel());
		jpselect.add(new JPanel());
		jpselect.add(jrbcontra);
		jpselect.add(new JPanel());
		
		ButtonGroup groupmode = new ButtonGroup();
	    groupmode.add(jrbmemoria);
	    groupmode.add(jrbcontra);
		
		
		jpselectmode.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpselect.setAlignmentX(Component.CENTER_ALIGNMENT);
		mode.add(jpselectmode);
		mode.add(jpselect);
		
		mode.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(mode);
		titol.add(Box.createVerticalStrut(25));
		
		JPanel difficulty = new JPanel();
		difficulty.setLayout(new BoxLayout(difficulty, BoxLayout.PAGE_AXIS));
		
		JLabel jlselectdiff = new JLabel("Select Difficulty");
		jlselectdiff.setFont(new java.awt.Font("Geneva", 1, 14));
		JRadioButton jrbeasy = new JRadioButton("Easy");
		JPanel jpeasy = new JPanel();
		jpeasy.setLayout(new GridLayout(1,4));
		jpeasy.add(new JPanel());
		jpeasy.add(jlselectdiff);
		jpeasy.add(jrbeasy);
		jpeasy.add(new JPanel());
		
		
		JRadioButton jrbmedium = new JRadioButton("Medium");
		JPanel jpmedium = new JPanel();
		jpmedium.setLayout(new GridLayout(1,4));
		jpmedium.add(new JPanel());
		jpmedium.add(new JPanel());
		jpmedium.add(jrbmedium);
		jpmedium.add(new JPanel());
		
		JRadioButton jrbhard = new JRadioButton("Hard");
		JPanel jphard = new JPanel();
		jphard.setLayout(new GridLayout(1,4));
		jphard.add(new JPanel());
		jphard.add(new JPanel());
		jphard.add(jrbhard);
		jphard.add(new JPanel());
		
		ButtonGroup groupdiff = new ButtonGroup();
	    groupdiff.add(jrbeasy);
	    groupdiff.add(jrbmedium);
	    groupdiff.add(jrbhard);
		
		
	    jpeasy.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpmedium.setAlignmentX(Component.CENTER_ALIGNMENT);
		jphard.setAlignmentX(Component.CENTER_ALIGNMENT);
		difficulty.add(jpeasy);
		difficulty.add(jpmedium);
		difficulty.add(jphard);
		
		difficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(difficulty);
		
		jpSelectGameCard.add(titol);
	}
	
	public void createGameCard(){
		
	}
	
	public void createRankingCard(){
		
		JPanel title = new JPanel();
		JLabel nameTitle = new JLabel("Top 10 Ranking");
		nameTitle.setFont(new java.awt.Font("Geneva", 1, 40));
		title.add(nameTitle);	
		title.setLayout(new FlowLayout());
		jpRankingCard.add(title, BorderLayout.PAGE_START);
		
		JPanel pRanking = new JPanel();	
		GridLayout glRanking = new GridLayout(11,2);
		pRanking.setLayout(glRanking);
		
		//pRanking.add(top10);

		JLabel nickName = new JLabel("NICKNAME");
		nickName.setBorder(BorderFactory.createLineBorder(Color.black));
		pRanking.add(nickName);
		//pRanking.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel punctuation = new JLabel("PUNTUACIO");
		punctuation.setBorder(BorderFactory.createLineBorder(Color.black));
		pRanking.add(punctuation);
		
		
		for(int i=0; i<20; i++){
			nickName = new JLabel("POL");
			//pRanking.add(nickName);
			nickName.setBorder(BorderFactory.createLineBorder(Color.black));
			pRanking.add(nickName);
			i++;
			punctuation = new JLabel("350");
			punctuation.setBorder(BorderFactory.createLineBorder(Color.black));
			pRanking.add(punctuation);
			//pRanking.add(punctuation);
		}
			
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBorder(BorderFactory.createTitledBorder("Llista Paraules"));
		//pRanking.add(scrollPane);
		
		//pRanking.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//pRanking.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//pRanking.setLayout(new FlowLayout());
		jpRankingCard.setLayout(new BoxLayout(jpRankingCard,BoxLayout.PAGE_AXIS));
		//jpRankingCard.add(pRanking, BorderLayout.CENTER);
		jpRankingCard.add(pRanking);

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
