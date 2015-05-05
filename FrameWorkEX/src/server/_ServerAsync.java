package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _ServerAsync extends _Server {
	
	/**
	 * TODO : A compléter
	 * @param obj
	 * @param name
	 * @throws RemoteException
	 */
	public void send(Object obj, String name) throws RemoteException;
	
	/**
	 * TODO : A compléter
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	public Object takeObject(String name) throws RemoteException;
}
