package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import user._UserSync;

/**
 * Classe ServerSync
 * Instanciation d'un serveur pour une communication synchrone multi-utilisateurs
 * @author Groupe 3
 */
public class ServerSync extends UnicastRemoteObject implements _ServerSync {

	private static final long serialVersionUID = 1L;
	protected Hashtable<String, Object> users;
	protected Enumeration<Object> eUsers;
	protected ArrayList<String> listUsers;
	protected String IpServer;

	public ServerSync() throws RemoteException {
		super();
	}

	/**
	 * Création d'un serveur et initialisation de la liste d'utilisateurs
	 * @param Ip Adresse IP du serveur
	 * @param Port Numéro du port pour créer la connexion
	 * @requires {@literal Port > 1000 && Port < 65535}
	 */
	public void createServer(String Ip, int Port) throws RemoteException {
		IpServer = Ip;
		LocateRegistry.createRegistry(Port);
		try {
			Naming.rebind("rmi://" + Ip + "/Chat", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		users = new Hashtable<String, Object>();
		listUsers = new ArrayList<String>();

	}

	/**
	 * Envoyer un objet aux utilisateurs connectés au serveur
	 * @param name Nom de l'expéditeur
	 * @param obj Objet à envoyer
	 */
	public void send(String name, Object obj) throws RemoteException {
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_UserSync user = ((_UserSync) eUsers.nextElement());
			try {
				user.receiveObject(name, obj);
			} catch (RemoteException ex) {
				try {
					System.out.println("Remove " + user.getuName());
					removeUser(user.getuName());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Ajout d'un utilisateur à la liste des utilisateurs
	 * @param name Nom de l'utilisateur à ajouter
	 * @param newUser Instance de l'utilisateur
	 * @return Vrai si l'ajout est réalisé, faux si le nom d'utilisateur est déjà utilisé
	 */
	public boolean registerUser(String name, _UserSync newUser)
			throws RemoteException {
		if (!users.containsKey(name)) {
			users.put(name, newUser);
			listUsers.add(name);
			eUsers = users.elements();
			while (eUsers.hasMoreElements()) {
				_UserSync user = ((_UserSync) eUsers.nextElement());
				try {
					user.setOnlineusers(getActiveUsers());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			return true;
		} else
			return false;

	}

	/**
	 * Suppression d'un utilisateur de la liste des utilisateurs
	 * @param name Nom de l'utilisateur à supprimer
	 * @requires {@literal getActiveUsers.size()> 0 && users.containsKey(name)}
	 */
	public void removeUser(String name) throws RemoteException {
		users.remove(name);
		listUsers.remove(name);
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_UserSync user = ((_UserSync) eUsers.nextElement());
			try {
				user.setOnlineusers(getActiveUsers());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Obtenir la liste des utilisateurs connectés
	 * @return La liste des utilisateurs
	 */
	public ArrayList<String> getActiveUsers() throws RemoteException {
		return listUsers;
	}

	/**
	 * Envoie d'un objet en privé à un utilisateur
	 * @param senderName Nom de l'expéditeur
	 * @param receiverName Nom du récepteur
	 * @param obj Objet à envoyer
	 */
	public void privateSend(String senderName, String receiverName, Object obj) throws RemoteException {
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_UserSync user = ((_UserSync) eUsers.nextElement());
			try {
				if ((user.getuName().equals(senderName))|| (user.getuName().equals(receiverName))) {
					user.receiveObject(senderName, obj);
				}
			} catch (RemoteException ex) {
				try {
					System.out.println("Remove " + user.getuName());
					removeUser(user.getuName());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
