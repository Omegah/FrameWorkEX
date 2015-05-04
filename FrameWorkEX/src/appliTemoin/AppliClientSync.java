package appliTemoin;

import java.rmi.RemoteException;
import java.util.Scanner;

import user.UserSync;

public class AppliClientSync {

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Ip du serveur :");
		String Ip = sc.nextLine();
		System.out.println("Port du serveur :");
		String Port = sc.nextLine();
		
		
		OurUser user = new OurUser(Ip,Integer.parseInt(Port));
		
	}

}
