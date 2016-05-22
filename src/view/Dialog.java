package view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Classe que permet printar dialegs amb un missatge concret i una tipologia concreta.
 */
public class Dialog extends JDialog{
	/**
	 * Metode que printa un dialeg amb missatge informatiu amb la string rebuda. 
	 */
	public static void DialogOK(String message){
		//JOptionPane.showMessageDialog(null,message, "Information message", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = pane.createDialog(new JFrame(), "Has Perdut!");
        dialog.setModalityType(JDialog.ModalityType.MODELESS);
        dialog.setVisible(true);
	}
	/**
	 * Metode que printa un dialeg d'error amb el missatge rebut per referencia.
	 */
	public static void DialogKO(String message){
		JOptionPane.showMessageDialog(null,message, "An Error Has Ocurred", JOptionPane.ERROR_MESSAGE);
	}
}
