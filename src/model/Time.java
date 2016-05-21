package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;

import controller.MainViewControllerC;
import network.ServerComunication;

public class Time extends Thread{
	
	private MainViewControllerC controller;
	
	private Timer timerComp;
	private Timer timerRank;
	
	private Timer countdownTimer;
	private int countdown;
	private Timer competitionTimer;
	private int competition;
	private boolean started = false;
	
	public Time(){
		timerComp = new Timer(1000, new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.sendStartServerC("START");
			}
		});
		
		timerComp.start();
		
		timerRank = new Timer(10000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.sendRankServerC("RANK");
			}
		});
		
		countdownTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (countdown > 0){
					countdown--;
					controller.refreshTime(countdown, false);
				}else{
					competitionTimer.start();
					countdownTimer.stop();
					controller.makeDialog("The competition has started!", true);
					started = true;
				}
			}
			
		});
		
		competitionTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (competition > 0){
					competition--;
					controller.refreshTime(competition, true);
				}else{
					competitionTimer.stop();
				}
			}
			
		});
	}
	
	public void registerController(MainViewControllerC controller){
		this.controller = controller;
	}
	
	public void startTimerRank(){
		timerRank.start();
	}
	
	public void stopTimerComp(){
		timerComp.stop();
	}
	
	public void startCountdownTimer(int countdown, int competition){
		this.countdown = countdown-3;
		this.competition = competition+2;
		countdownTimer.start(); 
	}
	
	public String getTime(){
		Date date = new GregorianCalendar().getTime();

		String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(date.getTime());
		
		return time;
	}
	
	public boolean getStarted(){
		return started;
	}
}