package appliTemoin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class AppliUserAsyncRecupere {

	public static void main(String[] args) throws RemoteException {
		OurUserAsync user = new OurUserAsync();
		user.importServer("152.77.82.230", 1099);
		Scanner sc = new Scanner(System.in);
		do{
		System.out.println("Fichier à recuperer : ");
		String name = sc.nextLine();
		user.receiveObject(name,user.server.getObject(name));
		}while(true);
	}
}
