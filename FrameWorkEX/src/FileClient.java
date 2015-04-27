import java.io.*;
import java.net.MalformedURLException;
import java.rmi.*;

public class FileClient {
	
	private String SUrl;
	private FileInterface fi;
	private String IpServeur;
	
	public FileClient() {

	}
	
	public void connection(String IpServeur,int port) {
		try {
			this.IpServeur=IpServeur;
			SUrl= "/"+IpServeur+"/FileServer";
			//SUrl= "/"+IpServeur+"/FileServer";
			fi = (FileInterface) Naming.lookup(SUrl);

		} catch (Exception e) {
			System.err.println("FileServer exception: " + e.getMessage());
			e.printStackTrace();
		}		
	}
			
		public void Download(String fileInputName,String fileOutputName) {
		
			try {
				byte[] filedata = fi.downloadFile(fileInputName);
				File file = new File(fileOutputName);
				BufferedOutputStream output;
				output = new BufferedOutputStream(
						new FileOutputStream(file.getName()));
				output.write(filedata, 0, filedata.length);
				output.flush();
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		public Remote getObject(String objectname) {
			Remote r = null;
			try {
				r = Naming.lookup("/"+IpServeur+"/"+objectname);
				
			} catch (MalformedURLException | RemoteException
					| NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return r;
		}
		
	}
