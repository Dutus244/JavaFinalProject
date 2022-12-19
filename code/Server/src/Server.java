import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	static List<User> allUsersList = new ArrayList<>(); 
	ServerSocket serverSocket;

	static int portnumber = 1234;

	
	public static void main(String[] args) { 
		new Server();
	}
	
	public Server() {
		try {
			serverSocket = new ServerSocket(portnumber);  // create a socket for server
			System.out.println("ServerSocket is created " + serverSocket);
			new ClientAccept().start(); // this will create a thread for client
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUsername(List<User> list, String username) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUsername().equals(username)) {
				return false;
			}
		}
		return true;
	}
	
	class ClientAccept extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					Socket clientSocket = serverSocket.accept();
					System.out.println("Connect request is accepted...");
					String clientHost = clientSocket.getInetAddress().getHostAddress();
					int clientPort = clientSocket.getPort();
					System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

					InputStream clientIn = clientSocket.getInputStream();
					BufferedReader br = new BufferedReader(new
							InputStreamReader(clientIn));
					String username = br.readLine();


					OutputStream clientOut = clientSocket.getOutputStream();
					PrintWriter pw = new PrintWriter(clientOut, true);

					if (!checkUsername(allUsersList,username)) {
						pw.println("Username already taken");
					} else {
						allUsersList.add(new User(username, clientSocket));
						pw.println("OK");
						new MsgRead(clientSocket, username).start();
					}
				} catch (IOException ioex) {  // throw any exception occurs
					ioex.printStackTrace();
				}
			}
		}
		
	}
	
	class MsgRead extends Thread { 
		Socket s;
		String Id;
		private MsgRead(Socket s, String username) {
			this.s = s;
			this.Id = username;
		}

		@Override
		public void run() {
			while (true) {
				if (s != null) {
					try {
						InputStream clientIn = s.getInputStream();
						BufferedReader br = new BufferedReader(new
								InputStreamReader(clientIn));
						String message = br.readLine();
						String[] split = message.split("`");
						if (!split[4].equals("exit")) {
							String username = split[2];
							for (int i = 0; i < allUsersList.size(); i++) {
								if (allUsersList.get(i).getUsername().equals(username)) { // we don't need to send message to ourself, so we check for our Id
									try {
										OutputStream clientOut = allUsersList.get(i).getSocket().getOutputStream();
										PrintWriter pw = new PrintWriter(clientOut, true);
										pw.println(message);
									} catch (Exception e) {
										e.printStackTrace(); // throw exceptions
									}
								}
							}
						}
						else {
							int index = -1;
							for (int i = 0; i < allUsersList.size(); i++) {
								if (!allUsersList.get(i).getUsername().equalsIgnoreCase(Id)) { // we don't need to send message to ourself, so we check for our Id

								}
								else {
									index = i;
								}
							}
							allUsersList.remove(index);
							clientIn.close();
							s.close();
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
