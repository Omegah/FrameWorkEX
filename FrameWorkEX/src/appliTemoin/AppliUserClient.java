package appliTemoin;

import java.rmi.RemoteException;
import java.util.Scanner;

import user.UserServer;

public class AppliUserClient {
	public static void main(String[] args) throws RemoteException {
		UserServer user = new UserServer();
		user.createServer("152.77.82.231", 1099);
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("cmd ?\n1 import server \n 2 envoi d'un fichier \n 3 récupere un fichier");
			String cmd = sc.nextLine();
			switch (Integer.parseInt(cmd)) {
			case 1:
				System.out.println("ip du server : ");
				String info = sc.nextLine();
				user.importServer(info, 1099);
				break;
			case 2:
				System.out.println("Fichier à envoyer : ");
				String name = sc.nextLine();
				user.send(user.takeObject(name),name );
				break;

			case 3:
				System.out.println("Fichier à recuperer : ");
				String name2 = sc.nextLine();
				user.receiveObject(name2,user.server.takeObject(name2));
				break;
			}

		} while (true);
	}

}
