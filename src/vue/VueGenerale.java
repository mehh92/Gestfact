package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Gestfact;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener
{
	private JPanel panelMenu = new JPanel();
	private JPanel panelProfil = new JPanel(); 
	
	private JButton btProfil = new JButton("Profil");
	private JButton btClients = new JButton("Clients");
	private JButton btContrats = new JButton("Contrats");
	private JButton btPrestations = new JButton("Prestations");
	private JButton btFactures = new JButton("Factures");
	private JButton btBord = new JButton("T-Bord");
	private JButton btQuitter = new JButton("Quitter");
	
	private JLabel logo = new JLabel();
	
	private JLabel msgBienvenu = new JLabel();
	
	Font unePolice = new Font("Arial", Font.ITALIC, 16);
	Font unePolice2 = new Font("Arial", Font.PLAIN, 15);
	
	//instanciation des Panels 
	private PanelClients unPanelClients = new PanelClients();
	private PanelContrats unPanelContrats = new PanelContrats();
	private PanelPrestations unPanelPrestations = new PanelPrestations();
	private PanelFactures unPanelFactures = new PanelFactures();
	private PanelBord unPanelBord  = new PanelBord();
	
	
	public  VueGenerale(User unUser) 
	{
		 this.setTitle("Gestfact");
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setBounds(200, 100, 900, 500);
		 this.setLayout(null);
		 this.getContentPane().setBackground(new Color (13,25,80));
		 this.setResizable(false);
		 
		 ImageIcon uneImage = new ImageIcon("src/images/3.png");
		 this.logo = new JLabel(uneImage);
		 this.logo.setBounds(290, 130, 300, 250);
		 
		 this.msgBienvenu = new JLabel("Bienvenu sur l'application Gestfact de la société SOG");
		 this.msgBienvenu.setFont(unePolice);
		 this.msgBienvenu.setBounds(253, 80, 400, 15);
		 
		 //construction du panelMenu 
		 this.panelMenu.setLayout(new GridLayout(1, 5, 2, 2));
		 this.panelMenu.setBounds(45, 20, 800, 30);
		 this.panelMenu.setBackground(new Color (13,25,80));
		 
		 this.panelMenu.add(this.btProfil).setFont(unePolice2);
		 this.panelMenu.add(this.btClients).setFont(unePolice2);
		 this.panelMenu.add(this.btContrats).setFont(unePolice2);
		 this.panelMenu.add(this.btPrestations).setFont(unePolice2);
		 this.panelMenu.add(this.btFactures).setFont(unePolice2);
		 this.panelMenu.add(this.btBord).setFont(unePolice2);
		 this.panelMenu.add(this.btQuitter).setFont(unePolice2);
		 
		 this.add(this.panelMenu);
		 this.add(msgBienvenu).setForeground(Color.white); ;
		 this.add(logo);
		 
		 
		 //construction du panel Profil 
		 this.panelProfil.setLayout(new GridLayout(4, 1));
		 this.panelProfil.setBounds(45, 70, 800, 380);
		 this.panelProfil.setBackground(new Color (186, 223, 255));
		 this.panelProfil.add(new JLabel("Nom user     : " + unUser.getNom()));
		 this.panelProfil.add(new JLabel("Prénom user  : " + unUser.getPrenom()));
		 this.panelProfil.add(new JLabel("Email  user  : " + unUser.getEmail()));
		 this.add(this.panelProfil); 
		 this.panelProfil.setVisible(false);
		 
		 //rendre les boutons ecoutables 
		 this.btQuitter.addActionListener(this);
		 this.btProfil.addActionListener(this);
		 this.btBord.addActionListener(this);
		 this.btClients.addActionListener(this);
		 this.btContrats.addActionListener(this);
		 this.btPrestations.addActionListener(this);
		 this.btFactures.addActionListener(this);
		 
		 //ajout des panels dans la fenetre 
		 this.add(this.unPanelClients); 
		 this.add(this.unPanelContrats); 
		 this.add(this.unPanelPrestations);
		 this.add(this.unPanelFactures);
		 this.add(this.unPanelBord); 
		 
		 this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	 
		if( e.getSource() == this.btQuitter)
		{
			int retour = JOptionPane.showConfirmDialog(this, 
					"Quitter Application","Voulez-vous quitter l'application ?",
					JOptionPane.YES_NO_OPTION); 
			if (retour == 0)
			{
				this.dispose(); //fermeture de la fenetre
				Gestfact.rendreVisibleVueConnexion(true);
			}
		}
		else if(e.getSource() == this.btProfil)
		{
			this.logo.setVisible(false);
			this.msgBienvenu.setVisible(false);
			this.unPanelPrestations.setVisible(false);
			this.unPanelFactures.setVisible(false);
			this.unPanelContrats.setVisible(false);
			this.unPanelContrats.setVisible(false);
			this.unPanelClients.setVisible(false);
			this.unPanelBord.setVisible(false);
			this.panelProfil.setVisible(true);
		}
		else if (e.getSource() == this.btClients)
		{
			this.logo.setVisible(false);
			this.msgBienvenu.setVisible(false);
			this.unPanelPrestations.setVisible(false);
			this.unPanelFactures.setVisible(false);
			this.unPanelContrats.setVisible(false);
			this.unPanelClients.setVisible(true);
			this.unPanelBord.setVisible(false);
			this.panelProfil.setVisible(false);
		}
		else if (e.getSource() == this.btContrats)
		{
			this.logo.setVisible(false);
			this.msgBienvenu.setVisible(false);
			this.unPanelPrestations.setVisible(false);
			this.unPanelFactures.setVisible(false);
			this.unPanelContrats.setVisible(true);
			this.unPanelClients.setVisible(false);
			this.unPanelBord.setVisible(false);
			this.panelProfil.setVisible(false);
		}
		
		else if (e.getSource() == this.btPrestations)
		{
			this.logo.setVisible(false);
			this.msgBienvenu.setVisible(false);
			this.unPanelPrestations.setVisible(true);
			this.unPanelFactures.setVisible(false);
			this.unPanelContrats.setVisible(false);
			this.unPanelClients.setVisible(false);
			this.unPanelBord.setVisible(false);
			this.panelProfil.setVisible(false);
		}
		
		else if (e.getSource() == this.btFactures)
		{
			this.logo.setVisible(false);
			this.msgBienvenu.setVisible(false);
			this.unPanelPrestations.setVisible(false);
			this.unPanelFactures.setVisible(true);
			this.unPanelContrats.setVisible(false);
			this.unPanelClients.setVisible(false);
			this.unPanelBord.setVisible(false);
			this.panelProfil.setVisible(false);
		}

		else if (e.getSource() == this.btBord)
		{
			this.logo.setVisible(false);
			this.msgBienvenu.setVisible(false);
			this.unPanelPrestations.setVisible(false);
			this.unPanelFactures.setVisible(false);
			this.unPanelContrats.setVisible(false);
			this.unPanelClients.setVisible(false);
			this.unPanelBord.actualiser();
			this.unPanelBord.setVisible(true);
			this.panelProfil.setVisible(false);
			
		}
	
		
	}
}

















