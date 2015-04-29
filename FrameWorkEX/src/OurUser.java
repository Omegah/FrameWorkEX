import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	public OurUser(String uName, String ipServer) throws RemoteException {
		super();

		initialize();
		this.uName = uName;
		super.ipServer = ipServer;
		importServer();
		server.registerUser(uName, this);

		// TODO Auto-generated constructor stub				
	}

	@Override
	public void execute(String name, Object obj) {
		// TODO Auto-generated method stub
		
		try {
			m =((MessageInterface)obj);
			System.out.println(m.getMessageHeure());
			windows.txtarea.append(m.getMessageHeure() + "\n");
			windows.txtarea.setCaretPosition(windows.txtarea.getDocument().getLength());
			windows.frame.repaint();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void executeFile(String name, byte[] file) {
		
		try {
			File dl = new File("testresult.txt");
			BufferedOutputStream output = new BufferedOutputStream(
					new FileOutputStream(dl.getName()));
			output.write(file,0,file.length);
			output.flush();
			output.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void start(String uName, Object obj) {
		// TODO Auto-generated method stub
		try {
			server.sendObject(uName, obj);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void blabla(ArrayList<String> activeUsers) {
		System.out.println("PASSAGE");
		windows.majUsers(activeUsers);
	}
	
	private void initialize() {
		windows = new TestWIndowBuilder();

		windows.textField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {

				Message m;
				try {
					m = new Message(uName, windows.textField.getText());
					start(uName, m);
					windows.textField.setText("");

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		windows.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					server.sendFile(uName, downloadFile(uName, windows.list_1.getSelectedValue().toString()));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void executeFile(String name, Object obj) {
		// TODO Auto-generated method stub
		
	}


}
