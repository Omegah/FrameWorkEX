package user;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _UserAsync extends Remote {
	public void pull(String name) throws RemoteException;
	public void deposit(String fileName)throws RemoteException;
	public void connectServer(String ipServer) throws RemoteException;
}
