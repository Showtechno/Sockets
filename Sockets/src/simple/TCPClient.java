package simple;

import java.io.*;
import java.net.*;

/** Testprogramm f�r einen TCP Client
* Dem Client wird per Kommandozeile ein Satz eingegeben, der an den
* Server gesendet wird. Nach erhalt der Antwort beendet sich der
* Client. */

class TCPClient {
	/**Exceptions, die auftreten k�nnen, werden durchgereicht
	 */
	public static void main(String args[]) throws java.net.SocketException, java.io.IOException{

		
	/** Nachricht, die an den Server geschickt wird */
	String sentence;
	
	/** Nachricht, die vom Server empfangen wird */
	String modifiedSentence;
	
	/** Objekt, dass Benutzerdaten einliest */
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	
	/** Stellt die Verbindung zum Server auf Port 7788 her. In diesem
	Fall l�uft der Server auf dem gleichen Rechner wie der Client. */
	Socket clientSocket = new Socket("localhost", 7788);
	while(true){
	DataOutputStream outToServer= new DataOutputStream(clientSocket.getOutputStream());
	/** Verwaltet den Eingabestream der Socketverbindung , 2. M�glichkeit*/
	BufferedReader inFromServer	= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	/** Der Benutzer gibt einen Satz ein */

	sentence = inFromUser.readLine();
	/*  Der Satz wird �ber den Ausgabestream der Socketverbindung an den Server
	gesendet. */
	outToServer.writeBytes(sentence +'\n');
	/*  Es wird auf die Nachricht vom Server gewartet und diese Empfangen */
	modifiedSentence = inFromServer.readLine();
	/*  Die Antwort wird auf der Konsole ausgegeben */
	System.out.println("FROM SERVER: "+ modifiedSentence);
	/*  Der Client schliesst die Socketverbindung */
	}
//	clientSocket.close();
	}  
}//  Programmende