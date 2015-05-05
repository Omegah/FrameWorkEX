package user;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server._Server;
import server._ServerSync;

public abstract class UserSync extends UnicastRemoteObject implements _UserSync {
	protected _ServerSync server;
	protected String uName;

	
	public UserSync(String name) throws RemoteException{
		uName = name;
	}
	
	public UserSync() throws RemoteException{
	}
	
	/**
	 * Définir comment mettre à jour la liste des utilisateurs connectés (ex:interface graphique)
	 * @param activeUsers La liste des utilisateurs connectés
	 */
	public abstract void majUser(ArrayList<String> activeUsers) throws RemoteException ;

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
		majUser(activeUsers);
	}

}
