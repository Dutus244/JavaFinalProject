import java.net.Socket;

public class User {
	private String username;
	private Socket socket;
	
	User(String username, Socket socket) {
		this.username = username;
		this.socket = socket;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
