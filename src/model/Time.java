package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;

import network.ServerComunication;

public class Time extends Thread{
	
	private Timer timerComp;
	private Timer timerRank;
	
	public Time(){
		timerComp = new Timer(5000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServerComunication.sendStart("START");
			}
		});
		
		timerComp.start();
		
		timerRank = new Timer(10000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServerComunication.sendStart("RANK");
			}
		});
		
	}
	
	public void startTimerRank(){
		timerRank.start();
	}
	
	public void stopTimerComp(){
		timerComp.stop();
	}
	
	public String getTime(){
		Date date = new GregorianCalendar().getTime();

		String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(date.getTime());
		
		return time;
	}
}