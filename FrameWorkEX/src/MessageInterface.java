import java.rmi.Remote;
import java.rmi.RemoteException;


public interface MessageInterface extends Remote {

    public String getMessageHeure()throws RemoteException;
    public void setMessage(String message)throws RemoteException;

}
