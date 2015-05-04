package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import user._UserSync;


public class ServerSync extends UnicastRemoteObject implements _ServerSync{

	private static final long serialVersionUID = 1L;
	private Hashtable<String, Object> users;
	private Enumeration<Object> eUsers;
	private ArrayList<String> listUsers;
	
	public ServerSync() throws RemoteException {
		super();
	}
	
	/**
	 * Start the server on a port number and initialization of the user list.
	 * @param Ip Ip adress of the server
	 * @param Port Port number to create the connection
	 */
	public void createServer(String Ip, int Port) throws RemoteException {
		LocateRegistry.createRegistry(Port);
        try {
			Naming.rebind("rmi://"+Ip+"/Chat", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		users = new Hashtable<String, Object>();
		listUsers = new ArrayList<String>();
		
	}
	
	/**
	 * Send an object to each user connected on the server.
	 * @param name Name of the user who send the object
	 * @param obj Object to send
	 */
	public void send(String name, Object obj) throws RemoteException {
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_UserSync user = ((_UserSync) eUsers.nextElement());
			try {
				user.receiveObject(name,obj);
			} catch (RemoteException ex) {
				//ex.printStackTrace();
				try {
					System.out.println("Remove "+user.getuName());
					removeUser(user.getuName());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}


	/**
	 * Register user in the user list
	 * @param name Name of the user to register
	 * @param newUser Instance of the user to register
	 * @return Vrai si l'ajout est réalisé, faux si le nom d'utilisateur est déjà utilisé
	 */
	public boolean registerUser(String name, _UserSync newUser) throws RemoteException {
		if(!users.containsKey(name)) {
			users.put(name, newUser);
			listUsers.add(name);
			eUsers = users.elements();
			while (eUsers.hasMoreElements()) {
				_UserSync user = ((_UserSync) eUsers.nextElement());
				try {
					user.setOnlineusers(getActiveUsers());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			return true;
		}
		else return false;
		
		
	}

	/**
	 * Remove a user in the user list.
	 * @param name The name of the user to remove
	 */
	public void removeUser(String name) throws RemoteException {
		users.remove(name);
		listUsers.remove(name);
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_UserSync user = ((_UserSync) eUsers.nextElement());
			try {
				user.setOnlineusers(getActiveUsers());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Get list of users.
	 * @return ArrayList of users (String)
	 */
	public ArrayList<String> getActiveUsers() throws RemoteException {
		return listUsers;
	}

}
