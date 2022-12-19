package client;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedWriter bufferedWriter;
	private String username;
	private String receiver;
	public Client(Socket socket, String username) {
		try {
			this.socket = socket;
			this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.username = username;
		}
		catch(IOException e) {
			closeEverything(socket,bufferedReader, bufferedWriter);
		}
	}
	public void sendMessage() {
		try {
			bufferedWriter.write(username);//send user name (to constructor in server)
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
			try (Scanner scanner = new Scanner(System.in)) {
				while(socket.isConnected()) {
					System.out.print("Message to: ");
					receiver = scanner.nextLine();
					System.out.print("Message: ");
					String message = scanner.nextLine();
					bufferedWriter.write(receiver+"$"+username + ":"+ message);
					bufferedWriter.newLine();
					bufferedWriter.flush();
				}
			}
		}
		catch(IOException e) {
			closeEverything(socket,bufferedReader, bufferedWriter);
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
						closeEverything(socket,bufferedReader, bufferedWriter);
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
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Enter your name: ");
			String username = scanner.nextLine();
			Socket socket;
			try {
				socket = new Socket("localhost", 1234);
				Client client = new Client(socket,username);
				client.ListenMessage();
				client.sendMessage();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
