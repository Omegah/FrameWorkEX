package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import user._UserSync;

public interface _ServerSync extends _Server {
	public boolean registerUser(String name,_UserSync newUser) throws RemoteException;
	public void removeUser(String name) throws RemoteException;
	public ArrayList<String> getActiveUsers() throws RemoteException;
	public void send(String name, Object obj) throws RemoteException;
	public void privateSend(String name, String name2, Object obj) throws RemoteException;
}
