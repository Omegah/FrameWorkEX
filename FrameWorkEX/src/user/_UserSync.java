package user;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface _UserSync extends _User{
	public abstract void majUser(ArrayList<String> activeUsers)	throws RemoteException;
	public String getuName() throws RemoteException;
	public void setOnlineusers(ArrayList<String> activeUsers) throws RemoteException;
}
