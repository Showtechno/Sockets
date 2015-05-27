package simple;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

class ServerThread implements Runnable{
	
	Socket sverb; 
	int    nverb;
	TCPServer server;
	/* Konstruktor von ServerThread wird bei jeder neuen Verbindung aus SimpleServer
	aufgerufen  */
	ServerThread (Socket s , int n, TCPServer server) { this.sverb = s; this.nverb = n; this.server = server;}
	public void run () {
		try {
			while(true){
			/** Verwaltet den Eingabestream der Socketverbindung */
			DataInputStream in=new DataInputStream(sverb.getInputStream());
			

			String clientSentence = in.readLine();
			server.senden(clientSentence);
			
			}// Ende while
		}// Ende try
		
		catch (IOException e){
			System.out.println("connetionLost");
			server.disconnect(nverb);
			
		}// Ende catch
	} // Ende run()
} // Ende SimpleServer