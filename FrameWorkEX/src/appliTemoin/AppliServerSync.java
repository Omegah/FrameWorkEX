package appliTemoin;

import java.rmi.RemoteException;
import java.util.Scanner;

import server.ServerSync;

public class AppliServerSync {

	public static void main(String[] args) throws RemoteException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ip du serveur :");
		String Ip = sc.nextLine();
		System.out.println("Port du serveur :");
		String Port = sc.nextLine();
		
		ServerSync server = new ServerSync();
		try {
			server.createServer(Ip, Integer.parseInt(Port));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
