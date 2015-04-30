package user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server._ServerDepot;

public class UserAsync extends UnicastRemoteObject  implements _UserAsync {
	_ServerDepot server;
	
	
	protected UserAsync() throws RemoteException {
		super();
		
	}

	@Override
	public void pull(String name) throws RemoteException {
		byte buffer[]  = server.pull(name);
		File file = new File(name);
		try {
		BufferedOutputStream output = new BufferedOutputStream(
				new FileOutputStream(file.getName()));
		
			output.write(buffer, 0, buffer.length);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(String fileName) throws RemoteException {
		File file = new File(fileName);
		byte buffer[] = new byte[(int) file.length()];
		try {
		BufferedInputStream input = new BufferedInputStream(
				new FileInputStream(fileName));
		
			input.read(buffer, 0, buffer.length);
		
		input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.deposit(buffer, fileName);
	}

	public void connectServer(String ipServer) throws RemoteException {
		try {
			server = (_ServerDepot) Naming.lookup("rmi://" + ipServer + "/Chat");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
