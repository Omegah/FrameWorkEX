import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public class Test {

	public static void main(String[] args) throws RemoteException, UnknownHostException, MalformedURLException {
		// TODO Auto-generated method stub
		
		ObjectImpl ob = new ObjectImpl();
		
		///////////////////////
		// TEST DOWNLOAD FILE//
		///////////////////////
		
		//FileServer fs = new FileServer(1099,"FileServer");
	//	FileClient fc = new FileClient();
		//fc.connection("152.77.82.82",1099);
		//fc.Download("got.avi", "gott.avi");
			
		
		/*
		 * 
		 *  TEST BIND OBJECT
		 *
		 * fs.bindObjetc("toastt");
		Remote r = fc.getObject("toastt");
		System.out.println(r);
		
		if (r instanceof ObjectInterface) {
			String s = ((ObjectInterface) r).getInformation();
			System.out.println("chaine renvoyee = " + s);
		}
		 */
		
		//////////////////////
		
		SeverSynchrone ss = new SeverSynchrone("152.77.82.108",1699); 
		ClientAsynchrone CA= new ClientAsynchrone();
		CA.connection("152.77.82.108", 1699);
		Remote r =CA.getObject("Chat");
		
		Message m = new Message("boby","yoyo");
		((ObjectInterface) r).setObject(m);

	}

}
