import java.rmi.RemoteException;

import server.Server;

public class MainServer {

	public static void main(String[] args) {
		try {
			Server server = new Server("152.77.82.229", 1099);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
