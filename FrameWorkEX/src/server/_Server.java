package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _Server extends Remote{
	/**
	 * Création du serveur
	 * @param Ip L'adresse IP du serveur
	 * @param Port Le numéro de port pour la connexion
	 * @throws RemoteException
	 */
	public void createServer(String Ip, int Port) throws RemoteException;
}
