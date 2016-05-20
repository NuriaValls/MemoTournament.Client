package view;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Dialog extends JDialog{

	public static void DialogOK(String message){
		JOptionPane.showMessageDialog(null,message, "Information message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void DialogKO(String message){
		JOptionPane.showMessageDialog(null,message, "An Error Has Ocurred", JOptionPane.ERROR_MESSAGE);
	}
}
