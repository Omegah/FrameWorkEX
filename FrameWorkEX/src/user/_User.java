package user;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface _User extends Remote {
	public void receiveObject(String name, Object obj) throws RemoteException;

	public void receiveFile(String name, byte[] file) throws RemoteException;

	public abstract void execute(String name, Object obj)
			throws RemoteException;

	public abstract void executeFile(String name, byte[] file)
			throws RemoteException;

	public abstract void blabla(ArrayList<String> activeUsers)
			throws RemoteException;

	public abstract void start(String uName, Object obj) throws RemoteException;

	public String getuName() throws RemoteException;

	public void setOnlineusers(ArrayList<String> activeUsers)
			throws RemoteException;

	public byte[] downloadFile(String fileName)
			throws RemoteException;

}
