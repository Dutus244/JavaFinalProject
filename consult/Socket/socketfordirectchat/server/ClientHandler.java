package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable{

	public static ArrayList<ClientHandler> clientHandler = new ArrayList<>();
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String clientusername;
	
	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			System.out.println("server socket "+ socket);
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.clientusername = bufferedReader.readLine();
			clientHandler.add(this);
			System.out.println("Server:" + clientusername +"join"); 
			
		}
		catch(IOException e) {
			closeEverything(socket,bufferedReader, bufferedWriter);
		}
	}
	
	@Override
	public void run() {
		String messageFromClient;
		while(socket.isConnected()) {
			try {
				messageFromClient = bufferedReader.readLine();
				System.out.println(messageFromClient);
				this.sendMessage(messageFromClient);
			}catch(IOException e) {
				closeEverything(socket,bufferedReader, bufferedWriter);
				break; //break the while
			}
		}
	
		
	}
	public void sendMessage(String message) {//send message to everybody else
		int iend = message.indexOf("$");
		String subString =null;
		if (iend != -1) 
		{
		    subString= message.substring(0 , iend); //this will give abc
		    message= message.substring(iend+1 ,message.length());
		}
		System.out.println("receiver "+ subString);
		System.out.println("message "+ message);
		for(ClientHandler clientHandler: clientHandler) {
			try {
				if(clientHandler.clientusername.equals(subString)) {
					clientHandler.bufferedWriter.write("Message from" +message);
					clientHandler.bufferedWriter.newLine(); //break line
					clientHandler.bufferedWriter.flush(); // delete character in the memories
				}
				
			}
			catch(IOException e) {
				closeEverything(socket,bufferedReader, bufferedWriter);
			}
		}
	}
	public void removeClientHandler() {
		//clientusername.remove(this.clientusername);
		clientHandler.remove(this);
		System.out.println("Server:" + clientusername +"left"); 
		
	}
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		removeClientHandler();
		try {
			if(bufferedReader!=null) {
				bufferedReader.close();
			}
			if(bufferedWriter!=null) {
				bufferedWriter.close();
			}
			if(socket!=null) {
				socket.close(); 
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
