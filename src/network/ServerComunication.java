package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Time;
import model.UserClient;
import controller.MainViewControllerC;

/**
 * Aquesta classe implementa els metodes que creen les comunicacions amb el servidor per enviar la informacio del client
 * corresponent i espera una resposta del servidor i trcta la informacio rebuda.
 * @author nvall
 *
 */
public class ServerComunication extends Thread{
	
	/**
	 * Atribut per establirla connexio amb el servidor.
	 */
	private Socket sServer;
	
	/**
	 * Atribut que serveix per llegir dades provinents de la connexio amb el servidor.
	 */
	private DataInputStream dataIn;
	
	/**
	 * Atribut que serveix per poder enviar dades al servidor.
	 */
	private DataOutputStream dataOut;
	
	/**
	 * Atribut que instancia un objecte de la classe Time per controlar els diferents timers.
	 */
	private Time time;
	
	/**
	 * Atribut que indica si hi ha una competicio en marxa o no.
	 */
	private boolean competition = false;
	
	/**
	 * Atribut que instancia un objecte de tipus controller per executar diferents metodes.
	 */
	private MainViewControllerC controller;
	
	/**
	 * Atrobut que serveix per configurar el port de connexio amb el server.
	 */
	private int portServer;
	
	/**
	 * Metode constructor de classe que rep un objecte de tipus Time, una ip corresponent a  la ip del servidor i un
	 * port de connexio amb el servidor.
	 */
	public ServerComunication(Time time, String ip, int portServer){
		this.time = time;
		this.portServer = portServer;
	}
	
	/**
	 * Metode que instancia el controlador a la classe.
	 */
	public void registerController(MainViewControllerC controller){
		this.controller = controller;
	}
	
	/**
	 * Metode que rep un missatge amb el nom de l'usuari i la seva contrassenya i els envia al servidor perque quedin
	 * registrats a la base de dades.
	 */
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
	
	/**
	 * Metode que rep una Sring amb un nom d'usuari i una contrassenya i els envia al servidor per comprovar si aquest
	 * ja esta registrat a la base de dades.
	 */
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
	
	/**
	 * Metode que serveix per preguntar al servidor  i rebre el ranquink d'usuaris inscrits a la competicio.
	 */
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
	
	/**
	 * Metode que rep una String amb un nom un mode i una puntuacio i ho envia al server perque h actualitzi a la base de dades.
	 */
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
	
	/**
	 * Metode que pregunta al servidor si hi ha alguna competicio iniciada i rep el temps de la competicio.
	 */
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
	
	/**
	 * Metode que pregunta al servidor si l'usuari que rep com a string està bloquejat peer l'usuari.
	 */
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
	
	/**
	 * Metode que retorna si hi ha una competicio iniciada.
	 */
	public boolean getCompetition(){
		return competition;
	}
}
