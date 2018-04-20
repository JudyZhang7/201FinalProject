package Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatRoom {

	private Vector<ServerThread> serverThreads;
	public ChatRoom(int port) {
		try {
			System.out.println("Binding to port " + port);
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Bound to port " + port);
			serverThreads = new Vector<ServerThread>();
			while(true) {
				Socket s = ss.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				ServerThread st = new ServerThread(s, this);
				serverThreads.add(st);
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
		}
	}
	
//	public void broadcast(String message, ServerThread st) {
	public void broadcast(ChatMessage cm, ServerThread st) {
		//if (message != null) {
		if (cm != null) {
			//System.out.println(message);
			System.out.println(cm.getUsername() + ": " + cm.getMessage());
			for(ServerThread threads : serverThreads) {
				if (st != threads) {
					//threads.sendMessage(message);
					threads.sendMessage(cm);
				}
			}
		}
	}
	
//	public static void main(String [] args) {
//		ChatRoom cr = new ChatRoom(6789);
//	}
}