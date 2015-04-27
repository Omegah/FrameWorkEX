import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;



public class ServerChatImpl extends UnicastRemoteObject implements ServerChat
{
	
    private String adress;
    private int port;
    // Une table de hash contenant les URL des clients distants et le nom de des utilisateurs.
    private HashMap clients;
	
    protected ServerChatImpl(String adress,int port) throws RemoteException {
        super();
        this.adress = adress;
        this.port = port;
        clients = new HashMap();
	}
    
    public void rmiEnregistrer() throws RemoteException, IllegalArgumentException, MalformedURLException
    {
     //   System.out.println(ressources.getString("ServeurChatImpl.RMIRegister"));
        LocateRegistry.createRegistry(port);
        // Lie a l'adresse indiquee a l'objet distant ServeurChatImpl.
        Naming.rebind("rmi://" + adress + ":" + port + "/Chat", this);
    }

    public void enregistrer(String url) throws RemoteException
    {
        // Recupere une reference (stub) pour le client distant associe a cette adresse.
       // ClientDistant client = null;


        // Recupere le pseudo de la personne qui entre dans le chat.
    //    String s = client.getUtilisateur();

        synchronized(clients)
        {
            // Ajoute l'URL du client distant et le nom de l'utilisateur.
          //  clients.put(url, s);
        }
    }

    public void desenregistrer(String url) throws RemoteException
    {
        synchronized(clients)
        {
            clients.remove(url);
        }

    }

    public HashMap getClients() throws RemoteException
    {
        synchronized(clients)
        {
            return clients;
        }
    }
}
