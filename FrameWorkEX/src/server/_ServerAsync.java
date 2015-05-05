package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _ServerAsync extends _Server {
	public void sendObject(Object obj) throws RemoteException;
	public Object takeObject(String name) throws RemoteException;
}
