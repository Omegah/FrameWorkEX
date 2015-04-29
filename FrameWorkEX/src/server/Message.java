package server;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;


public class Message extends UnicastRemoteObject implements MessageInterface {
	
    private String message;
    private String expediteur;
    private Calendar heure;

    public Message(String expediteur, String message)throws RemoteException
    {
    	this.expediteur = expediteur;
    	this.message=message;
        this.heure = Calendar.getInstance();
    }
    
    public Message()throws RemoteException {
    	
    	this.expediteur = "toast";
    	this.message="this is the test maggle";
        this.heure = Calendar.getInstance();
    }
    
    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getHeure()
    {
        return heure.get(Calendar.HOUR_OF_DAY) + ":" + heure.get(Calendar.MINUTE);
    }
    
    public String getExpediteur(){
    	return expediteur;
    }
    
    public String getMessageHeure()throws RemoteException
    {
        return getExpediteur() + "> [" + heure.get(Calendar.HOUR_OF_DAY) + ":" + heure.get(Calendar.MINUTE) + "] "
                + getMessage();
    }
    
}
