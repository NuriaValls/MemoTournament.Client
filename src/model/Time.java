package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Timer;

import network.ServerComunication;

public class Time {
	
	public Time(){
		Timer timerComp = new Timer(5000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ServerComunication.sendStart("START");
			}
		});
		timerComp.start();
	}
	
	public String getTime(){
		Date date = new GregorianCalendar().getTime();

		String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(date.getTime());
		
		return time;
	}
}