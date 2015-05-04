package user;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _User extends Remote {
	public void receiveObject(String name, Object obj) throws RemoteException;
	public abstract void execute(String name, Object obj) throws RemoteException;
	public void importServer(String ip, int port) throws RemoteException;
	public void send(Object obj)throws RemoteException;

}
