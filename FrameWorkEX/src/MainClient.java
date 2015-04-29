import java.rmi.RemoteException;


public class MainClient {


	public static void main(String[] args) {

		try {
			OurUser user = new OurUser("sdfgqsdfqsdf", "152.77.82.229");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
