package appliTemoin;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import user.UserAsync;

public class OurUserAsync extends UserAsync {

	protected OurUserAsync() throws RemoteException {
		super();
	}


	@Override
	public void execute(String name, Object obj) throws RemoteException {
		if(obj instanceof byte[]){
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

	}

	@Override
	public void send(Object obj) throws RemoteException {
		
	}


	@Override
	public void receiveObject(String name, Object obj) throws RemoteException {
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



}
