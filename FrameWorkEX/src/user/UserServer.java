package user;

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

import server._ServerAsync;

public class UserServer extends UserAsync implements _ServerAsync {

	public UserServer() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
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

	public void sendObject(Object obj, String name) throws RemoteException {
		try {
			BufferedOutputStream output = new BufferedOutputStream(

			new FileOutputStream("files/" + name));
			output.write(((byte[]) obj), 0, ((byte[]) obj).length);
			output.flush();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object takeObject(String name) throws RemoteException {
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
	public void receiveObject(String name, Object obj) throws RemoteException {
		try {
			BufferedOutputStream output = new BufferedOutputStream(

			new FileOutputStream("files/" + name));
			output.write(((byte[]) obj), 0, ((byte[]) obj).length);
			output.flush();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void send(Object obj) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(String name, Object obj) throws RemoteException {
		if (obj instanceof byte[]) {
			try {
				BufferedOutputStream output = new BufferedOutputStream(

				new FileOutputStream("files/" + name));
				output.write(((byte[]) obj), 0, ((byte[]) obj).length);
				output.flush();
				output.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}