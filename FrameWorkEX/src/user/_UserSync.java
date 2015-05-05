package user;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface _UserSync extends _User{
	/**
	 * Abstraite: Implémenter la mise à jour de la représentation de la liste des utilisateurs connectés
	 * @param activeUsers Liste des utilisateurs connectés
	 * @throws RemoteException
	 */
	public abstract void updateUser(ArrayList<String> activeUsers)	throws RemoteException;
	
	/**
	 * Obtention du nom de l'utilisateur
	 * @return Le nom de l'utilisateur
	 * @throws RemoteException
	 */
	public String getuName() throws RemoteException;
	
	/**
	 * Mise à jour de la représentation de la liste des utilisateurs connectés
	 * Appel de la fonction abstraite updateUser()
	 * @param activeUsers La liste des utilisateurs connectés
	 * @throws RemoteException
	 */
	public void setOnlineusers(ArrayList<String> activeUsers) throws RemoteException;
}
