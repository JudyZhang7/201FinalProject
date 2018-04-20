package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient extends Thread {

	//private BufferedReader br;
	//private PrintWriter pw;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	public ChatClient(String hostname, int port) {
		try {
			System.out.println("Trying to connect to " + hostname + ":" + port);
			Socket s = new Socket(hostname, port);
			System.out.println("Connected to " + hostname + ":" + port);
			//br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			//pw = new PrintWriter(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			this.start();
			Scanner scan = new Scanner(System.in);
			while(true) {
				String line = scan.nextLine();
				ChatMessage cm = new ChatMessage("Ryan", line);
				oos.writeObject(cm);
				oos.flush();
				//pw.println("Donald: " + line);
				//pw.flush();
			}
			
		} catch (IOException ioe) {
			System.out.println("ioe in ChatClient constructor: " + ioe.getMessage());
		}
	}
	public void run() {
		try {
			while(true) {
				//String line = br.readLine();
				//System.out.println(line);
				ChatMessage cm = (ChatMessage)ois.readObject();
				System.out.println(cm.getUsername() + ": " + cm.getMessage());
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ChatClient.run(): " + ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		}
	}
	public static void main(String [] args) {
		ChatClient cc = new ChatClient("localhost", 6789);
	}
}