import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.util.Enumeration;

public class FileServer {
	public static void main(String argv[]) {
		/*
		 * if(System.getSecurityManager() == null) {
		 * System.setSecurityManager(new RMISecurityManager()); }
		 */
		try {
			LocateRegistry.createRegistry(1099);
			FileInterface fi = new FileImpl("FileServer");
			int i = 0;
			String IP = null;

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

			Naming.rebind("/" + IP + "/FileServer", fi);
		} catch (Exception e) {
			System.out.println("FileServer: " + e.getMessage());
			e.printStackTrace();
		}
	}

}