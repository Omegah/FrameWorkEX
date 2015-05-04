package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface _Server extends Remote{
	public void createServer(String Ip, int Port) throws RemoteException;

}
