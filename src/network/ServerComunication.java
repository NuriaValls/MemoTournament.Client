package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Time;
import controller.MainViewControllerC;

public class ServerComunication extends Thread{
	
	private Socket sServer;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private Time time;
	private boolean started = false;
	private MainViewControllerC controller;
	
	public ServerComunication(Time time){
		this.time = time;
	}
	
	public void registerController(MainViewControllerC controller){
		this.controller = controller;
	}
	
	public boolean sendAddUser(String message){
		boolean next = false;
		
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				controller.makeDialog("The user has been successfully registered!",true);
				next = true;
			}else{
				controller.makeDialog("The user name is already taken!",false);
			}
			
			dataOut.close();
			dataIn.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return next;
	}
	
	public boolean sendLogUser(String message){
		boolean next = false; 
		
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				controller.makeDialog("The user has been successfully logged in!",true);
				next = true;
			}else{
				controller.makeDialog("The user name or password is wrong!",false);
			}
			
			dataOut.close();
			dataIn.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return next;
	}
	
	public void sendRanking(String message){
		try {
			sServer = new Socket("127.0.0.1", 5200);
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			
			controller.refreshRanking(answer);
			
			dataIn.close();
			dataOut.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendUpdate(String message){
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				controller.makeDialog("The user has been successfully updated!",true);
			}else{
				controller.makeDialog("The user could not be updated!",false);
			}
			
			dataOut.close();
			dataIn.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendStart(String message){
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.startsWith("START")){
				//controller.makeDialog("The countdown for the competition has started!",true);
				System.out.println("1");
				answer = answer.substring(6);
				String[] array = answer.split("/");
				time.stopTimerComp();
				time.startCountdownTimer(Integer.parseInt(array[0]),Integer.parseInt(array[1]));
				time.startTimerRank();
			}
			
			dataOut.close();
			dataIn.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendTime(String message){
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.startsWith("COUNTDOWN")){
				controller.makeDialog("The countdown for the competition has started! Now users can log to the competition.",true);
				message = message.substring(10);
				controller.refreshTime(message, false);
			}
			if(answer.startsWith("COMPETITION")){
				controller.makeDialog("The competition has started! It's time to get the best score!",true);
				message = message.substring(12);
				controller.refreshTime(message, true);
			}
			
			dataOut.close();
			dataIn.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
