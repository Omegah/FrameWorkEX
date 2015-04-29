import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.AbstractListModel;

import server.Server;


public class TestWIndowBuilder {

	JFrame frame;
	private JTextField textField;
	public JTextArea txtrJeanLouis;
	private OurUser user;
	private Server server;


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
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Jean Louis", "Bernard", "George"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list.setBounds(25, 45, 167, 206);
		frame.getContentPane().add(list);
		
		JTextArea txtrJeanLouis = new JTextArea();
		txtrJeanLouis.setText("Jean Louis[14:15] > Hello\nBernard[14:16]> Hello l'ami !");
		txtrJeanLouis.setEditable(false);
		txtrJeanLouis.setBounds(25, 295, 548, 135);
		frame.getContentPane().add(txtrJeanLouis);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setBackground(new Color(51, 153, 255));
		btnEnvoyer.setBounds(456, 438, 117, 25);
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Message m;
				try {
					m = new Message("louis",textField.getText());
					//user.start(m);
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnEnvoyer);
		
		textField = new JTextField();
		textField.setBounds(25, 442, 419, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUtilisateursConnects = new JLabel("Utilisateurs Connectés");
		lblUtilisateursConnects.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUtilisateursConnects.setBounds(24, 15, 191, 19);
		frame.getContentPane().add(lblUtilisateursConnects);
		
		JList list_1 = new JList();
		list_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		list_1.setBounds(237, 45, 333, 154);
		frame.getContentPane().add(list_1);
		
		JButton btnNewButton = new JButton("Envoyer");
		btnNewButton.setBackground(new Color(0, 153, 255));

		btnNewButton.setBounds(326, 211, 147, 40);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblMesFichiers = new JLabel("Mes fichiers");
		lblMesFichiers.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMesFichiers.setBounds(341, 12, 123, 25);
		frame.getContentPane().add(lblMesFichiers);
	}
}
