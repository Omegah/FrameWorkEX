package user;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _User extends Remote {
	
	/**
	 * Récupération d'un objet envoyé
	 * Appel de la fonction abstraite execute
	 * @param name Le nom de l'objet
	 * @param obj L'objet à récupérer
	 * @throws RemoteException Problème d'appel d'une méthode distante
	 */
	public void receiveObject(String name, Object obj) throws RemoteException;
	
	/**
	 * Abstraite: Traiter l'objet reçu.
	 * @param name Le nom de l'objet
	 * @param obj L'objet récupéré
	 * @throws RemoteException Problème d'appel d'une méthode distante
	 */
	public abstract void execute(String name, Object obj) throws RemoteException;
	
	/**
	 * Initialisation de la connexion avec un serveur
	 * @param ip L'adresse IP du serveur
	 * @param port Le numéro du port pour la connexion
	 * @throws RemoteException Problème d'appel d'une méthode distante
	 * @requires {@literal !ip.isEmpty() && port >1000 && port < 65535}
	 */
	public void importServer(String ip, int port) throws RemoteException;
	
	/**
	 * Envoie d'un objet sur le serveur.
	 * @param obj L'objet à envoyer
	 * @throws RemoteException Problème d'appel d'une méthode distante
	 */
	public void send(Object obj)throws RemoteException;

}
