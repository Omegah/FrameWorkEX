package user;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import server._ServerAsync;

/**
 * Classe UserServer
 * 
 * Classe concrète : Proposition de transfert de fichier entre
 * deux utilisateurs.
 * Chaque utilisteur est aussi serveur pour permettre le partage
 * de fichiers sans passer par un serveur à proprement parlé.
 * 
 * @author Groupe 3
 *
 */
public class UserServer extends UserAsync implements _ServerAsync {

	public UserServer() throws RemoteException {
		super();
	}
	
	/**
	 * Création du serveur
	 * @param Ip Adresse IP du serveur
	 * @param Port Numéro de port pour la connexion
	 */
	public void createServer(String Ip, int Port) throws RemoteException {
		try {

			LocateRegistry.createRegistry(Port);
			try {
				Naming.rebind("rmi://" + Ip + "/Chat", this);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Envoie d'un objet à un utilisateur.
	 * @param obj L'objet à envoyer
	 * @param name Le nom de l'objet
	 */
	public void send(Object obj, String name) throws RemoteException {
		try {
			BufferedOutputStream output = new BufferedOutputStream(
					new FileOutputStream("files/" + name));
			output.write(((byte[]) obj), 0, ((byte[]) obj).length);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Récuperer un objet sur le serveur
	 * @param name Le nom de l'objet à télécharger
	 */
	public Object getObject(String name) throws RemoteException {
		try {
			File file = new File("files/" + name);
			byte buffer[] = new byte[(int) file.length()];
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream("files/" + name));
			input.read(buffer, 0, buffer.length);
			input.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Récupération d'un objet sur le serveur
	 * @param name Nom de l'objet
	 * @param obj Objet à envoyer
	 */
	public synchronized void receiveObject(String name, Object obj) throws RemoteException {
		execute(name,obj);
	}

	@Override
	public void send(Object obj) throws RemoteException {
	}

	/**
	 * Traitement de l'objet : téléchargement
	 * @param name Nom de l'objet
	 * @param obj Objet à envoyer
	 */
	public void execute(String name, Object obj) throws RemoteException {
		if (obj instanceof byte[]) {
			try {
				BufferedOutputStream output = new BufferedOutputStream(

				new FileOutputStream("files/" + name));
				output.write(((byte[]) obj), 0, ((byte[]) obj).length);
				output.flush();
				output.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}