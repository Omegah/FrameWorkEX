package user;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server._Server;
import server._ServerSync;

/**
 * Classe abstraite UserSync
 * 
 * Utilisateur d'un serveur synchrone (ex: Chat)
 * La connexion avec le serveur se fait à l'aide de la méthode importServer
 * Les implémentations à réaliser concernent le traitement des objets envoyés
 * et la gestion des utilisateurs (liste)
 * 
 * @author Groupe 3
 */
public abstract class UserSync extends UnicastRemoteObject implements _UserSync {
	protected _ServerSync server;
	protected String uName;

	/**
	 * Constructeur de la classe
	 * @param name Nom de l'utilisateur
	 * @throws RemoteException
	 */
	public UserSync(String name) throws RemoteException{
		uName = name;
	}
	
	protected UserSync() throws RemoteException{
	}
	
	/**
	 * Définir comment mettre à jour la liste des utilisateurs connectés (ex:interface graphique)
	 * @param activeUsers La liste des utilisateurs connectés
	 */
	public abstract void updateUser(ArrayList<String> activeUsers) throws RemoteException ;

	/**
	 * Définir comment traiter l'objet reçu.
	 * @param name Le nom de l'expéditeur de l'objet
	 * @param obj L'objet envoyé
	 */
	public abstract void execute(String name, Object obj) throws RemoteException ;
	

	/**
	 * Recevoir un objet depuis le serveur (utilisé depuis le serveur par RMI).
	 * @param name Le nom de l'expéditeur de l'objet
	 * @param obj L'objet envoyé
	 */
	public synchronized void receiveObject(String name, Object obj)throws RemoteException {
		execute(name, obj);
	}

	/**
	 * Initialisation de la connexion avec un serveur
	 * @param ip L'adresse IP du serveur
	 * @param port Le numero de port de la connexion
	 * @require !ip.isEmpty() && port >1000 && port < 65535
	 */
	public void importServer(String ip, int port) throws RemoteException{
				try {
					server = (_ServerSync) Naming.lookup("rmi://"+ ip +":" + port + "/Chat");
				} catch (MalformedURLException | NotBoundException e) {
					e.printStackTrace();
				}
	}

	/**
	 * Envoyer un objet sur le serveur (pour diffusion aux autres utilisateurs).
	 * @param obj L'objet à envoyer
	 */
	public void send(Object obj) throws RemoteException {
		try {
			server.send(uName, obj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtenir le nom d'utilisateur.
	 * @return Le nom de l'utilisateur
	 */
	public String getuName() throws RemoteException {
		return uName;
	}
	
	/**
	 * Mettre à jour la liste des utilisateurs connectés (utilisé depuis le serveur avec RMI).
	 * @param activeUsers La liste des utilisateurs connectés
	 */
	public synchronized void setOnlineusers(ArrayList<String> activeUsers) throws RemoteException{
		updateUser(activeUsers);
	}

}
