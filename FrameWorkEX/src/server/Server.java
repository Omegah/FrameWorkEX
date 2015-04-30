package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import user._User;
public class Server extends UnicastRemoteObject implements _Server{
	
	private Hashtable<String, Object> users;
	private Enumeration<Object> eUsers;
	private ArrayList<String> listUsers;
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
		listUsers = new ArrayList<String>();
	}
	
	public synchronized boolean registerUser(String name,Object newUser) {
		users.put(name, newUser);
		listUsers.add(name);
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_User user = ((_User) eUsers.nextElement());
			try {
				user.setOnlineusers(getActiveUsers());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}

	public synchronized void sendObject(String name, Object obj) {
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_User user = ((_User) eUsers.nextElement());
			try {
				user.receiveObject(name,obj);
			} catch (RemoteException ex) {
				//ex.printStackTrace();
				try {
					System.out.println("[TEST]->"+user.getuName());
					removeUser(user.getuName());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized void sendFile(String name, byte[] buf) {
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_User user = ((_User) eUsers.nextElement());
			try {
				user.receiveFile(name,buf);
			} catch (RemoteException ex) {
				ex.printStackTrace();
			}
		}
	}

	public synchronized void removeUser(String name) {
		users.remove(name);
		listUsers.remove(name);
		eUsers = users.elements();
		while (eUsers.hasMoreElements()) {
			_User user = ((_User) eUsers.nextElement());
			try {
				user.setOnlineusers(getActiveUsers());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getActiveUsers() {
		return listUsers;
	}
}
