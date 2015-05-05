package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _Server extends Remote{
	/**
	 * Création du serveur
	 * @param Ip L'adresse IP du serveur
	 * @param Port Le numéro de port pour la connexion
	 * @throws RemoteException
	 * @require !ip.isEmpty() && port>1000 && port < 65535
	 */
	public void createServer(String ip, int port) throws RemoteException;
}
