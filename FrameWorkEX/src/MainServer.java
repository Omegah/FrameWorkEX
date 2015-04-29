import java.rmi.RemoteException;

import server.Server;


public class MainServer {

	public MainServer() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Server server = new Server("152.77.82.229",1099);
			OurUser user = new OurUser("louis", "152.77.82.229");
			//OurUser user2 = new OurUser("paul", "152.77.82.207");
			//OurUser user3 = new OurUser("jean", "152.77.82.207");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
