import java.rmi.RemoteException;
import java.util.Scanner;

import server.UserServer;


public class TestAsync  {
	
	public static void main(String[] args) {
		UserServer serv = null;
		try {
			serv = new UserServer(1099, "152.77.82.230");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
