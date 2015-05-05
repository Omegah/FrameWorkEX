package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public abstract class ServerAsync extends UnicastRemoteObject implements _ServerAsync {


	protected ServerAsync() throws RemoteException {
		super();
	}


	public void sendObject(Object obj, String name) throws RemoteException{
		try {
			BufferedOutputStream output = new BufferedOutputStream(

					new FileOutputStream("files/" + name));
			output.write(((byte[])obj), 0, ((byte[])obj).length);
			output.flush();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
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
