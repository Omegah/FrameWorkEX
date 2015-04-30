import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;

import server.Server;
import user.User;
import user._User;

public class OurUser extends User {

	MessageInterface m;
	TestWIndowBuilder windows;

	public OurUser(String osef, String ipServer) throws RemoteException {
		super();

		initialize();
		
		this.uName = JOptionPane.showInputDialog("Entrez votre nom d'utilisateur");
		super.ipServer = ipServer;
		importServer();
		windows.frame.setTitle(uName);
		server.registerUser(uName, this);
	}

	@Override
	public void execute(String name, Object obj) {

		try {
			m = ((MessageInterface) obj);
			// System.out.println(m.getMessageHeure());
			windows.txtarea.append(m.getMessageHeure() + "\n");
			windows.txtarea.setCaretPosition(windows.txtarea.getDocument()
					.getLength());
			windows.frame.repaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void executeFile(String name, byte[] file) {

		try {
			File dl = new File("testresult.txt");
			BufferedOutputStream output = new BufferedOutputStream(
					new FileOutputStream(dl.getName()));
			output.write(file, 0, file.length);
			output.flush();
			output.close();
			if (!name.equals(uName)) {
				windows.txtarea.append(name + " vous a envoyé un fichier !\n");
				windows.txtarea.setCaretPosition(windows.txtarea.getDocument()
						.getLength());
				windows.frame.repaint();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start(String uName, Object obj) {
		try {
			server.sendObject(uName, obj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void blabla(ArrayList<String> activeUsers) {
		windows.majUsers(activeUsers);
	}

	private void initialize() {
		windows = new TestWIndowBuilder();
		
		windows.frame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		    	try {
					server.removeUser(uName);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
		    }
		});

		windows.textField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {

				Message m;
				try {
					m = new Message(uName, windows.textField.getText());
					start(uName, m);
					windows.textField.setText("");

				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});

		windows.btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Message m;
				try {
					m = new Message(uName, windows.textField.getText());
					start(uName, m);
					windows.textField.setText("");

				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		windows.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					server.sendFile(
							uName,
							downloadFile(windows.list_1.getSelectedValue().toString()));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
