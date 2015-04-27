import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


public interface ServerChat extends Remote
{

    public void enregistrer(String url) throws RemoteException;

    public void desenregistrer(String url) throws RemoteException;

    public HashMap getClients() throws RemoteException;
    
}