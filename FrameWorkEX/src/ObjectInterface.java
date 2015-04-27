import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ObjectInterface extends Remote {

	public String getInformation() throws RemoteException;
	public void setObject(Object o) throws RemoteException;
	
	public Object getObject() throws RemoteException;

}
