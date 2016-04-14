package model;

public class UserClient {
	private String nickName;
	private String password;
	private int punctuation;
	
	public void userRegistration(String nickName,String password){
		//Quan inicia el compte enrere l'usuari pot introduir-se a la base de dades mitjançant sockets.
	}
	
	public void logOut(){
		
	}
	
	public void play(){
		//2 modalitats
	}
	
	public void showRanking(){
		
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPunctuation() {
		return punctuation;
	}
	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}
	
}
