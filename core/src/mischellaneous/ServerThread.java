package mischellaneous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import Chat.GameMaster;

public class ServerThread extends Thread {

	//private PrintWriter pw;
	//private BufferedReader br;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private GameMaster cr;
	public ServerThread(Socket s, GameMaster cr) {
		try {
			this.cr = cr;
			//pw = new PrintWriter(s.getOutputStream());
			//br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			oos = new ObjectOutputStream(s.getOutputStream());
			ois = new ObjectInputStream(s.getInputStream());
			this.start();
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
		}
	}

	//public void sendMessage(String message) {
	public void sendMessage(ChatMessage cm) {
//		pw.println(message);
//		pw.flush();
		try {
			oos.writeObject(cm);
			oos.flush();
		} catch (IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}
	
	public void run() {
		try {
			while(true) {
				//String line = br.readLine();
				//cr.broadcast(line, this);
				ChatMessage cm = (ChatMessage)ois.readObject();
//				cr.broadcast(cm, this);
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread.run(): " + ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("cnfe: " + cnfe.getMessage());
		}
	}
}