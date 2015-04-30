import java.rmi.RemoteException;

import server.ServerSync;

public class MainServer {

	public static void main(String[] args) {
		try {
			ServerSync server = new ServerSync("152.77.82.229", 1099);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
