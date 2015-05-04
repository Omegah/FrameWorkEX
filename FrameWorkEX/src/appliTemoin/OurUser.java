package appliTemoin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import user.UserSync;


public class OurUser extends UserSync {

	TestWIndowBuilder windows;
	_Message message;

	public OurUser(String ipServer, int port) throws RemoteException {
		super();
		initialize();
		
		this.uName = JOptionPane.showInputDialog("Entrez votre nom d'utilisateur");
		importServer(ipServer,port);
		windows.frame.setTitle(uName);
		server.registerUser(uName, this);
	}

	@Override
	public void execute(String name, Object obj) {
		if(obj instanceof _Message){
			message = ((_Message) obj);
			try {
				windows.txtarea.append(message.getMessageHeure() + "\n");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			windows.txtarea.setCaretPosition(windows.txtarea.getDocument()
					.getLength());
			windows.frame.repaint();
		}
		else{
			try {
				File dl = new File("testresult.txt");
				BufferedOutputStream output = new BufferedOutputStream(
						new FileOutputStream(dl.getName()));
				output.write(((byte[])obj), 0, ((byte[])obj).length);
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
	}

	public void send(String uName, Object obj) {
		try {
			server.send(uName, obj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void majUser(ArrayList<String> activeUsers) {
		windows.majUsers(activeUsers);
	}

	public synchronized byte[] downloadFile(String fileName) {
		try {
			File file = new File("files/"+fileName);
			byte buffer[] = new byte[(int) file.length()];
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream("files/" + fileName));
			input.read(buffer, 0, buffer.length);
			input.close();
			return buffer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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

				_Message message;
				try {
					message = new Message(uName, windows.textField.getText());
					send(uName, message);
					windows.textField.setText("");

				} catch (RemoteException e) {
					e.printStackTrace();
				}

			}
		});

		windows.btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Message message;
				try {
					message = new Message(uName, windows.textField.getText());
					send(uName, message);
					windows.textField.setText("");

				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		windows.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					server.send(uName, downloadFile(windows.list_1.getSelectedValue().toString()));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
