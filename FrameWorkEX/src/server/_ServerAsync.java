package server;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _ServerAsync extends Remote {
	public File takeObject(String name) throws RemoteException;
	public void addObject(String name) throws RemoteException;
	public byte[] sendObject(String name) throws RemoteException;
	public void connectServer(String name) throws RemoteException;
}
