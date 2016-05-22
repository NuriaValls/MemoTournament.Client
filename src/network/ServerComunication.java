package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Time;
import model.UserClient;
import controller.MainViewControllerC;

public class ServerComunication extends Thread{
	
	private Socket sServer;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	private Time time;
	private boolean competition = false;
	private MainViewControllerC controller;
	
	private int portServer;
	
	public ServerComunication(Time time, String ip, int portServer){
		this.time = time;
		this.portServer = portServer;
	}
	
	public void registerController(MainViewControllerC controller){
		this.controller = controller;
	}
	
	public boolean sendAddUser(String message){
		boolean next = false;
		
		try {
			sServer = new Socket("127.0.0.1",portServer);
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
			controller.makeDialog("Coudn't connect with server", false);
		} catch (IOException e) {
			controller.makeDialog("Coudn't connect with server", false);
		}
		
		return next;
	}
	
	public boolean sendLogUser(UserClient user){
		boolean next = false; 
		String message = "LOG:"+user.getNickname()+"/"+user.getPassword();
		try {
			sServer = new Socket("127.0.0.1",portServer);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.startsWith("OK")){
				controller.makeDialog("The user has been successfully logged in!",true);
				controller.user = new UserClient (user.getNickname(), user.getPassword(), Integer.parseInt(answer.substring(3)));
				next = true;
			}else{
				controller.makeDialog("The user name or password is wrong!",false);
			}
			
			dataOut.close();
			dataIn.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			controller.makeDialog("Coudn't connect with server", false);
		} catch (IOException e) {
			controller.makeDialog("Coudn't connect with server", false);
		}
		
		return next;
	}
	
	public void sendRanking(String message){
		try {
			sServer = new Socket("127.0.0.1", portServer);
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
			controller.makeDialog("Coudn't connect with server", false);
		} catch (IOException e) {
			controller.makeDialog("Coudn't connect with server", false);
		}
	}
	
	public void sendUpdate(String message){
		try {
			sServer = new Socket("127.0.0.1",portServer);
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
			controller.makeDialog("Coudn't connect with server", false);
		} catch (IOException e) {
			controller.makeDialog("Coudn't connect with server", false);
		}
	}
	
	public void sendStart(String message){
		try {
			sServer = new Socket("127.0.0.1",portServer);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.startsWith("START")){
				competition = true;
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
			controller.makeDialog("Coudn't connect with server", false);
		} catch (IOException e) {
			controller.makeDialog("Coudn't connect with server", false);
		}
	}
	
	public void sendBlockedUser(String message){
		try {
			sServer = new Socket("127.0.0.1",portServer);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("BLOCKED")){
				controller.setBlocked(true);
			}else{
				controller.setBlocked(false);
			}
			
			dataOut.close();
			dataIn.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			controller.makeDialog("Coudn't connect with server", false);
		} catch (IOException e) {
			controller.makeDialog("Coudn't connect with server", false);
		}
	}
	
	public boolean getCompetition(){
		return competition;
	}
}
