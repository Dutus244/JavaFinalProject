package server;

import java.io.*;
import java.net.*;


public class Server {
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	private ServerSocket serverSocket;
	public Server(ServerSocket serverSocket, String username) {
		this.serverSocket = serverSocket;
		this.username = username;
	}
	
	public void startServer() {
		try {
			while(!serverSocket.isClosed()) {
				this.socket = serverSocket.accept();
				this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//this.username = bufferedReader.readLine();
				System.out.println("Socket" + socket +"conneted");
				
				this.ListenMessage();
				this.sendMessage();
			} 
		}
		catch(IOException e){
			closeEverything(socket,bufferedReader, bufferedWriter);
			closeServerSocket();
		}
	}
	public void sendMessage() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
		while(socket.isConnected()) {
			String message = br.readLine();
			bufferedWriter.write(username + ':'+ message);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			}
		}
		catch(IOException e) {
			closeEverything(socket,bufferedReader, bufferedWriter);
			closeServerSocket();
		}
	}
	public void ListenMessage() {
		new Thread ( new Runnable() {
			public void run() {
				String msg;
				while(socket.isConnected()) {
					try {
						msg = bufferedReader.readLine();
						System.out.println(msg);
					}
					catch(IOException e) {
						
					}
				}
			}
		}).start();
	}
	public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
		
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
			System.out.println("Enter your name: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String username = br.readLine();
			ServerSocket serverSocket = new ServerSocket(1234);
			Server server = new Server(serverSocket, username);
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
