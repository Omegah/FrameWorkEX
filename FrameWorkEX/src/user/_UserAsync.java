package user;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface _UserAsync extends _User {
	/**
	 * Récupérer un objet depuis le serveur
	 * @param name Nom de l'objet à récupérer
	 * @return L'objet récupéré
	 * @throws RemoteException
	 * @require !name.isEmpty()
	 */
	public abstract Object getObject(String name)throws RemoteException;
	
	/**
	 * Envoi d'un objet sur le serveur
	 * @param obj L'objet à envoyer
	 * @param name Le nom de l'objet
	 * @throws RemoteException
	 */
	public void send(Object obj, String name) throws RemoteException;
}
