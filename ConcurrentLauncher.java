package com.cooksys.launch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ConcurrentLauncher {
	
	static Set<ClientHandler> clients = new HashSet<>();

	public static void main(String[] args) throws InterruptedException, IOException {

		ServerSocket server = new ServerSocket(4455);
		
		

		while (true) {
			Socket client = server.accept();
			
			//____________________________
			ClientHandler cH = new ClientHandler(client);
			
			//clients.add(cH);
			//_______________________________

			Thread clientHandler = new Thread(cH);

			clientHandler.start();
		}
		
		

	}
	
	//------------------------------------------------------------
	/*public void respond(String message) throws IOException{
		
		for(ClientHandler x : clients){
			
			x.respondToAll(message);
		}
	}*/

}
