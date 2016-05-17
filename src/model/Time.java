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
					System.out.println(countdown);
				}else{
					competitionTimer.start();
					countdownTimer.stop();
					controller.makeDialog("The competition has started!", true);
				}
			}
			
		});
		
		competitionTimer = new Timer(1000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (competition > 0){
					competition--;
					System.out.println("comp: "+competition);
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
		this.countdown = countdown;
		this.competition = competition;
		countdownTimer.start();
	}
	
	public String getTime(){
		Date date = new GregorianCalendar().getTime();

		String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(date.getTime());
		
		return time;
	}
}