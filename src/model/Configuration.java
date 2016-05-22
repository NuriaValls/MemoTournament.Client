package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Configuration {
	private int portServer;
	private String IP = new String();
	
	public boolean configurate(){
		Gson gson = new GsonBuilder().create();
		BufferedReader br;
		try {
		   br = new BufferedReader(new FileReader("config.json"));
		   Configuration aux = gson.fromJson(br, Configuration.class);
		   
		   this.portServer = aux.portServer;
		   this.IP = aux.IP;
		   
		  } catch (FileNotFoundException e) {
		   return false;
		  } catch (Exception e) {
		   e.printStackTrace();
		   return false;
		  }
		  return true;
	}

	public int getPortServer() {
		return portServer;
	}

	public String getIp() {
		return IP;
	}
}
