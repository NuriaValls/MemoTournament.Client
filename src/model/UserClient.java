package model;

import model.MTMainClient;

public class UserClient {
	private String nickname;
	private String password;
	private int score;
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
	
	public void logOut(){
		
	}
	
	public void play(){
		//2 modalitats
	}
	
	public void showRanking(){
		
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
