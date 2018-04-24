package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class GameMaster {

//	private Vector<ServerThread> serverThreads;
	ServerThread st;
	private BufferedReader br;
	private PrintWriter pw;
	
	public GameMaster(int port) {
		try {
			System.out.println("Binding to port: " + port);
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Bound to port: " + port);
			System.out.println("Server has started up! Waiting for a client to connect...");
//			Scanner input = new Scanner(System.in);
//			System.out.println("Please input a welcoming message to send to the client:");
//			String messageToSend = input.nextLine();
			Socket s = ss.accept(); // Accept that one client
			System.out.println("Client Connected! Connection from: " + s.getInetAddress());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(s.getOutputStream());
			Scanner input = new Scanner(System.in);
			System.out.println("Please input a welcoming message to send to the client:");
			String messageToSend = input.nextLine();
			pw.println(messageToSend);
			pw.flush();
			// Try doing a scanner, while game master wants to send messages to the client,
			// keep sending messages. Using MessageClientThread?
//			Scanner input = new Scanner(System.in);
//			// While input is not equal to end
//			while (!input.nextLine().equalsIgnoreCase("end"))
//			{
//				messageToSend = input.nextLine();
//				pw.println(messageToSend);
//				System.out.println("Message sent!");
//			}
			input.close();
			if (br != null) {
				br.close();
			}
			if (pw != null) {
				pw.close();
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
		}
	}
	
//	public void broadcast(String message, ServerThread st) {
//	public void broadcast(ChatMessage cm, ServerThread st) {
//		//if (message != null) {
//		if (cm != null) {
//			//System.out.println(message);
//			System.out.println(cm.getUsername() + ": " + cm.getMessage());
//			for(ServerThread threads : serverThreads) {
//				if (st != threads) {
//					//threads.sendMessage(message);
//					threads.sendMessage(cm);
//				}
//			}
//		}
//	}
	
	public static void main(String [] args) {
		GameMaster gm = new GameMaster(6789);
	}
}