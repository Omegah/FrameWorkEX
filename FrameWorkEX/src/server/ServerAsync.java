package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe ServerAsync
 * Instanciation d'un serveur pour une communication asynchrone multi-utilisateurs
 * @author Groupe 3
 */
public class ServerAsync extends UnicastRemoteObject implements _ServerAsync {


	public ServerAsync() throws RemoteException {
		super();
	}


	/**
	 * Envoi d'un objet sur le serveur
	 * @param obj L'objet à envoyer
	 * @param name Le nom de l'objet
	 */
	public void send(Object obj, String name) throws RemoteException{
		try {
			BufferedOutputStream output = new BufferedOutputStream(
					new FileOutputStream("filesServer/" + name));
			output.write(((byte[])obj), 0, ((byte[])obj).length);
			output.flush();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récupération d'un objet depuis le serveur
	 * @param name Le nom de l'objet à récupérer
	 * @require !name.isEmpty()
	 */
	public Object getObject(String name) throws RemoteException{
		try {
			File file = new File("filesServer/" + name);
			byte buffer[] = new byte[(int) file.length()];
			BufferedInputStream input = new BufferedInputStream(new FileInputStream("filesServer/" + name));
			input.read(buffer, 0, buffer.length);
			input.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * Création du serveur
	 * @param IP Adresse IP du serveur
	 * @param Port Numéro de port pour la connexion
	 * @require Port > 1000 && Port <65535
	 */
	public void createServer(String Ip, int Port) throws RemoteException {
		try {

			LocateRegistry.createRegistry(Port);
			try {
				Naming.rebind("rmi://" + Ip + "/Chat", this);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
