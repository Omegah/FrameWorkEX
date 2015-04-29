package user;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.Server;
import server._Server;

public abstract class User extends UnicastRemoteObject implements _User, Serializable {
	protected String uName;
	private String ipServer;
	protected _Server server;
	
	
	  public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}


	public User(String uName, String ipServer) throws RemoteException
 {
		this.uName = uName;
		this.ipServer = ipServer;
		importServer();
		server.registerUser(uName, this);
	}

	public synchronized void receiveObject(String name, Object obj) {
		execute(name,obj);
	}
	
	public abstract void execute(String name, Object obj);
	public abstract void start();
	
	public void importServer() {
		try {
			System.out.println(ipServer);
			server =  (_Server) Naming.lookup("rmi://152.77.82.229/Chat"); // Génériser le nom
		} catch (RemoteException ex) {
			ex.printStackTrace();
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (NotBoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public String getName() {
		return uName;
	}
}
