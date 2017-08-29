package com.cooksys.launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

	Socket client;
	private String name;

	public ClientHandler(Socket client) {
		this.client = client;
	}
	
	ConcurrentLauncher cL = new ConcurrentLauncher();
	

	@Override
	public void run() {
		try {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter writer = new PrintWriter(client.getOutputStream());
			Scanner scanner = new Scanner(System.in);
			
			//Greeting
			writer.println("Connected");
			writer.flush();
			System.out.println("Someone " + reader.readLine());
			writer.println("Your name?");
			writer.flush();
			name=reader.readLine();
			System.out.println("His name is " + name);
			writer.println("Sup "+name +"?");
			writer.flush();

							
			//Read
				new Thread(()->{
					try { 
						while (true) {
							String said = reader.readLine();
							System.out.println(said);
							//writer.println(name+" said: " +said);
							writer.flush();
					}
					} catch (IOException e) {
						e.printStackTrace();
				}}).start();
				
			//Write
				new Thread(()->{
					while(true){
						
						//multichat
						//--------------------------------------
						/*try {
							String s = scanner.nextLine();
							cL.respond(s);
							//System.out.println(s);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						//-------------------------------------
						
						//OneOOne
						writer.println("Artem said: "+scanner.nextLine());
						writer.flush();
					}
				}).start();
				
				scanner.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
	/*public void respondToAll(String message) throws IOException{
		
		PrintWriter writer = new PrintWriter(client.getOutputStream());
		writer.println(message);
		writer.flush();
		
	}*/

}
