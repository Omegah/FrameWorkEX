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

public class ServerDepot extends UnicastRemoteObject implements _ServerDepot {

	public ServerDepot(int Port, String ip) throws RemoteException {

		try {

			LocateRegistry.createRegistry(Port);
			try {
				Naming.rebind("rmi://" + ip + "/Chat", this);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(byte[] filedata, String name) throws RemoteException {
	
		File file = new File(name);
		try {
		BufferedOutputStream output = new BufferedOutputStream(
				new FileOutputStream(file.getName()));
		
			output.write(filedata, 0, filedata.length);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public byte[] pull(String name) throws RemoteException {
		File file = new File(name);
		byte buffer[] = new byte[(int) file.length()];
		try {
		BufferedInputStream input = new BufferedInputStream(
				new FileInputStream(name));
		
			input.read(buffer, 0, buffer.length);
		
		input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (buffer);

	}

}
