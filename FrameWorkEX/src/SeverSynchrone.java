import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Locale;
import java.util.ResourceBundle;


public class SeverSynchrone {

    private String adresse;
    private int port;


    public SeverSynchrone(String adresse,int port) throws RemoteException, MalformedURLException
    {
    	this.adresse=adresse;
    	this.port=port;   	
    	//ObjectInterface ob = new ObjectImpl();
    	
    	ObjectInterface o = new ObjectImpl();
    	
        //   System.out.println(ressources.getString("ServeurChatImpl.RMIRegister"));
        LocateRegistry.createRegistry(port);
        // Lie a l'adresse indiquee a l'objet distant ServeurChatImpl.
        Naming.rebind("//" + adresse + ":" + port + "/Chat", o);
    }

}
