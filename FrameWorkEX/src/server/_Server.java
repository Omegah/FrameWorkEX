package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _Server extends Remote {
	public boolean registerUser(String name,Object user) throws RemoteException;
	public void sendObject(String name, Object obj) throws RemoteException;
	public void removeUser(String name) throws RemoteException;
}
