package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Hashtable;

import user._User;
public class Server extends UnicastRemoteObject implements _Server{
	
	private Hashtable<String, Object> users;
	private Enumeration<Object> eUsers;
	private Message m;
	
	public Server(String adress,int port) throws RemoteException
	{
        m = new Message();
		LocateRegistry.createRegistry(port);
        try {
			Naming.rebind("rmi://"+adress+"/Chat", this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		users = new Hashtable<String, Object>();
	}
	
	public synchronized boolean registerUser(String name,Object user) {
		users.put(name, user);
		return true;
	}

	public synchronized void sendObject(String name, Object obj) {
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_User user = ((_User) eUsers.nextElement());
			try {
				System.out.println(user.getuName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				user.receiveObject(name,obj);
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		}
	}

	public synchronized void removeUser(String name) {
		users.remove(name);
	}
}
