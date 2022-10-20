package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Tableau;
import modele.Modele;

public class PanelClients extends PanelDeBase implements ActionListener, KeyListener
{
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	
	private JTextField txtNom = new JTextField();
	private JTextField txtSiret = new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCP = new JTextField();
	private JTextField txtVille = new JTextField();
	
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ;
	private Tableau unTableau ; 
	
	private JPanel panelRechercher = new JPanel(); 
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	public PanelClients ()
	{
		super (new Color (186, 223, 255));
		//construction du Panel Form 
		this.panelForm.setLayout(new GridLayout(8,2,2,2));
		this.panelForm.setBounds(20, 40, 250, 340);
		this.panelForm.setBackground(new Color (186, 223, 255));
		this.panelForm.add(new JLabel("Nom Client : "));
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Siret Client : "));
		this.panelForm.add(this.txtSiret);
		this.panelForm.add(new JLabel("Tel : "));
		this.panelForm.add(this.txtTel);
		this.panelForm.add(new JLabel("Email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Adresse : "));
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("CP : "));
		this.panelForm.add(this.txtCP);
		this.panelForm.add(new JLabel("Ville : "));
		this.panelForm.add(this.txtVille);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		//construction du panel Rechercher 
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		this.panelRechercher.setBackground(new Color (186, 223, 255));
		this.panelRechercher.add(new JLabel("Filtrer les clients : "));
		this.panelRechercher.add(this.txtMot); 
		this.panelRechercher.add(this.btRechercher); 
		this.add(this.panelRechercher); 
		
		//rendre les boutons cliquables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtMot.addKeyListener(this);
		this.txtTel.addKeyListener(this);
		this.txtNom.addKeyListener(this);
		this.txtSiret.addKeyListener(this);
		this.txtEmail.addKeyListener(this);
		this.txtAdresse.addKeyListener(this);
		this.txtCP.addKeyListener(this);
		this.txtVille.addKeyListener(this);
		
		//construction de  la table 
		String entetes [] = {"Id Client", "Nom", "Siret", "Tel", "Email", "Adresse", "CP", "Ville"};
		
		Object donnees [][] = this.getDonnees ("") ; //select tous les clients  
		this.unTableau = new Tableau (entetes, donnees); 
		
