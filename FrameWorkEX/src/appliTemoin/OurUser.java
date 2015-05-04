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
import javax.swing.JTextField;

import user.UserSync;


public class OurUser extends UserSync {

	TestWIndowBuilder windows;
	_Message message;
	String ipServer;
	int port;

	public OurUser() throws RemoteException {
		super();
		initialize();
		importServer(ipServer,port);
		
		while(!server.registerUser(uName, this)) {
			this.uName = JOptionPane.showInputDialog(null, "Pseudo non disponible\nChoisissez un nouveau pseudo : ");
		}
		windows.frame.setTitle(uName);
	}

	@Override
	public void execute(String filename, Object obj) {
		if(obj instanceof _Message){
			message = ((_Message) obj);
			try {
				windows.txtarea.append(message.getMessageHeure() + "\n");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			windows.txtarea.setCaretPosition(windows.txtarea.getDocument()
					.getLength());
			windows.frame.repaint();
		}
		else{
			try {
				BufferedOutputStream output = new BufferedOutputStream(
						new FileOutputStream("files/" + filename));
				output.write(((byte[])obj), 0, ((byte[])obj).length);
				output.flush();
				output.close();
				windows.txtarea.append("Partage du fichier \"" + filename + "\"\n");
				windows.txtarea.setCaretPosition(windows.txtarea.getDocument().getLength());
				windows.frame.repaint();

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
		
		JTextField pseudo = new JTextField();
		JTextField ip = new JTextField();
		JTextField port = new JTextField();
		Object[] options = {
				"Pseudo:", pseudo,
				"Adresse IP:", ip,
				"Port:", port
		};

		int option = JOptionPane.showConfirmDialog(null, options, "Login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			this.ipServer = ip.getText();
			this.port = Integer.parseInt(port.getText());
			this.uName = pseudo.getText();
		} else {
		    System.out.println("Login canceled");
		}
		
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
					server.send(windows.list_1.getSelectedValue().toString(), downloadFile(windows.list_1.getSelectedValue().toString()));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
