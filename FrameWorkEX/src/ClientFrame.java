import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;


public class ClientFrame extends JFrame{
    protected JList zoneUtilisateurs;
    protected Vector utilisateurs;
    
	public ClientFrame() {
		// TODO Auto-generated constructor stub
        super("");

        JPanel panneau = new JPanel();
        panneau.setLayout(new BorderLayout(10, 10));
        zoneUtilisateurs = new JList();
        zoneUtilisateurs.setPreferredSize(new Dimension(150, 300));
        DefaultListModel lm1  = new DefaultListModel();
        lm1.addElement("toast");
        // getActiveUsers() ??
        zoneUtilisateurs.setModel(lm1);
        
        panneau.add(zoneUtilisateurs, BorderLayout.EAST);
        utilisateurs = new Vector();    
        Container conteneur = getContentPane();
        conteneur.add(panneau, BorderLayout.CENTER);
        
        this.setSize(200, 125);
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
        this.setResizable(false);
        
	}

    public static void main(String[] args)
    {
        ClientFrame gui = new ClientFrame();
        gui.pack();
        gui.setVisible(true);
    }
	
}
