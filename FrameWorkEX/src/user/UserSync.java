package user;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server._Server;
import server._ServerSync;

public abstract class UserSync extends UnicastRemoteObject implements _UserSync {
	protected _ServerSync server;
	protected String uName;

	
	public UserSync(String name) throws RemoteException{
		uName = name;
	}
	
	public UserSync() throws RemoteException{
	}
	
	/**
	 * Implement how the represent the user list one the user interface
	 * @param activeUsers ArrayList of users.
	 */
	public abstract void majUser(ArrayList<String> activeUsers) throws RemoteException ;

	/**
	 * Implement how to treat the received object.
	 * @param name Name of the user who send the Object
	 * @param obj Object sent
	 */
	public abstract void execute(String name, Object obj) throws RemoteException ;
	

	/**
	 * Get an object from the server (used on the server with RMI).
	 * @param name Name of the user who send the Object
	 * @param obj Object sent
	 */
	public synchronized void receiveObject(String name, Object obj)throws RemoteException {
		execute(name, obj);
	}

	/**
	 * Initialize the connection with a server
	 * @param ip Ip address of the server
	 * @param port Port number to connect
	 */
	public void importServer(String ip, int port) throws RemoteException{
				try {
					server = (_ServerSync) Naming.lookup("rmi://"+ ip + "/Chat");
				} catch (MalformedURLException | NotBoundException e) {
					e.printStackTrace();
				}
	}

	/**
	 * TODO : Completer
	 */
	public void send(Object obj) throws RemoteException {
		try {
			server.send(uName, obj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the user name.
	 * @return The username.
	 */
	public String getuName() throws RemoteException {
		return uName;
	}
	
	/**
	 * Update the user list, used by a server with RMI
	 * @param activeUsers ArrayList of users connected on the server
	 */
	public synchronized void setOnlineusers(ArrayList<String> activeUsers) throws RemoteException{
		majUser(activeUsers);
	}

}
