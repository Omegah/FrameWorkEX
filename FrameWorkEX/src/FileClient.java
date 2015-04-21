import java.io.*;
import java.rmi.*;

public class FileClient {
	public static void main(String argv[]) {
		/*
		 * if(argv.length != 2) {
		 * System.out.println("Usage: java FileClient fileName machineName");
		 * System.exit(0); }
		 */
		try {
			String name = "//localhost/FileServer";
			FileInterface fi = (FileInterface) Naming.lookup(name);
			byte[] filedata = fi.downloadFile("f1.txt");
			File file = new File("fcopy.txt");
			BufferedOutputStream output = new BufferedOutputStream(
					new FileOutputStream(file.getName()));
			output.write(filedata, 0, filedata.length);
			output.flush();
			output.close();
		} catch (Exception e) {
			System.err.println("FileServer exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}