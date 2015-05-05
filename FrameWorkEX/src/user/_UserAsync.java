package user;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface _UserAsync extends _User {
	public abstract Object takeObject(String name)throws RemoteException;
}
