package model;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import network.ServerComunication;
import controller.MainViewControllerC;
import view.MainViewClient;

public class MTMainClient {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				
				Configuration config = new Configuration();
				if (config.configurate()){
					
					MainViewClient clientView = new MainViewClient();
					Time time = new Time();
					ServerComunication serverCom = new ServerComunication(time, config.getIp(), config.getPortServer());
					MainViewControllerC controller = new MainViewControllerC(clientView,serverCom,time);
					
					clientView.registerController(controller);
					serverCom.registerController(controller);
					time.registerController(controller);
					clientView.setVisible(true);
					
				}
			}
		});
	}
}
