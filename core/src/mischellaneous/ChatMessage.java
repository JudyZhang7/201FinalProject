package mischellaneous;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	public static final long serialVersionUID = 1;
	private String username;
	private String message;
	
	public ChatMessage(String username, String message) {
		this.username = username;
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public String getMessage() {
		return message;
	}
}