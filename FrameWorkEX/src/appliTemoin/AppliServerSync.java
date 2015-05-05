package appliTemoin;

import java.rmi.RemoteException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import server.ServerSync;

public class AppliServerSync {

	public static void main(String[] args) throws RemoteException {
		String Ip = null;
		int Port = 0;
		
		JTextField ip = new JTextField();
		JTextField port = new JTextField();
		Object[] options = {"Adresse IP:", ip, "Port:",port };

		int option = JOptionPane.showConfirmDialog(null, options, "Création serveur",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			Ip = ip.getText();
			Port = Integer.parseInt(port.getText());
		} else {
			System.out.println("Création annulée");
			System.exit(0);
		}
		
		ServerSync server = new ServerSync();
		try {
			server.createServer(Ip, Port);
			JOptionPane.showMessageDialog(null, "Serveur lancé à l'adresse " + Ip + "[port:" + Port + "]");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
