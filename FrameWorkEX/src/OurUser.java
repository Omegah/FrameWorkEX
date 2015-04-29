import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

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


public class OurUser extends User {

	MessageInterface m;
	TestWIndowBuilder windows;
	JFrame frame;
	private JTextField textField;
	public static JTextArea txtrJeanLouis;
	
	  public static void main(String[] args)
	    {
			//this.user = new OurUser("louis","152.77.82.207",this);	
		  		//TestWIndowBuilder window = new TestWIndowBuilder();
		  		//window.frame.setVisible(true);
	    }
	  
	  
	public OurUser(String uName, String ipServer) throws RemoteException {
		super(uName, ipServer);
		txtrJeanLouis = new JTextArea();

		initialize();
		frame.setVisible(true);
		System.out.println(txtrJeanLouis.getText());

		// TODO Auto-generated constructor stub				
	}

	@Override
	public void execute(String name, Object obj) {
		// TODO Auto-generated method stub
		
		try {
			m =((MessageInterface)obj);
			System.out.println(m.getMessageHeure());
			System.out.println(txtrJeanLouis.getText());
			txtrJeanLouis.append(m.getMessageHeure() + "\n");
			frame.repaint();
		} catch (RemoteException e) {
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

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	
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
		
		txtrJeanLouis = new JTextArea();
		txtrJeanLouis.setText("");
		txtrJeanLouis.setEditable(false);
		txtrJeanLouis.setBounds(25, 295, 548, 135);
		JScrollPane sp = new JScrollPane(txtrJeanLouis);   // JTextArea is placed in a JScrollPane.
		sp.setBounds(10,260,50,50);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(sp);
		frame.getContentPane().add(txtrJeanLouis);
		
		JButton btnEnvoyer = new JButton("Envoyer");
		btnEnvoyer.setBackground(new Color(51, 153, 255));
		btnEnvoyer.setBounds(456, 438, 117, 25);
		btnEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Message m;
				try {
					m = new Message(uName,textField.getText());
					start(uName,m);
					textField.setText("");
					
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
		
		textField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent a){
		
        			Message m;
        			try {
        				m = new Message(uName,textField.getText());
        				start(uName,m);
    					textField.setText("");

        				
        			} catch (RemoteException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		
            }});
		
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
