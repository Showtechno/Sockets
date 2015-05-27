package simple;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/** Testprogramm für einen TCP Server Der Server empfängt Nachrichten, wandelt sie in
Grossbuchstaben und sendet sie zurück. */
public class TCPServer  {

	private Hashtable<Integer, ServerThread> clientList;
	
	public void start()throws java.net.SocketException, java.io.IOException{
		/** Der Serverdienst wird auf Port 7788 aufgesetzt */
		int anz = 1;
		try {
			ServerSocket ss =new ServerSocket(7788);
			while(true){              
				Socket sverb = ss.accept();
				System.out.println(" Verbindung " + anz);
				clientList.put(anz,new ServerThread(sverb,anz,this));

		
				anz++;
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	public void disconnect(int nverb) {
		clientList.remove(nverb);	
	}
	
	public void senden(String nachricht){
		for(int i = 0;i<=clientList.size();i++){
			clientList.get(i).drucken();
			if(i==clientList.size())
				i = 0;
		}
	}
	
	public static void main(String[] args) throws SocketException, IOException {
		new TCPServer().start();
	}
}