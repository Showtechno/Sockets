package simple;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

/** Testprogramm für einen TCP Server Der Server empfängt Nachrichten, wandelt sie in
Grossbuchstaben und sendet sie zurück. */
public class TCPServer  {

	private HashMap<Integer, ServerThread> clientList;
	
	public void start()throws java.net.SocketException, java.io.IOException{
		/** Der Serverdienst wird auf Port 7788 aufgesetzt */
		int anz = 1;
		try {
			ServerSocket ss =new ServerSocket(7788);
			while(true){              
				Socket sverb = ss.accept();
				System.out.println(" Verbindung " + anz);
				new ServerThread(sverb,anz,this).start();

		
				anz++;
//				
//				/** Eingehende TCP Verbindungen werden an einen eigenen (virtuellen)             Socket
//				weitergereicht, über den die eigentliche Kommunikation stattfindet        (Damit mehrere
//				Anfragen gleichzeitig bearbeitet werden können, müsste         dieser Teil durch Threads
//				parallelisiert werden.) */
//				Socket connectionSocket = welcomeSocket.accept();
//				BufferedReader inFromClient= new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//				DataOutputStream outToClient= new DataOutputStream(connectionSocket.getOutputStream());
//				clientSentence = inFromClient.readLine();
//				/*  Wandele die Nachricht in Grossbuchstaben */
//				capitalizedSentence = clientSentence.toUpperCase()+'\n';
//				/*  Sende die Antwort an den Client */
//				outToClient.writeBytes(capitalizedSentence);
//				/*  Der Server schliesst die Socketverbindung nicht. Weitere
//				 *  Anfragen über diesen Socket werden jedoch nicht beachtet. */
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	public static void main(String[] args) throws SocketException, IOException {
		new TCPServer().start();
	}
	public void disconnect(int nverb) {
		clientList.remove(nverb);
		
	}
}