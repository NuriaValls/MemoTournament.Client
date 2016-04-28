package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import controller.MainViewControllerC;

public class ServerComunication extends Thread{
	
	private static Socket sServer;
	private static DataInputStream dataIn;
	private static DataOutputStream dataOut;
	
	public static boolean sendAddUser(String message){
		boolean next = false;
		
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				MainViewControllerC.makeDialog("The user has been successfully registered!",true);
				next = true;
			}else{
				MainViewControllerC.makeDialog("The user name is already taken!",false);
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
	
	public static boolean sendLogUser(String message){
		boolean next = false; 
		
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				MainViewControllerC.makeDialog("The user has been successfully logged in!",true);
				next = true;
			}else{
				MainViewControllerC.makeDialog("The user name or password is wrong!",false);
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
	
	public static void sendRanking(String message){
		try {
			sServer = new Socket("127.0.0.1", 5200);
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			//MainViewControllerC.refreshList(answer);
			
			dataIn.close();
			dataOut.close();
			sServer.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendUpdate(String message){
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				MainViewControllerC.makeDialog("The user has been successfully updated!",true);
			}else{
				MainViewControllerC.makeDialog("The user could not be updated!",false);
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
	
	public static void sendStart(String message){
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.startsWith("OK:")){
				MainViewControllerC.makeDialog("The count back for the competition has started!",true);
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
