package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public interface _Server extends Remote {
	public boolean registerUser(String name,Object user) throws RemoteException;
	public void sendObject(String name, Object obj) throws RemoteException;
	public void removeUser(String name) throws RemoteException;
	public ArrayList<String> getActiveUsers() throws RemoteException;

}
