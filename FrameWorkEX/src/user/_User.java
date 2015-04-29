package user;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _User extends Remote {
	public abstract void receiveObject(String name, Object obj) throws RemoteException;
	public abstract void execute(String name, Object obj)throws RemoteException;
	public abstract void start()throws RemoteException;
	  public String getuName() throws RemoteException;
	

}
