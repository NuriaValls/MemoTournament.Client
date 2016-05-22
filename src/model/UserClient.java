package model;

import java.net.Socket;

import model.MTMainClient;

/**
 * Aquesta clase serveix per tenir registre d'un usuari amb el seu nom, contrassenya, puntuacio i si esta bloquejat o no.
 * Aquest usuaru conte la informacio de l'usuari que esta actualment connectat al programa client en cas de no ser un convidat.
 */
public class UserClient {
	/**
	 * String que guarda el nickname de l'usuari actual
	 */
	private String nickname;
	/**
	 * String que conte la contrassenya de l'usuari actual.
	 */
	private String password;
	/**
	 * int que conte la puntuacio total dins la competicio de l'usuari actual.
	 */
	private int score;
	/**
	 * Boolea que indica si l'usuari esta bloquejat o no.
	 */
	private boolean blocked = false;
	
	public UserClient(String nickname,String password,int score){
		this.nickname = nickname;
		this.password = password;
		this.score = score;
	}
	
	public UserClient(String nickname,String password){
		this.nickname = nickname;
		this.password = password;
	}
	
	public boolean isBlocked(){
		return blocked;
	}
	
	public void setBlocked(boolean blocked){
		this.blocked = blocked;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
