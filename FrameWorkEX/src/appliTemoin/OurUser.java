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

/**
 * Classe concrète : Implémentation de la classe UserSync pour l'application témoin
 * @author Groupe 3
 *
 */
public class OurUser extends UserSync {

	PrototypeFrame windows;
	_Message message;
	String ipServer;
	int port;

	public OurUser() throws RemoteException {
		super();
		initialize();
		importServer(ipServer, port);

		while (!server.registerUser(uName, this)) {
			this.uName = JOptionPane.showInputDialog(null,
					"Pseudo non disponible\nChoisissez un nouveau pseudo : ");
		}
		windows.frame.setTitle(uName);
	}

	@Override
	public void execute(String filename, Object obj) {
		if (obj instanceof _Message) {
			message = ((_Message) obj);
			try {
				windows.textArea.append(message.getMessageHeure() + "\n");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			windows.textArea.setCaretPosition(windows.textArea.getDocument()
					.getLength());
			windows.frame.repaint();
		} else {
			try {
				BufferedOutputStream output = new BufferedOutputStream(

				new FileOutputStream("files/" + filename));
				output.write(((byte[]) obj), 0, ((byte[]) obj).length);
				output.flush();
				output.close();

				windows.textArea.append("Partage du fichier \"" + filename
						+ "\"\n");
				windows.textArea.setCaretPosition(windows.textArea.getDocument()
						.getLength());
				windows.frame.repaint();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void send(String uName, Object obj) throws RemoteException {

		if (obj instanceof Message && !((Message) obj).getMessage().equals("")) {
			if (((Message) obj).getMessage().charAt(0) == '/') {
				String parts[] = ((Message) obj).getMessage().split(" ");
				String destinataire = parts[0];
				destinataire = destinataire.substring(1, destinataire.length());
				String m = "**";

				for (int i = 1; i < parts.length; i++)
					m = m + " " + parts[i];
				((Message) obj).setMessage(m + " **");
				sendWhisp(obj, destinataire);
			} else {
				try {
					server.send(uName, obj);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				server.send(uName, obj);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

	}

	public void sendWhisp(Object obj, String name) throws RemoteException {
		try {
			server.privateSend(uName, name, obj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(ArrayList<String> activeUsers) {
		windows.updateUsers(activeUsers);
	}

	public synchronized byte[] downloadFile(String fileName) {
		try {
			File file = new File("files/" + fileName);
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
		JTextField port = new JTextField("1099");
		Object[] options = { "Pseudo:", pseudo, "Adresse IP:", ip, "Port:",
				port };

		int option = JOptionPane.showConfirmDialog(null, options, "Login",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			this.ipServer = ip.getText();
			this.port = Integer.parseInt(port.getText());
			this.uName = pseudo.getText();
		} else {
			System.out.println("Login canceled");
			System.exit(0);
		}

		windows = new PrototypeFrame();

		windows.frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
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

		windows.btnSendText.addActionListener(new ActionListener() {
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

		windows.btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				windows.updateFiles();
				windows.frame.repaint();
			}
		});

		windows.btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					server.send(windows.fileList.getSelectedValue().toString(),
							downloadFile(windows.fileList.getSelectedValue()
									.toString()));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
