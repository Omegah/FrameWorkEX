package user;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server._ServerAsync;



public abstract class UserAsync extends UnicastRemoteObject implements _UserAsync {
protected UserAsync() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

protected _ServerAsync server;
	@Override
	public abstract void receiveObject(String name, Object obj) throws RemoteException;
	
	@Override
	public void execute(String name, Object obj) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void importServer(String ip, int port) throws RemoteException {
		try {
			server = (_ServerAsync) Naming.lookup("rmi://" + ip + "/Chat");
	} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void send(Object obj) throws RemoteException {
		server.takeObject(obj);
	}

}
