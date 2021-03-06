package model;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.GameWindowController;
import controller.MainViewControllerC;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Classe que s'encarrega de generar el taulell de joc i coordinar la logica de la partida.
 * @author Luis Recolons
 * 
 */
public class Board extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<Card> cards;
    private Card selectedCard;
    private Card c1;
    private Card c2;
    private Timer t;
    private static Timer ch;
    private Timer ait;
    private Timer aith;
    private String[] imagename;
    private int numcards;
    private int score;
    private int aiscore;
    private LinkedList<Card> memory;
    private boolean[] matches;
    private static int timegame;
    
    private GameWindowController controller;
    /**
     * Constructor de la classe. Crea el taulell, distribuint-ne les cartes seguint els parametres dels diferents nivells de dificultat. Es declaran els timers que controlan les accions dels torns.
     * @param difficulty serveix per determinar diferents caracteristiques de la partida.
     * @param concentration indica si es juga Memoria o Concentracio.
     * @param ai indica si es juga contra la Maquina.
     */
    public Board(int difficulty, boolean concentration, boolean ai, GameWindowController controller){
    	
    	this.controller = controller;
    	
    	int pairs = assignPairs(difficulty); //pairs depending on the difficulty level
        numcards = pairs*2;
        
        memory = new LinkedList<Card>();
        List<Card> cardsList = new ArrayList<Card>();
        List<Integer> cardVals = new ArrayList<Integer>();
        
        matches = new boolean[pairs];
        for (int j = 0; j < pairs; j++){
        	matches[j] = false;
        }
    
        

        for (int i = 0; i < pairs; i++){ //assigns the numbers as the elements of the cards
            cardVals.add(i);
            cardVals.add(i);
        } 
        
        switch (difficulty){ //assigns a set of images
        	case 1: setImagesEasy();
        			break;
        	case 2: setImagesNormal();
        			break;
        	case 3: setImagesHard();
        			break;
        	default:		
					break;
        }
       
        Collections.shuffle(cardVals); //suffles the list of elements
        
        int idCard = 0;
        for (int val : cardVals){
            Card c = new Card(val, imagename[val], idCard, difficulty);
            c.addActionListener(new ActionListener(){ //creates the button itself
            	public void actionPerformed(ActionEvent ae){ 
            		selectedCard = c;
            		if (concentration == true){
            			doConTurn();
            		}else{
            			doTurn(ai, difficulty);
            		}
            		
            	}  	
            });
            cardsList.add(c);
            c.hideImage();
            idCard++;
        }
        
        this.cards = cardsList;
        
        t = new Timer(750, new ActionListener(){ //timer for delay when showing cards
            public void actionPerformed(ActionEvent ae){
                checkCards(concentration, ai, true);
                if(ai == true && numcards!=0){
                	doAiTurn(difficulty);
                }
            }
        });
        
        t.setRepeats(false);
           
        
        ait = new Timer(750, new ActionListener(){ //timer for delay when showing cards
            public void actionPerformed(ActionEvent ao){
                checkCards(false, true, false);
            }
        });
        ait.setRepeats(false);
        
        Time.startGameTimer(setTimer(difficulty));
    	
        aith = new Timer(750, new ActionListener(){ //timer for delay when showing cards
            public void actionPerformed(ActionEvent au){
                checkCards(false, true, false);
            }
        });
        
        aith.setRepeats(false);

        //creates the board 
        Container pane = getContentPane();
        createGrid(difficulty, pane);  //creates the grid depending on the difficulty level
        
        for (Card c : cards){
            pane.add(c);
        }
        
        if(concentration){ //shows the board if concentration mode is played
        	showBoard(difficulty);
        }
        
        //setTitle("Memory Match");
        
    }
    /**
	 * Para el cronometre de la partida.
	 */
    public static void stopTimer(){
    	ch.stop();
    }
    /**
     * Gira la carta seleccionada per l'usuari, controlant si es la primera o la segona del torn
     * @param ai indica si es juga amb IA
     * @param difficulty indica el nivell de dificultat
     */ 
    public void doTurn(boolean ai, int difficulty){
        if (c1 == null && c2 == null){ //player chooses c1
            c1 = selectedCard;
            c1.showImage();
        }

        if (c1 != null && c1 != selectedCard && c2 == null){ //player chooses c2
            c2 = selectedCard;
            c2.showImage();
            t.start();
        }
    }
    /**
     * S'encarrega de cridar el Turn segons la dificultat per que sigui executat per la maquina
     * @param difficulty serveix per triar quin tipus de Turn s'ha de realitzar
     */
    public void doAiTurn(int difficulty){
    	
    	switch (difficulty){
    		case 1: easyTurn();
    				break;
    			
    		case 2: normalTurn(15);
    				break;
    			
    		case 3: hardTurn(21);
    				break;
    	}
    }
    /**
     * Fa un gir de cartes en dificultat facil, escollides aleatoriament
     */
    public void easyTurn(){
    	int randCard1;
    	int randCard2;
    	Card c;
    	randCard1 = (int)(Math.random()*numcards);
		c = cards.get(randCard1);
    	selectedCard = c;
        c1 = selectedCard;
        c1.showImage();
        
    	do{
    		randCard2 = (int)(Math.random()*numcards);
    	}while (randCard1 == randCard2);
		c = cards.get(randCard2);
    	selectedCard = c;
        c2 = selectedCard;
        c2.showImage();

        ait.start();
    }    	
    /**
     * Fa un gir en dificultat normal, pel que pot ser un facil o un dificil en una proporcio 50/50
     * @param pairs nombre de parelles de cartes
     */
    public void normalTurn(int pairs){
    	int rand;
    	
    	rand = (int)(Math.random()*10);
    	
    	if (rand >= 5){
    		hardTurn(pairs);
    	}else{
    		easyTurn();
    	}
    }
    /**
     * Fa un gir en dificultat dificil, tenint en compte la memoria que es va actualitzant amb la informacio que rep durant la partida.
     * @param pairs nombre de parelles de cartes
     */
    public void hardTurn(int pairs){
    	int i, j=-1, randCard1, randCard2;
    	boolean match = false, first = false;
    	Card c;
    	
    	for (i = 0; i < pairs; i++){
    		if (matches[i] == true){
    			match = true;
    			j = i;
    		}
    	}
    	
    	if(match == true){
    		for (Card m : cards){
    			if (m.getId() == j && !first){
    				c1 = m;
    				selectedCard = m;
    				c1.showImage();
    				first = true;
    			}
    			if (m.getId() == j && first){
    				c2 = m;
    				selectedCard = m;
    				c2.showImage();
    			}
    		}
    		
    	}else{
        	randCard1 = (int)(Math.random()*numcards);
    		c = cards.get(randCard1);
        	selectedCard = c;
            c1 = c;
            c1.showImage();
    		
            for (j = 0; j < (memory.size()); j++){
    			c = memory.get(j);
    			if (c1.compareTo(c) == 1){
    				c2 = c;
    				selectedCard = c;
    				c2.showImage();
    				match = true;
    			}
    		}
    		if (match == false){
    	    	do{
    	    		randCard2 = (int)(Math.random()*numcards);
    	    	}while (randCard1 == randCard2);
    			c = cards.get(randCard2);
    	    	selectedCard = c;
    	        c2 = c;
    	        c2.showImage();
    		}
            
    	}

        aith.start();
    }
    /**
     * Fa un gir de cartes en el mode concentracio, sense mostrar-les
     */
    public void doConTurn(){
        if (c1 == null && c2 == null){ //player chooses c1
            c1 = selectedCard;
        }

        if (c1 != null && c1 != selectedCard && c2 == null){ //player chooses c2
            c2 = selectedCard;
            t.start();
        }
    }
    /**
     * S'encarrega de comprovar si la parella de cartes escollida es correcte. Si ho es s'encarrega de marcar-les com seleccionades i actualitzar la puntuacio i la memoria si es juga amb IA, o si no ho son les torna a amagar i actualitza la memoria en el cas que es jugui contra la maquina.
     * @param concentration indica si es juga el mode Concentracio.
     * @param ai indica si es juga contra la maquina.
     * @param player indica si la seleccio de cartes ho la ha feta el jugador o la maquina.
     */
    public void checkCards(boolean concentration, boolean ai, boolean player){
    	boolean match = false;
    	Card aux;
        if (c1.getId() == c2.getId() /*&& c1.getIdCard() != c2.getIdCard()*/){ //if match 
            if(concentration == true){
            	c1.showImage();
            	c2.showImage();
            }
        	c1.setEnabled(false);  //disables the button
            c2.setEnabled(false);
            c1.setMatched(true);  //flags the button
            c2.setMatched(true);
            if (player){
            	score++;
            }else{
            	aiscore++;
            }
            Game.setScore(score, aiscore);
            numcards = numcards - 2;
            memory.remove(c1);
            cards.remove(c1);
            memory.remove(c2);
            cards.remove(c2);
            matches[c1.getId()] = false;
            if (this.gameWon()){
                Time.stopGameTimer();
            	if(ai == true){
                	if(score == aiscore){
                		//JOptionPane.showMessageDialog(this, "Draw!");
                		JOptionPane pane = new JOptionPane("Draw!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        JDialog dialog = pane.createDialog(new JFrame(), "Game ended!");
                        dialog.setModalityType(JDialog.ModalityType.MODELESS);
                        dialog.addWindowListener(controller);
                        dialog.setVisible(true);
                	}
                	if(score < aiscore){
                		JOptionPane pane = new JOptionPane("You've lost!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        JDialog dialog = pane.createDialog(new JFrame(), "Game ended!");
                        dialog.setModalityType(JDialog.ModalityType.MODELESS);
                        dialog.addWindowListener(controller);
                        dialog.setVisible(true);
                	}
                	if(score > aiscore){
                		JOptionPane pane = new JOptionPane("You've won!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        JDialog dialog = pane.createDialog(new JFrame(), "Game ended!");
                        dialog.setModalityType(JDialog.ModalityType.MODELESS);
                        dialog.addWindowListener(controller);
                        dialog.setVisible(true);
                	}
                }else{
                	JOptionPane pane = new JOptionPane("You've completed the level!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
                    JDialog dialog = pane.createDialog(new JFrame(), "Game ended!");
                    dialog.setModalityType(JDialog.ModalityType.MODELESS);
                    dialog.addWindowListener(controller);
                    dialog.setVisible(true);
                }
            	MainViewControllerC.gameEnded(score);
            }
        }
        else{ //if not match 
            c1.hideImage(); 
            c2.hideImage();
            if (ai){
            	if (!memory.isEmpty()){
            		for (Card c : memory){
            			if (c1.compareTo(c) == 1){
            				matches[c1.getId()] = true;
            				match = true;
            			}
            		}
            	}
            	if (!match && !memory.contains(c1)){
            		if (memory.size() > 10){
            			aux = memory.removeFirst();
            		}
            		memory.add(c1);
           		}
            	
            	match = false;
            	
            	if (!memory.isEmpty()){
            		for (Card g : memory){
            			if (c2.compareTo(g) == 1){
            				matches[c2.getId()] = true;
            				match = true;
            			}
            		}
            	}
            	if (!match && !memory.contains(c2)){
            		if (memory.size() > 10){
            			aux = memory.removeFirst();
            		}
            		memory.add(c2);
           		}
            }
        }
        c1 = null; //reset c1 and c2
        c2 = null;
    }
    /**
     * comprova si es s'ha acabat la partida.
     * @return true si totes les parelles estan encertades
     */
    public boolean gameWon(){
        return this.cards.stream().allMatch(c -> c.getMatched()); //game finished
    }
    /**
     * Asigna el numero de parelles segons la dificultat.
     * @param difficulty indica el nivell de dificultat que es juga.
     * @return el numero de parelles
     */
    public int assignPairs(int difficulty){
    	switch (difficulty){
    		case 1: return 10; //easy 10 pairs
    
    		case 2: return 15; //normal 15 pairs
    				
    		case 3: return 21; //hard 21 pairs
    		
    		default: JOptionPane.showMessageDialog(this, "An error has ocurred when determining the difficulty level");
					System.exit(0);
					return -1;
    				
    	}
    }
    /**
     * Crea el taulell segons la dificultat.
     * @param difficulty indica la dificultat de la partida.
     * @param pane la graella de les cartes.
     */
    public void createGrid(int difficulty, Container pane){
    	switch (difficulty){
			case 1: pane.setLayout(new GridLayout(4, 5));//easy 20 
					break;
					
			case 2: pane.setLayout(new GridLayout(5, 6)); //normal 30
					break;
					
			case 3: pane.setLayout(new GridLayout(6, 7)); //hard 42
					break;
					
    		default: JOptionPane.showMessageDialog(this, "An error has ocurred when determining the difficulty level");
    				System.exit(0);
    	}
    }
    /**
     * Asigna les imatges a les cartes en dificultat facil.
     */
    public void setImagesEasy(){ //easy images
    	imagename = new String[] {"AUSTRALIA","BRAZIL","CANADA","CHINA","GREATBRITAIN","GREECE",
    			"JAMAICA","JAPAN","SWEDEN","USA"};
    }
    /**
     * Asigna les imatges a les cartes en dificultat normal.
     */
    public void setImagesNormal(){ //normal images
    	imagename = new String[] {"ADIDAS","APPLE","COCACOLA","DISNEY","GOOGLE","INTEL","LEGO",
    			"MCDONALDS","MERCEDES","MICROSOFT","NIKE","SHELL","UBISOFT","VOLKSWAGEN","WARNER"};
    }
    /**
     * Asigna les imatges a les cartes en dificultat dificil.
     */
    public void setImagesHard(){ //hard images
    	imagename = new String[] {"2_of_clubs","2_of_hearts","3_of_diamonds","4_of_clubs","4_of_hearts",
    			"5_of_spades","6_of_clubs","7_of_diamonds","7_of_spades","8_of_hearts","9_of_spades",
    			"10_of_clubs","10_of_diamonds","ace_of_spades","ace_of_hearts","jack_of_hearts","king_of_clubs",
    			"king_of_diamonds","queen_of_diamonds","queen_of_spades","red_joker"};
    }
    /**
     * Ense�a el taulell en el mode Concentracio un temps determinat per la dificultat.
     * @param difficulty indica el nivell de dificultat de la partida.
     */
    public void showBoard(int difficulty){ //shows board in the concentration mode
    	Timer con;
    	for (Card c : cards){ //shows cards
            c.showImage();            
        }
    	switch(difficulty){ //for a time depending on the difficulty
    		case 1: con = new Timer(10000, new ActionListener(){ //10 sec/10 pairs = 1 sec per pair
    					public void actionPerformed(ActionEvent e){
    						for (Card c : cards){
    				            c.hideImage();            
    				        }
    					}
    				});
    				con.setRepeats(false);
    				con.start();
    				break;
    		case 2: con = new Timer(12000, new ActionListener(){ //12 sec/15 pairs = 0.8 sec per pair
						public void actionPerformed(ActionEvent e){
    				   
							for (Card c : cards){
    				            c.hideImage();            
    				        }
						}
					});
    				con.setRepeats(false);
    				con.start();
					break;
    		case 3: con = new Timer(13000, new ActionListener(){ //13 sec/21 pairs = 0.62 sec per pair
						public void actionPerformed(ActionEvent e){
    				    	for (Card c : cards){
    				            c.hideImage();            
    				        }
						}
					});
    				con.setRepeats(false);
    				con.start();
					break;    		
    	}
    }
    /**
     * Asigna el temps de la partida segons la dificultat.
     * @param difficulty indica la dificultat de la partida
     * @return el temps maxim que durara la partida
     */
    public int setTimer(int difficulty){ //time limit depending on the difficulty
    	switch (difficulty){
    		case 1: return 90000; //9 sec for pair
    		case 2: return 120000; //8 sec for pair
    		case 3: return 147000; //7 sec for pair
    		default: JOptionPane.showMessageDialog(this, "An error has ocurred when determining the difficulty level");
            		System.exit(0);
    				return -1;
    	}
    }
    /**
     * Retorna la puntuacio de la partida.
     * @return la puntuacio de la partida.
     */
    public int getScore(){
    	return score;
    }
}

