package user;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server._ServerAsync;

public abstract class UserAsync extends UnicastRemoteObject implements _UserAsync {
	public UserAsync() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public _ServerAsync server;

	public abstract void execute(String name, Object obj) throws RemoteException;

	public void importServer(String ip, int port) throws RemoteException {
		try {
			server = (_ServerAsync) Naming.lookup("rmi://" + ip + "/Chat");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

	public void send(Object obj, String name) throws RemoteException {
		server.sendObject(obj, name);
	}
	
	public Object takeObject(String name) throws RemoteException{
		try {
			File file = new File("files/" + name);
			byte buffer[] = new byte[(int) file.length()];
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream("files/" + name));
			input.read(buffer, 0, buffer.length);
			input.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
