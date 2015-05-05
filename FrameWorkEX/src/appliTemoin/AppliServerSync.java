package appliTemoin;

import java.awt.Font;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.ServerSync;

public class AppliServerSync {

	public static void main(String[] args) throws RemoteException {
		String ip = null;
		int port = 0;
		
		JTextField tfIp = new JTextField();
		JTextField tfPort = new JTextField();
		Object[] options = {"Adresse IP:", tfIp, "Port:",tfPort };

		int option = JOptionPane.showConfirmDialog(null, options, "Création serveur",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			ip = tfIp.getText();
			port = Integer.parseInt(tfPort.getText());
		} else {
			System.out.println("Création annulée");
			System.exit(0);
		}
		
		ServerSync server = new ServerSync();
		try {
			server.createServer(ip, port);
			JFrame frame = new JFrame();
			frame.setBounds(100, 100, 200, 100);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			JTextArea affichage = new JTextArea();
			affichage.setBounds(0, 0, 200, 100);
			affichage.append("Serveur connecté\nIP: " + ip + "\nPORT: " + port);
			frame.add(affichage);
			frame.setVisible(true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
