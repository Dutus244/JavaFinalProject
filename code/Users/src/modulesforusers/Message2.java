package modulesforusers;

public class Message2 {
	private String MessageID;
	private String UserName;
	private String GroupName;
	private String TypeInbox;
	private String Message;
	private String CreateTime;

	public Message2(String messageID, String userName,String groupName,String TypeInbox, String message, String createTime) {
		this.MessageID = messageID;
		this.UserName = userName;
		this.Message = message;
		this.CreateTime = createTime;
		this.GroupName = groupName;
		this.TypeInbox = TypeInbox;
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
	public String getGroupName() {
		return GroupName;
	}
	
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public String getTypeInbox() {
		return TypeInbox;
	}
	
	public void setTypeInbox(String typeInbox) {
		TypeInbox = typeInbox;
	}
	
}
