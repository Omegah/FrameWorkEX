package appliTemoin;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;


public class Message extends UnicastRemoteObject implements _Message {
	
    private String message;
    private String expediteur;
    private Calendar heure;

	/**
	 * Constructeur de Message
	 * 
	 * @param expediteur Expediteur du message
	 * @param message Texte du message
	 * @throws RemoteException Problème d'appel d'une méthode distante
	 */
    public Message(String expediteur, String message)throws RemoteException
    {
    	this.expediteur = expediteur;
    	this.message=message;
        this.heure = Calendar.getInstance();
    }
    
    public Message()throws RemoteException {

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
