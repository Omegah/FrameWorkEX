package appliTemoin;

import java.rmi.RemoteException;
import java.util.Scanner;

import server.ServerAsync;
import server.ServerSync;

public class AppliServAsync {

	public static void main(String[] args) throws RemoteException {
		ServerAsync serv = new ServerAsync() ;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Ip du serveur :");
		String Ip = sc.nextLine();
		System.out.println("Port du serveur :");
		String Port = sc.nextLine();
		serv.createServer(Ip, Integer.parseInt(Port));
	}

}
