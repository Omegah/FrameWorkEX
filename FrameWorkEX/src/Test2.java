import java.rmi.Remote;
import java.rmi.RemoteException;


public class Test2 {

	public Test2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		ClientAsynchrone CA= new ClientAsynchrone();
		CA.connection("152.77.82.108", 1699);
		Remote r =CA.getObject("Chat");
		
		if(r instanceof ObjectInterface) {
			String s = ((MessageInterface)((ObjectInterface) r).getObject()).getMessageHeure();
			System.out.println(s);
		}
	}

	
	
	
}
