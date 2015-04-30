package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _ServerDepot extends Remote {
	public void deposit(byte[] filedata, String name) throws RemoteException;
	public byte[] pull(String name) throws RemoteException;
}
