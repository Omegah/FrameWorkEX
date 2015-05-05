package user;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server._ServerAsync;

/**
 * Classe abstraite UserAsync
 * Instanciation d'un utilisateur pour un serveur asynchrone (ex: dépôt de fichiers)
 * La méthode abstraite à implémenter concerne le traitement des fichiers récupérés
 * 
 * @author Groupe 3
 */
public abstract class UserAsync extends UnicastRemoteObject implements _UserAsync {
	public _ServerAsync server;
	
	public UserAsync() throws RemoteException {
		super();
	}
	
	/**
	 * Implémentation du traitement d'un fichier récupéré
	 * @param name Le nom du fichier
	 * @param obj L'objet à traiter
	 */
	public abstract void execute(String name, Object obj) throws RemoteException;

	/**
	 * Création de la connexion avec un serveur
	 * @param ip Adresse IP du serveur
	 * @param port Numéro de port pour la connexion
	 * @requires {@literal !ip.isEmpty() && port > 1000 && port < 65535}
	 */
	public void importServer(String ip, int port) throws RemoteException {
		try {
			server = (_ServerAsync) Naming.lookup("rmi://" + ip + "/Chat");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Envoie d'un objet sur le serveur
	 * @param obj L'objet à déposer
	 * @param name Le nom de l'objet
	 * @requires !name.isEmpty()
	 */
	public void send(Object obj, String name) throws RemoteException {
		server.send(obj, name);
	}
	
	/**
	 * Récupération d'un objet sur le serveur
	 * @param name Le nom de l'objet à récupérer
	 * @requires !name.isEmpty()
	 */
	public Object getObject(String name) throws RemoteException{
		try {
			File file = new File("files/" + name);
			byte buffer[] = new byte[(int) file.length()];
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream("files/" + name));
			input.read(buffer, 0, buffer.length);
			input.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
