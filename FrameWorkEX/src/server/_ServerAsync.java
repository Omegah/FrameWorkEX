package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _ServerAsync extends _Server {
	
	/**
	 * Envoi d'un objet sur le serveur
	 * @param obj L'objet à envoyer
	 * @param name Le nom de l'objet
	 * @throws RemoteException
	 */
	public void send(Object obj, String name) throws RemoteException;
	
	/**
	 * Récupération d'un objet depuis le serveur
	 * @param name Le nom de l'objet à récupérer
	 * @return L'objet récupéré
	 * @throws RemoteException
	 */
	public Object getObject(String name) throws RemoteException;
}
