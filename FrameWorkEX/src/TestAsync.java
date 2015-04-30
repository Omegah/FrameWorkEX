import java.rmi.RemoteException;
import java.util.Scanner;

import server.serverAsync;


public class TestAsync {
	
	public static void main(String[] args) {
		serverAsync serv = new serverAsync(1099, "152.77.82.230");
		Scanner sc = new Scanner(System.in);
		String ip = sc.nextLine();
		try {
			serv.connectServer(ip);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fichier = sc.nextLine();
		try {
			serv.takeObject(fichier);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
