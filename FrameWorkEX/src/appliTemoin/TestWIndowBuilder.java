package appliTemoin;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.AbstractListModel;
import server.ServerSync;

public class TestWIndowBuilder {
	TestWIndowBuilder windows;
	JFrame frame;
	protected JTextField textField;
	private ArrayList<String> values;
	private ArrayList<String> files;
	public static JTextArea txtarea;
	protected JButton btnEnvoyer;
	protected JButton btnActu;
	protected JButton btnNewButton;
	Hashtable<String, Object> table;
	private JList list;
	protected JList list_1;

	/**
	 * Create the application.
	 */
	public TestWIndowBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		files = new ArrayList<String>();
		list = new JList();
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list.setBounds(25, 45, 167, 206);
		frame.getContentPane().add(list);
		txtarea = new JTextArea();
		txtarea.setText("");
		txtarea.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtarea);
		scroll.setBounds(25, 295, 548, 135);
		frame.getContentPane().add(scroll);
		btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setBackground(new Color(51, 153, 255));
		btnEnvoyer.setBounds(456, 438, 117, 25);
		frame.getContentPane().add(btnEnvoyer);
		textField = new JTextField();
		textField.setBounds(25, 442, 419, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JLabel lblUtilisateursConnects = new JLabel("Utilisateurs Connectés");
		lblUtilisateursConnects.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUtilisateursConnects.setBounds(24, 15, 191, 19);
		frame.getContentPane().add(lblUtilisateursConnects);
		list_1 = new JList();
		list_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list_1.setBounds(237, 45, 333, 154);
		majFiles();
		frame.getContentPane().add(list_1);
		btnNewButton = new JButton("Envoyer");
		btnNewButton.setBackground(new Color(0, 153, 255));
		btnNewButton.setBounds(240, 211, 147, 40);
		frame.getContentPane().add(btnNewButton);
		btnActu = new JButton("Actualiser");
		btnActu.setBackground(new Color(0, 153, 255));
		btnActu.setBounds(420, 211, 147, 40);
		frame.getContentPane().add(btnActu);
		JLabel lblMesFichiers = new JLabel("Mes fichiers");
		lblMesFichiers.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesFichiers.setBounds(341, 12, 123, 25);
		frame.getContentPane().add(lblMesFichiers);
		frame.setVisible(true);
	}

	public void majUsers(ArrayList<String> a) {
		values = a;
		list.removeAll();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.size();
			}

			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		frame.repaint();
	}

	public void majFiles() {
		File directory = new File("files/");
		File[] tabFiles = directory.listFiles();
		files.clear();
		for (int i = 0; i < tabFiles.length; i++) {
			files.add(tabFiles[i].getName());
		}
		list_1.setModel(new AbstractListModel() {
			public int getSize() {
				return files.size();
			}

			public Object getElementAt(int index) {
				return files.get(index);
			}
		});
	}
}