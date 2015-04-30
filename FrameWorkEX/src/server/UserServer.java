package server;

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
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class UserServer extends UnicastRemoteObject implements _ServerAsync {

	private _ServerAsync server;
	
	public UserServer(int Port, String ip) throws RemoteException {
		
		try {
			
			LocateRegistry.createRegistry(Port);
			try {
				Naming.rebind("rmi://"+ ip +"/Chat", this);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public _ServerAsync getServ(){
		return server;
	}
	
	@Override
	public void takeObject(String name) throws RemoteException {
		// TODO Auto-generated method stub
		byte[] filedata = server.sendObject(name);
		File file = new File(name);
		try {
		BufferedOutputStream output = new BufferedOutputStream(
				new FileOutputStream(file.getName()));
		
			output.write(filedata, 0, filedata.length);
			output.flush();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public byte[] sendObject(String name) throws RemoteException {
		File file = new File(name);
		byte buffer[] = new byte[(int) file.length()];
		try {
		BufferedInputStream input = new BufferedInputStream(
				new FileInputStream(name));
		
			input.read(buffer, 0, buffer.length);
		
		input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (buffer);
	}



	@Override
	public void connectServer(String ipServer) throws RemoteException {
		try {
			server = (_ServerAsync) Naming.lookup("rmi://" + ipServer + "/Chat");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
