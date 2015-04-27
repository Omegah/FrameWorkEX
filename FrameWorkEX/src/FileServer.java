import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.Enumeration;

public class FileServer {
	String IP=null;
	private String url;
	public FileServer(int port,String nameServer) {
		try {
			/*
			 * if(System.getSecurityManager() == null) {
			 * System.setSecurityManager(new RMISecurityManager()); }
			 */
			LocateRegistry.createRegistry(port);
			FileInterface fi = new FileImpl(nameServer);
			int i = 0;

			Enumeration en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) en.nextElement();
				Enumeration ee = ni.getInetAddresses();
				while (ee.hasMoreElements()) {
					InetAddress ia = (InetAddress) ee.nextElement();
					if (i == 1)
						IP = ia.toString();
					System.out.println(ia.getHostAddress());
					i++;
				}
			}
			System.out.println(IP);

			Naming.rebind("/" + IP + "/"+nameServer, fi);
		} catch (Exception e) {
			System.out.println("FileServer: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getIpServer() {
		return IP;
	}
	
	public void bindObjetc(String nameObject) throws RemoteException, UnknownHostException, MalformedURLException {
		ObjectImpl objectImpl = new ObjectImpl();

		url = "/" +IP + "/"+nameObject;
		System.out.println("Enregistrement de l'objet avec l'url : " + url);
		Naming.rebind(url, objectImpl);
	}
	
	public void listBindObjects() throws RemoteException, MalformedURLException{
		
		String [] objetsDispo = Naming.list(url);

		for(String o : objetsDispo)
			System.out.println(o);		
	}
	
}