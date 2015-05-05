package appliTemoin;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.AbstractListModel;

public class PrototypeFrame {
	PrototypeFrame windows;
	JFrame frame;
	protected JTextField textField;
	private ArrayList<String> activeUsers;
	private ArrayList<String> files;
	public static JTextArea textArea;
	protected JButton btnSendText;
	protected JButton btnRefresh;
	protected JButton btnSendFile;
	Hashtable<String, Object> table;
	private JList<String> userList;
	protected JList<String> fileList;

	public PrototypeFrame() {
		initialize();
	}

	/**
	 * Initialisation du contenu de la fenêtre
	 */
	private void initialize() {
		// Caractéristiques de la fenêtre
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// Affichage des messages
		textArea = new JTextArea();
		textArea.setText("");
		textArea.setEditable(false);
		JScrollPane scrollableTextArea = new JScrollPane(textArea);
		scrollableTextArea.setBounds(25, 295, 548, 135);
		frame.getContentPane().add(scrollableTextArea);
		
		// Bouton Envoyer
		btnSendText = new JButton("Envoyer");
		btnSendText.setBackground(new Color(51, 153, 255));
		btnSendText.setBounds(456, 438, 117, 25);
		frame.getContentPane().add(btnSendText);
		
		// Zone de texte editable pour saisie messages
		textField = new JTextField();
		textField.setBounds(25, 442, 419, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		// Liste des utilisateurs
		userList = new JList<String>();
		JScrollPane scrollableUserList = new JScrollPane(userList);
		scrollableUserList.setBounds(25, 45, 167, 206);
		frame.getContentPane().add(scrollableUserList);
		JLabel lblUtilisateursConnects = new JLabel("Utilisateurs Connectés");
		lblUtilisateursConnects.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUtilisateursConnects.setBounds(24, 15, 191, 19);
		frame.getContentPane().add(lblUtilisateursConnects);
		
		
		

		
		// Liste des fichiers
		files = new ArrayList<String>();
		fileList = new JList<String>();
		JScrollPane scrollableFileList = new JScrollPane(fileList);
		scrollableFileList.setBounds(237, 45, 333, 154);
		frame.getContentPane().add(scrollableFileList);
		updateFiles();
		JLabel lblMesFichiers = new JLabel("Mes fichiers");
		lblMesFichiers.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesFichiers.setBounds(341, 12, 123, 25);
		frame.getContentPane().add(lblMesFichiers);
		// Boutons fichiers
		btnSendFile = new JButton("Envoyer");
		btnSendFile.setBackground(new Color(0, 153, 255));
		btnSendFile.setBounds(418, 211, 147, 40);
		frame.getContentPane().add(btnSendFile);
		btnRefresh = new JButton("Actualiser");
		btnRefresh.setBackground(new Color(0, 153, 255));
		btnRefresh.setBounds(247, 211, 147, 40);
		frame.getContentPane().add(btnRefresh);
		

		frame.setVisible(true);
	}
	
	/**
	 * mets a jours la liste des utilisateurs 
	 */

	public void updateUsers(ArrayList<String> a) {
		activeUsers = a;
		userList.removeAll();
		userList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return activeUsers.size();
			}

			public String getElementAt(int index) {
				return activeUsers.get(index);
			}
		});
		frame.repaint();
	}

	/**
	 * mets a jour la liste des fichiers 
	 */
	
	public void updateFiles() {
		File directory = new File("files/");
		File[] tabFiles = directory.listFiles();
		files.clear();
		for (int i = 0; i < tabFiles.length; i++) {
			files.add(tabFiles[i].getName());
		}
		fileList.setModel(new AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;

			public int getSize() {
				return files.size();
			}

			public String getElementAt(int index) {
				return files.get(index);
			}
		});
	}
}