import java.io.*;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class FileServer {
	public static void main(String argv[]) {
		/*
		 * if(System.getSecurityManager() == null) {
		 * System.setSecurityManager(new RMISecurityManager()); }
		 */
		try {
			LocateRegistry.createRegistry(1099);

			FileInterface fi = new FileImpl("FileServer");
			Naming.rebind("//152.77.82.178/FileServer", fi);
		} catch (Exception e) {
			System.out.println("FileServer: " + e.getMessage());
			e.printStackTrace();
		}
	}
}