		this.uneTable = new JTable(unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(290, 80, 500, 280);
		this.add(this.uneScroll);
		
		//gestion de la suppression / modification 
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {	
			}
			
			@Override
			public void mousePressed(MouseEvent e) {				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {	
				int nbClic = e.getClickCount(); 
				int numLigne = uneTable.getSelectedRow(); 
				if( nbClic == 2 )
				{
					int retour = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer le client ?", 
							"Suppression Client", JOptionPane.YES_NO_OPTION); 
					if(retour ==0) {
						int idclient = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString()); 
						//on supprime le client dans la base 
						Modele.deleteClient(idclient);
						//on le supprime de l'affichage 
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				}else if (nbClic==1)
				{
					txtNom.setText(unTableau.getValueAt(numLigne,1).toString());
					txtSiret.setText(unTableau.getValueAt(numLigne,2).toString());
					txtTel.setText(unTableau.getValueAt(numLigne,3).toString());
					txtEmail.setText(unTableau.getValueAt(numLigne,4).toString());
					txtAdresse.setText(unTableau.getValueAt(numLigne,5).toString());
					txtCP.setText(unTableau.getValueAt(numLigne,6).toString());
					txtVille.setText(unTableau.getValueAt(numLigne,7).toString());
					btEnregistrer.setText("Modifier");
				}
			}
		});
		
	}

	public Object [][] getDonnees (String mot)
	{
		ArrayList<Client> lesClients = Modele.selectAllClients(mot); 
		Object [][] matrice = new Object [lesClients.size()][8]; 
		int i=0; 
		for (Client unClient : lesClients)
		{
			matrice[i][0] = unClient.getIdclient(); 
			matrice[i][1] = unClient.getNom(); 
			matrice[i][2] = unClient.getSiret(); 
			matrice[i][3] = unClient.getTel(); 
			matrice[i][4] = unClient.getEmail(); 
			matrice[i][5] = unClient.getAdresse();
			matrice[i][6] = unClient.getCP(); 
			matrice[i][7] = unClient.getVille();
			i++; 
		}
		return matrice ; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.viderChamps (); 
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Enregistrer"))
		{
			this.traitement (0); 
		}
		else if (e.getSource() == this.btEnregistrer && e.getActionCommand().equals("Modifier"))
		{
			this.traitement (1); 
		}
		else if (e.getSource() == this.btRechercher)
		{
			String mot = this.txtMot.getText(); 
			//mise à jour de l'affichage 
			this.unTableau.setDonnees (this.getDonnees(mot)); 
		}
	}
	public void viderChamps()
	{
		this.txtNom.setText("");
		this.txtSiret.setText("");
		this.txtTel.setText("");
		this.txtEmail.setText("");
		this.txtAdresse.setText("");
		this.txtCP.setText("");
		this.txtVille.setText("");
		this.btEnregistrer.setText("Enregistrer");
		this.txtNom.setBackground(Color.white);
		this.txtSiret.setBackground(Color.white);
		this.txtEmail.setBackground(Color.white);
	}
	public void traitement (int choix)
	{
		String nom = this.txtNom.getText(); 
		String siret = this.txtSiret.getText(); 
		String tel = this.txtTel.getText(); 
		String email = this.txtEmail.getText(); 
		String adresse = this.txtAdresse.getText();
		String CP = this.txtCP.getText();
		String Ville = this.txtVille.getText();
		
		if (nom.equals(""))  {
			this.txtNom.setBackground(Color.red);
		}else {
			this.txtNom.setBackground(Color.white);
		}
		if (siret.equals("")) {
			this.txtSiret.setBackground(Color.red);
		}else {
			this.txtSiret.setBackground(Color.white);
		}
		if (email.equals("")){
			this.txtEmail.setBackground(Color.red);
		}else {
			this.txtEmail.setBackground(Color.white);
		}
		if (nom.equals("") || siret.equals("") || email.equals("")){
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
			//this.viderChamps();
		}
		else { if (choix == 0) {
						//instancier la classe Client 
						Client unClient = new Client(0, nom, siret, tel, email, adresse, CP, Ville); 
						
						//Insertion dans la BDD 
						Modele.insertClient(unClient);
						
						JOptionPane.showMessageDialog(this, "Insertion r�ussie dans la base");
						
						//on récupère le client inséré pour son nouveau ID 
						unClient = Modele.selectWhereClient(email,tel); 
						
						Object ligne[] ={unClient.getIdclient(), unClient.getNom(), unClient.getSiret(), 
								unClient.getTel(), unClient.getEmail(), unClient.getAdresse(), unClient.getCP(), unClient.getVille() }; 
						
						this.unTableau.ajouterLigne(ligne);
		}else {
			int numLigne = this.uneTable.getSelectedRow(); 
			int idclient = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			Client unClient = new Client(idclient, nom, siret, tel, email, adresse, CP, Ville);
			//update dans la base de données 
			Modele.updateClient(unClient);
			//update dans le tableau d'affichage 
			Object ligne[] ={unClient.getIdclient(), unClient.getNom(), unClient.getSiret(), 
					unClient.getTel(), unClient.getEmail(), unClient.getAdresse(), unClient.getCP(), unClient.getVille() }; 
			this.unTableau.modifierLigne (numLigne, ligne); 
		}
		
		//vider les champs 
		this.viderChamps();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			String mot = this.txtMot.getText(); 
			//mise à jour de l'affichage 
			this.unTableau.setDonnees (this.getDonnees(mot));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}


















