package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class GameMaster extends Thread {
	
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
			this.start();
			Scanner input = new Scanner(System.in);
//			System.out.println("Please input a welcoming message to send to the client:");
			String messageToSend = "";
//			messageToSend = input.nextLine();
//			System.out.println("Message that was sent: " + messageToSend);
//			pw.println(messageToSend);
//			pw.flush();
			// Try doing a scanner, while game master wants to send messages to the client,
			// keep sending messages. Using MessageClientThread?
			// While input is not equal to end
			while (true)
			{
				messageToSend = input.nextLine();
				if (messageToSend.equalsIgnoreCase("end"))
				{
					break;
				}
				pw.println(messageToSend);
				pw.flush();
				System.out.println("Message that was sent: " + messageToSend);
			}
			input.close();
			if (br != null) {
				br.close();
			}
			if (pw != null) {
				pw.close();
			}
		} catch (IOException ioe) {
			System.out.println("ioe in GameMaster constructor: " + ioe.getMessage());
		}
	}
	
	public void run()
	{
		while (true)
		{
			try
			{
				String line = br.readLine();
				System.out.println(line);
				if (line == null)
				{
					break;
				}
			}
			catch (IOException io)
			{
				System.out.println("IO Exception in Server: " + io.getMessage());
			}
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