package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Aquesta classe ens permet llegir la informacio de configuracio d'un fitxer.json i guardarla en atributs locals.
 */
public class Configuration {
	/**
	 * Atribut que guarda el port del servidor
	 */
	private int portServer;
	/**
	 * Atribut que guarda la ip del servidor.
	 */
	private String IP = new String();
	/**
	 * Metode que llegeix el fitxer .json i escriu el seu contingut als atributs de la classe. Retorna false si no troba el fitxer.
	 */
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
