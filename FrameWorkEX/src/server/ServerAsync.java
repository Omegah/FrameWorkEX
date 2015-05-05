package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public abstract class ServerAsync extends UnicastRemoteObject implements _ServerAsync {


	protected ServerAsync() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract void sendObject(Object obj) throws RemoteException;

	@Override
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
}
