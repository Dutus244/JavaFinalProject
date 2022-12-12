package server;

import java.io.*;
import java.net.*;

public class Server {

	private ServerSocket serverSocket;
	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	public void startServer() {
		try {
			while(!serverSocket.isClosed()) {
				Socket socket = serverSocket.accept();
				System.out.println("Socket" + socket +"conneted");
				ClientHandler clientHandler = new ClientHandler(socket);
				
				Thread thread = new Thread(clientHandler);
				
				thread.start();
			} 
		}
		catch(IOException e){
			
		}
	}
	public void closeServerSocket() {
		try {
			if(serverSocket != null) {
				serverSocket.close();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	
		try {
			ServerSocket serverSocket = new ServerSocket(1234);
			Server server = new Server(serverSocket);
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
