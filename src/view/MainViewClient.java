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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.MainViewControllerC;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
	
	//atributs menu
	//s'ha d'afegir la info tot en una label per que quedi tot seguit.
	//important posar espai abans i despres del text (per que el borde no quedi tan enganxat)
	private JLabel userinfo = new JLabel("   Hello  (nomusuari),  your punctuation is: (puntuacio)   ");
	private JLabel comptime = new JLabel("   The competiton time is: (temps competicio)   ");
	
	//atributs de log in
	private JTextField jtfnickname;
	private JPasswordField jpfpassword;
	
	//atributs de register
	private JTextField jtfnicknameR;
	private JPasswordField jpfpasswordR;
	private JTextField jtfname;
	private JTextField jtflastname;
	
	//atributs ranking
	private JPanel panell;
	private JTable table;
	private String[] columnNames = {"NickName","Score",};
	private JPanel title;
	
	//atributs joc
	private JPanel jpgame;
	
	public MainViewClient(){
		setTitle("Memory Torunament -Client-");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,550);
		setLocationRelativeTo(null);
		
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
		jtfname = new JTextField();
		JPanel jpname = new JPanel();
		jpname.setLayout(new GridLayout(1,4));
		jpname.add(new JPanel());
		jpname.add(jlname);
		jpname.add(jtfname);
		jpname.add(new JPanel());
		
		JLabel jllastname = new JLabel("Last Name");
		jllastname.setFont(new java.awt.Font("Geneva", 1, 14));
		jtflastname = new JTextField();
		JPanel jplastname = new JPanel();
		jplastname.setLayout(new GridLayout(1,4));
		jplastname.add(new JPanel());
		jplastname.add(jllastname);
		jplastname.add(jtflastname);
		jplastname.add(new JPanel());
		
		JLabel jlnickname = new JLabel("Nickname");
		jlnickname.setFont(new java.awt.Font("Geneva", 1, 14));
		jtfnicknameR = new JTextField();
		JPanel jpnickname = new JPanel();
		jpnickname.setLayout(new GridLayout(1,4));
		jpnickname.add(new JPanel());
		jpnickname.add(jlnickname);
		jpnickname.add(jtfnicknameR);
		jpnickname.add(new JPanel());
		
		JLabel jlpassword = new JLabel("Password");
		jlpassword.setFont(new java.awt.Font("Geneva", 1, 14));
		jpfpasswordR = new JPasswordField();
		JPanel jppassword = new JPanel();
		jppassword.setLayout(new GridLayout(1,4));
		jppassword.add(new JPanel());
		jppassword.add(jlpassword);
		jppassword.add(jpfpasswordR);
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
	
	public String getRegNickname(){
		return jtfnicknameR.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getRegPasword(){
		return jpfpasswordR.getText();
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
		jtfnickname = new JTextField();
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
		jpfpassword = new JPasswordField();
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
	
	public String getLogNickname(){
		return jtfnickname.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getLogPasword(){
		return jpfpassword.getText();
	}
	
	public void createInitialMenuCard(){

		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
		titol.add(Box.createVerticalStrut(15));
	
		JLabel nomtitol = new JLabel("Menu");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 40));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(15));
		userinfo.setFont(new java.awt.Font("Geneva", 1, 16));
		Border border = BorderFactory.createLineBorder(Color.ORANGE, 2);
		userinfo.setBackground(Color.WHITE);
		userinfo.setOpaque(true);
		userinfo.setBorder(border);
		userinfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(userinfo);
		
		titol.add(Box.createVerticalStrut(20));
		comptime.setFont(new java.awt.Font("Geneva", 1, 16));
		Border border2 = BorderFactory.createLineBorder(Color.CYAN, 2);
		comptime.setBackground(Color.WHITE);
		comptime.setOpaque(true);
		comptime.setBorder(border2);
		comptime.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(comptime);
		
		jpInitialMenuCard.add(titol);
	}
	
	public void createSelectGameCard(){
		
		JPanel titol = new JPanel();
		
		titol.setLayout(new BoxLayout(titol, BoxLayout.PAGE_AXIS));
	
		JLabel nomtitol = new JLabel("New Game");
		nomtitol.setFont(new java.awt.Font("Geneva", 1, 40));
		nomtitol.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(Box.createVerticalStrut(25));
		titol.add(nomtitol);
		titol.add(Box.createVerticalStrut(55));
		
	    JPanel mode = new JPanel();
		mode.setLayout(new BoxLayout(mode, BoxLayout.PAGE_AXIS));
		
		JLabel jlselectmode = new JLabel("Select Mode");
		jlselectmode.setFont(new java.awt.Font("Geneva", 1, 14));
		JRadioButton jrbmemoria = new JRadioButton("Memory");
		JPanel jpselectmode = new JPanel();
		jpselectmode.setLayout(new GridLayout(1,4));
		jpselectmode.add(new JPanel());
		jpselectmode.add(jlselectmode);
		jpselectmode.add(jrbmemoria);
		jpselectmode.add(new JPanel());
		
		
		JRadioButton jrbconcentracio = new JRadioButton("Concentration");
		JPanel jpselect = new JPanel();
		jpselect.setLayout(new GridLayout(1,4));
		jpselect.add(new JPanel());
		jpselect.add(new JPanel());
		jpselect.add(jrbconcentracio);
		jpselect.add(new JPanel());
		
		ButtonGroup groupmode = new ButtonGroup();
	    groupmode.add(jrbmemoria);
	    groupmode.add(jrbconcentracio);
		
		
		jpselectmode.setAlignmentX(Component.CENTER_ALIGNMENT);
		jpselect.setAlignmentX(Component.CENTER_ALIGNMENT);
		mode.add(jpselectmode);
		mode.add(jpselect);
		
		mode.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(mode);
		titol.add(Box.createVerticalStrut(25));
		
		
		JPanel game = new JPanel();
		game.setLayout(new BoxLayout(game, BoxLayout.PAGE_AXIS));
		
		JLabel jlselectgame = new JLabel("Select Game(AI)");
		jlselectgame.setFont(new java.awt.Font("Geneva", 1, 14));
		JRadioButton jrbmachine = new JRadioButton("Machine");
		
		JPanel jpselectgame = new JPanel();
		jpselectgame.setLayout(new GridLayout(1,4));
		jpselectgame.add(new JPanel());
		jpselectgame.add(jlselectgame);
		jpselectgame.add(jrbmachine);
		jpselectgame.add(new JPanel());
		
		
		JRadioButton jrbtimetrial = new JRadioButton("Time Trial");
		
		JPanel jptimetrial = new JPanel();
		jptimetrial.setLayout(new GridLayout(1,4));
		jptimetrial.add(new JPanel());
		jptimetrial.add(new JPanel());
		jptimetrial.add(jrbtimetrial);
		jptimetrial.add(new JPanel());
		
		ButtonGroup groupgame2 = new ButtonGroup();
	    groupgame2.add(jrbmachine);
	    groupgame2.add(jrbtimetrial);
		
		
		jpselectgame.setAlignmentX(Component.CENTER_ALIGNMENT);
		jptimetrial.setAlignmentX(Component.CENTER_ALIGNMENT);
		game.add(jpselectgame);
		game.add(jptimetrial);
		
		game.setAlignmentX(Component.CENTER_ALIGNMENT);
		titol.add(game);
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
		jpgame = new JPanel();
	}
	
	public JPanel getJpgame(){
		return jpgame;
	}
	
	public void createRankingCard(){
		
		title = new JPanel();
		title.setLayout(new BoxLayout(title, BoxLayout.PAGE_AXIS));
		JLabel nameTitle = new JLabel("Top 10 Ranking");
		nameTitle.setFont(new java.awt.Font("Geneva", 1, 34));	
		title.add(Box.createVerticalStrut(15));
		nameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(nameTitle);
		title.add(Box.createVerticalStrut(15));
		
		panell = new JPanel();
		
		String [][] mTopTen = new String [11][2];
		table = new JTable(mTopTen, columnNames);
		
		table.setPreferredSize(new Dimension(500, 250));
		table.setOpaque(false);
		table.setAlignmentX(Component.CENTER_ALIGNMENT);
		panell.add(table);
		panell.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.add(panell);
		jpRankingCard.add(title);

		
	}
	
	public void refreshRanking(String sTopTen){
		String matrix[][] = new String [11][2];
		String[] users = sTopTen.split("#");
		int j = 0;
		for(int i=0;i<users.length;i++){				
			String[] aux = users[i].split("/");
			matrix[j] = aux;
			j++;
		}
		DefaultTableModel model = new DefaultTableModel(matrix,columnNames);
		table.setModel(model);
		model.fireTableDataChanged();
	}
	
	public void showRegister(){
		jtfnicknameR.setText("");
		jpfpasswordR.setText("");
		jtfname.setText("");
		jtflastname.setText("");
		
		cardLayout.show(jpMenu, "2");
		
		jbBack.setVisible(true);
		jbGoToMenu.setText("Register Profile");
		
		jbGuest.setVisible(false);
		jbRegister.setVisible(false);
	}
	
	public void showInitialMenu(){
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
	
	public void showRanking(){
		cardLayout.show(jpMenu, "6");
		
		jbNewGame.setVisible(false);
		jbRanking.setVisible(false);
		jbLogOut.setVisible(false);
		
		jbGoToMenu.setText("Menu");
		jbGoToMenu.setVisible(true);
	}
	
	public void showLogIn(){
		jtfnickname.setText("");
		jpfpassword.setText("");
		
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
	
	public void showSelectGame(){
		cardLayout.show(jpMenu, "4");
		
		jbNewGame.setVisible(false);
		jbRanking.setVisible(false);
		jbLogOut.setVisible(false);
		
		jbGoToMenu.setText("Back to Menu");
		jbGoToMenu.setVisible(true);
		jbStartGame.setVisible(true);
	}
	
	public void showGame(){
		cardLayout.show(jpMenu, "5");
		
		jbStartGame.setVisible(false);
		
		jbGoToMenu.setText("End Game");
		jbGoToMenu.setVisible(true);
	}
	
	public void makeDialog(String message, boolean type){
		if(type){
			Dialog.DialogOK(message);
		}else{
			Dialog.DialogKO(message);
		}
	}
}
