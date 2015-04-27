import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public class ClientAsynchrone {

    private String adresseS;
    private int portS;
    private String pseudo;
	private String SUrl;
	private ObjectInterface ob;
	private MessageInterface m;
	private String IpServeur;
	
	public ClientAsynchrone(){
		
	}
	
	public ClientAsynchrone(String adress,int port,String pseudo) {	
		this.adresseS=adress;
		this.portS=port;
		this.pseudo=pseudo;
	}
	
	
	public void connection(String IpServeur,int port) {
		try {
			this.portS=port;
			this.IpServeur=IpServeur;
			SUrl= "//"+IpServeur+":"+port+"/Chat";
			//SUrl= "/"+IpServeur+"/FileServer";
			ob = (ObjectInterface) Naming.lookup(SUrl);

		} catch (Exception e) {
			System.err.println("FileServer exception: " + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	public Remote getObject(String objectname) {
		Remote r = null;
		try {
			r = Naming.lookup("//"+IpServeur+":"+portS+"/"+objectname);
			
		} catch (MalformedURLException | RemoteException
				| NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

}
