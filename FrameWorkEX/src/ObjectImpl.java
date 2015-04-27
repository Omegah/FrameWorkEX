import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ObjectImpl extends UnicastRemoteObject implements ObjectInterface {
	 
	private static final long serialVersionUID = 2674880711467464646L;
	private Object objet;
			protected ObjectImpl() throws RemoteException {
			super();
			}

		public String getInformation() throws RemoteException {
			System.out.println("Invocation de la méthode getInformation()");
			return "test";
		}
		public void setObject(Object o){
			objet = o;
		}
		
		public Object getObject(){
			return objet;
		}

	}