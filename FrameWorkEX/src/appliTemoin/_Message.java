package appliTemoin;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface _Message extends Remote {
    public String getMessageHeure()throws RemoteException;
    public void setMessage(String message)throws RemoteException;

}
