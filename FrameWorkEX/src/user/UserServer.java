package user;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import server._ServerAsync;

public abstract class UserServer extends UserAsync implements _ServerAsync{

	protected UserServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createServer(String Ip, int Port) throws RemoteException {
		try {

			LocateRegistry.createRegistry(Port);
			try {
				Naming.rebind("rmi://" + Ip + "/Chat", this);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public abstract void takeObject(Object obj) throws RemoteException ;

	@Override
	public abstract Object sendObject(String name) throws RemoteException;

	@Override
	public abstract void receiveObject(String name, Object obj) throws RemoteException;

}
