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
	
	public static void sendAddUser(String message){
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				//MainViewControllerC.makeDialog("The user has been successfully registered!");
			}else{
				//MainViewControllerC.makeDialog("The user name is already taken!");
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
	
	public static void sendLogUser(String message){
		try {
			sServer = new Socket("127.0.0.1",5200);
			dataIn = new DataInputStream(sServer.getInputStream());
			dataOut = new DataOutputStream(sServer.getOutputStream());
			dataOut.writeUTF(message);
			
			String answer = new String();
			answer = dataIn.readUTF();
			if(answer.equals("OK")){
				//MainViewControllerC.makeDialog("The user has been successfully logged in!");
			}else{
				//MainViewControllerC.makeDialog("The user name or password is wrong!");
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
				//MainViewControllerC.makeDialog("The user has been successfully updated!");
			}else{
				//MainViewControllerC.makeDialog("The user could not be updated!");
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
				//MainViewControllerC.makeDialog("The count back for the competition has started!");
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
