import java.rmi.RemoteException;
import java.util.Scanner;

import user.UserAsync;


public class TestClientDepot {

	public TestClientDepot() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserAsync user = null;
		try {
			user = new UserAsync();
			String ip = sc.nextLine();
			user.connectServer(ip);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("deposer un fichier :");
		String fichier = sc.nextLine();
		try {
			user.deposit(fichier);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("recup fichier :");
		fichier = sc.nextLine();
		try {
			user.pull(fichier);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
