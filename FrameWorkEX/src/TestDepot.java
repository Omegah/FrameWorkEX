import java.rmi.RemoteException;

import server.ServerDepot;
import server.UserServer;


public class TestDepot {

	public TestDepot() {
		// TODO Auto-generated constructor stub	
	}
	
	public static void main(String[] args) {
		
		ServerDepot serv = null;
			try {
				serv = new ServerDepot(1099, "152.77.82.229");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
