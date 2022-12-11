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
			broadcastMessage("Server:" + clientusername +"join"); 
			
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
				broadcastMessage(messageFromClient);
			}catch(IOException e) {
				closeEverything(socket,bufferedReader, bufferedWriter);
				break; //break the while
			}
		}
	
		
	}
	public void broadcastMessage(String message) {//send message to everybody else
		for(ClientHandler clientHandler: clientHandler) {
			try {
				if(!clientHandler.clientusername.equals(clientusername)) {
					clientHandler.bufferedWriter.write(message);
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
		clientHandler.remove(this);
		broadcastMessage("Server:" + clientusername +"left"); 
		
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
