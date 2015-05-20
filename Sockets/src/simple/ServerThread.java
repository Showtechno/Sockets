package simple;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

class ServerThread extends Thread{
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
			
			/** Verwaltet den Ausgabestream der Socketverbindung */
			PrintStream	out=new PrintStream(sverb.getOutputStream());
			
			/** Eingehende TCP Verbindungen werden an einen eigenen (virtuellen)             Socket
			weitergereicht, über den die eigentliche Kommunikation stattfindet        (Damit mehrere
			Anfragen gleichzeitig bearbeitet werden können, müsste         dieser Teil durch Threads
			parallelisiert werden.) */

			String clientSentence = in.readLine();
			/*  Wandele die Nachricht in Grossbuchstaben */
			String capitalizedSentence = clientSentence.toUpperCase()+'\n';
			/*  Sende die Antwort an den Client */
			out.print(capitalizedSentence);
			/*  Der Server schliesst die Socketverbindung nicht. Weitere
			 *  Anfragen über diesen Socket werden jedoch nicht beachtet. */
			
			if(capitalizedSentence =="END"){
				sverb.close();
			}
			}
		}
		catch (IOException e){
			System.out.println("connetionLost"); 
			server.disconnect(nverb);
			try {
				sverb.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	} // Ende run()
} // Ende SimpleServe