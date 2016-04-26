package model;

import javax.swing.SwingUtilities;

import controller.MainViewControllerC;
import view.MainViewClient;

public class MTMainClient {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				
				MainViewClient clientView = new MainViewClient();
				MainViewControllerC controller = new MainViewControllerC();
				
				clientView.registerController(controller);
				clientView.setVisible(true);
				
			}
			
		});
	}
}
