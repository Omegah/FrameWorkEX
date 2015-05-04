package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _ServerAsync extends _Server {
	public void takeObject(Object obj) throws RemoteException;
	public Object sendObject(String name) throws RemoteException;
}
