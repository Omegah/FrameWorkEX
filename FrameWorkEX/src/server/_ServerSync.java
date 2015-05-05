package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import user._UserSync;

public interface _ServerSync extends _Server {
	/**
	 * Ajout d'un utilisateur à la liste des utilisateurs connectés au serveur
	 * @param name Le nom de l'utilisateur à ajouter
	 * @param newUser L'objet utilisateur à ajouter
	 * @return Vrai si succès, faux si le nom est déjà utilisé (ajout impossible)
	 * @throws RemoteException
	 */
	public boolean registerUser(String name,_UserSync newUser) throws RemoteException;
	
	/**
	 * Suppression d'un utilisateur de la liste des utilisateurs connectés au serveur
	 * @param name Le nom de l'utilisateur à supprimer
	 * @throws RemoteException
	 * @require getActiveUsers.size()>0 && users.containsKey(name)
	 */
	public void removeUser(String name) throws RemoteException;
	
	/**
	 * Obtention de la liste des utilisateurs connectés au serveur
	 * @return La liste des utilisateurs connectés
	 * @throws RemoteException
	 */
	public ArrayList<String> getActiveUsers() throws RemoteException;
	
	/**
	 * Envoie d'un objet sur le serveur
	 * @param name Le nom de l'objet
	 * @param obj L'objet à envoyer
	 * @require !name.isEmpty()
	 */
	public void send(String name, Object obj) throws RemoteException;
	
	/**
	 * Envoie d'un objet à un seul utilisateur
	 * @param senderName Le nom de l'utilisateur expéditeur
	 * @param receiverName Le nom de l'utilisateur récepteur
	 * @param obj L'objet envoyé
	 * @throws RemoteException
	 */
	public void privateSend(String senderName, String receiverName, Object obj) throws RemoteException;
}
