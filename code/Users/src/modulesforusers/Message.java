package modulesforusers;

public class Message {
	private String MessageID;
	private String UserName;
	private String Message;
	private String CreateTime;
	
	public Message(String messageID, String userName, String message, String createTime) {
		this.MessageID = messageID;
		this.UserName = userName;
		this.Message = message;
		this.CreateTime = createTime;
	}
	
	public String getMessageID() {
		return MessageID;
	}
	public void setMessageID(String messageID) {
		MessageID = messageID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
	
}
