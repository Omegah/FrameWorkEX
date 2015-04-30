package user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server.Server;
import server._Server;

public abstract class User extends UnicastRemoteObject implements _User,
		Serializable {
	protected String uName;
	protected String ipServer;
	protected _Server server;
	protected ArrayList<String> values;

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public User() throws RemoteException {
	}

	public User(String uName, String ipServer) throws RemoteException {
		this.uName = uName;
		this.ipServer = ipServer;
		importServer();
		server.registerUser(uName, this);
	}

	public synchronized void receiveObject(String name, Object obj) {
		execute(name, obj);
	}

	public synchronized void receiveFile(String name, byte[] file) {
		executeFile(name, file);
	}

	public abstract void execute(String name, Object obj);

	public abstract void executeFile(String name, byte[] file);

	public abstract void start(String uName, Object obj);

	public synchronized void setOnlineusers(ArrayList<String> activeUsers) {
		blabla(activeUsers);
	}

	public synchronized byte[] downloadFile(String fileName) {
		try {
			File file = new File(fileName);
			byte buffer[] = new byte[(int) file.length()];
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream(file.getName()));
			input.read(buffer, 0, buffer.length);
			input.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public abstract void blabla(ArrayList<String> activeUsers);

	public void getUsers() {
		// TODO Auto-generated method stub
		try {
			values = server.getActiveUsers();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void importServer() {
		try {
			System.out.println(ipServer);
			server = (_Server) Naming.lookup("rmi://152.77.82.229/Chat"); // Génériser
																			// le
																			// nom
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
