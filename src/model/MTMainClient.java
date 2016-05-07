package model;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import controller.MainViewControllerC;
import view.MainViewClient;

public class MTMainClient {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				
				MainViewClient clientView = new MainViewClient();
				Time time = new Time();
				MainViewControllerC controller = new MainViewControllerC(clientView);
				
				clientView.registerController(controller);
				clientView.setVisible(true); //asdf
				
			}
		});
	}
}
