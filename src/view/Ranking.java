package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;


public class Ranking extends JFrame{
	private JTextArea jta;
	
	public Ranking(){
		showRanking();
	}

	public void showRanking() {
		//suposant  endreçar array endreçat.
		
		//Panell del ranking:
		JPanel panel = new JPanel(new GridLayout(1,1));	
		setJta(new JTextArea());
		jta.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(jta);
		scrollPane.setBorder(BorderFactory.createTitledBorder("XXXXXXXXXX"));
		panel.add(scrollPane);
		this.getContentPane().add(panel, BorderLayout.CENTER);
		setTitle("RANKING");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		
		/*for(int i = 0;i<ARRAY();i++ ){
			jta.append(ARRAY.GETINFO());
		}*/
	}

	public JTextArea getJta() {
		return jta;
	}

	public void setJta(JTextArea jta) {
		this.jta = jta;
	}
}
