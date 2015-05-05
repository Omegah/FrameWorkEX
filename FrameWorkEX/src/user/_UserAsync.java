package user;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface _UserAsync extends _User {
	/**
	 * TODO : A compléter
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	public abstract Object takeObject(String name)throws RemoteException;
	
	/**
	 * TODO : A compléter
	 * @param obj
	 * @param name
	 * @throws RemoteException
	 */
	public void send(Object obj, String name) throws RemoteException;
}
