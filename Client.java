package com.cooksys.launch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	private String myName;
	
	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public static void main(String[] args) throws Exception {
		
		Client client = new Client();
		
		Socket socket = new Socket("10.1.1.229", 4455);
		
		Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		//Greeting
		System.out.println(reader.readLine());
		writer.println("Connected");
		writer.flush();
		System.out.println(reader.readLine());
		String tempName=scanner.nextLine();
		client.setMyName(tempName.substring(0,1).toUpperCase()+tempName.substring(1).toLowerCase());
		
		writer.println(client.getMyName());
		writer.flush();
		
		
		
		//Write
			new Thread(()->{
					while(true){
						writer.println(client.getMyName()+" said: "+scanner.nextLine());
						writer.flush();
					}
				}).start();
		
		//Read
			new Thread(()->{
				try { 
					while (true) {
							System.out.println(reader.readLine());
						}
						} catch (IOException e) {
							e.printStackTrace();
				}}).start();
			
			scanner.close();
			socket.close();
	}

}
