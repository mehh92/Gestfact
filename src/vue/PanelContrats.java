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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Contrat;
import controleur.Tableau;
import modele.Modele;

public class PanelContrats extends PanelDeBase implements ActionListener, KeyListener
{
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	
	/*private JTextField txtIdclient = new JTextField();*/
	private JComboBox<String> txtIdclient = new JComboBox<String>();
	private JTextField txtDate_souscription = new JTextField();
	private JComboBox<String> txtEtat_du_contrat = new JComboBox<String>();
	//private JTextField txtEtat_du_contrat = new JTextField();
	private JTextField txtObjet_du_contrat = new JTextField();
	private JTextField txtMontant_mensuel_ht = new JTextField();
	
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ;
	private Tableau unTableau ; 
	
	private JPanel panelRechercher = new JPanel(); 
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	public PanelContrats ()
	{
		super (new Color (186, 223, 255));
		//construction du Panel Form 
		this.panelForm.setLayout(new GridLayout(6,2,2,2));
		this.panelForm.setBounds(20, 40, 250, 340);
		this.panelForm.setBackground(new Color (186, 223, 255));
		this.panelForm.add(new JLabel("Id Client : "));
		this.panelForm.add(this.txtIdclient); 
		this.panelForm.add(new JLabel("Date de souscription : "));
		this.panelForm.add(this.txtDate_souscription);
		this.panelForm.add(new JLabel("Etat du contrat : "));
		this.panelForm.add(this.txtEtat_du_contrat);
		this.panelForm.add(new JLabel("Objet du contrat : "));
		this.panelForm.add(this.txtObjet_du_contrat);
		this.panelForm.add(new JLabel("Montant mensuel HT : "));
		this.panelForm.add(this.txtMontant_mensuel_ht);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		//remplir les combo 
		for (Client unClient : Modele.selectAllClients(""))
		{
			this.txtIdclient.addItem(unClient.getIdclient()+" - "+unClient.getNom());
		}
		
		this.txtEtat_du_contrat.addItem("Valide");
		this.txtEtat_du_contrat.addItem("Resilier");
		this.txtEtat_du_contrat.addItem("En cours");
		
		
		//construction du panel Rechercher 
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		this.panelRechercher.setBackground(new Color (186, 223, 255));
		this.panelRechercher.add(new JLabel("Filtrer les contrats : "));
		this.panelRechercher.add(this.txtMot); 
		this.panelRechercher.add(this.btRechercher); 
		this.add(this.panelRechercher); 
		
		//rendre les boutons cliquables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtMot.addKeyListener(this);
		this.txtIdclient.addKeyListener(this);
		this.txtDate_souscription.addKeyListener(this);
		this.txtEtat_du_contrat.addKeyListener(this);
		this.txtObjet_du_contrat.addKeyListener(this);
		this.txtMontant_mensuel_ht.addKeyListener(this);
		
		//construction de  la table 
		String entetes [] = {"N� contrat", "Id client", "Date de souscription", "Etat", "Objet", "Montant"};
		
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
					int retour = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer le contrat ?", 
							"Suppression Contrat", JOptionPane.YES_NO_OPTION); 
					if(retour == 0) {
						int num_contrat = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString()); 
						//on supprime le client dans la base 
						Modele.deleteContrat(num_contrat);
						//on le supprime de l'affichage 
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				}else if (nbClic==1)
				{
					Client unClient = Modele.selectWhereClient(Integer.parseInt(unTableau.getValueAt(numLigne,1).toString()));
					String nom = unClient.getNom();
					txtIdclient.getModel().setSelectedItem((unTableau.getValueAt(numLigne,1).toString())+" - "+nom);
					txtDate_souscription.setText(unTableau.getValueAt(numLigne,2).toString());
					txtEtat_du_contrat.getModel().setSelectedItem(unTableau.getValueAt(numLigne,3).toString());
					txtObjet_du_contrat.setText(unTableau.getValueAt(numLigne,4).toString());
					txtMontant_mensuel_ht.setText(unTableau.getValueAt(numLigne,5).toString());
					btEnregistrer.setText("Modifier");
				}
			}
		});
		
	}

	public Object [][] getDonnees (String mot)
	{
		ArrayList<Contrat> lesContrats = Modele.selectAllContrats(mot);
		Object [][] matrice = new Object [lesContrats.size()][6]; 
		int i=0; 
		for (Contrat unContrat : lesContrats)
		{
			matrice[i][0] = unContrat.getNum_contrat(); 
			matrice[i][1] = unContrat.getIdclient(); 
			matrice[i][2] = unContrat.getDate_souscription(); 
			matrice[i][3] = unContrat.getEtat_du_contrat(); 
			matrice[i][4] = unContrat.getObjet_du_contrat(); 
			matrice[i][5] = unContrat.getMontant_mensuel_ht();
			i++; 
		}
		return matrice ; 
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.viderChamps(); 
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
		//this.txtIdclient.setText("");
		this.txtDate_souscription.setText("");
		//this.txtEtat_du_contrat.setText("");
		this.txtObjet_du_contrat.setText("");
		this.txtMontant_mensuel_ht.setText("");
		
		this.btEnregistrer.setText("Enregistrer");
		
		this.txtIdclient.setBackground(Color.white);
		this.txtDate_souscription.setBackground(Color.white);
		this.txtEtat_du_contrat.setBackground(Color.white);
		this.txtObjet_du_contrat.setBackground(Color.white);
		this.txtMontant_mensuel_ht.setBackground(Color.white);
	}
	public void traitement (int choix)
	{
		String date_souscription = this.txtDate_souscription.getText();
		String etat_du_contrat = this.txtEtat_du_contrat.getSelectedItem().toString();
		String objet_du_contrat = this.txtObjet_du_contrat.getText();
		float montant_mensuel_ht = 0;
		try {
		 montant_mensuel_ht = Float.parseFloat(this.txtMontant_mensuel_ht.getText());
		 this.txtMontant_mensuel_ht.setBackground(Color.white);
		}
		catch(NumberFormatException exp) {
			this.txtMontant_mensuel_ht.setBackground(Color.red);
			JOptionPane.showMessageDialog(this, "Veuillez un montant valide >0");
			
		}
		String chaine = this.txtIdclient.getSelectedItem().toString();
		String tab [] = chaine.split("-");
		int idclient = Integer.parseInt(tab[0].trim());
		
		
		
		
		if (date_souscription.equals("")) {
			this.txtDate_souscription.setBackground(Color.red);
		}else {
			this.txtDate_souscription.setBackground(Color.white);
		}
		
		if (objet_du_contrat.equals("")){
			this.txtObjet_du_contrat.setBackground(Color.red);
		}else {
			this.txtObjet_du_contrat.setBackground(Color.white);
		}
		
		if (date_souscription.equals("") 
				|| objet_du_contrat.equals("") || montant_mensuel_ht==0){
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
			//this.viderChamps();
		}
		else { if (choix == 0) {
						//instancier la classe Client 
						Contrat unContrat = new Contrat(0, idclient, date_souscription, 
								etat_du_contrat, objet_du_contrat, montant_mensuel_ht); 
						
						//Insertion dans la BDD 
						Modele.insertContrat(unContrat);
						
						JOptionPane.showMessageDialog(this, "Insertion r�ussie dans la base");
						
						//on récupère le client inséré pour son nouveau ID 
						unContrat = Modele.selectWhereContrat(date_souscription, objet_du_contrat); 
						
						Object ligne[] ={unContrat.getNum_contrat(), unContrat.getIdclient(), unContrat.getDate_souscription(), 
								unContrat.getEtat_du_contrat(), unContrat.getObjet_du_contrat(), unContrat.getMontant_mensuel_ht()}; 
						
						this.unTableau.ajouterLigne(ligne);
		}else {
			int numLigne = this.uneTable.getSelectedRow(); 
			int num_contrat = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			Contrat unContrat = new Contrat (num_contrat, idclient, date_souscription, etat_du_contrat, objet_du_contrat, montant_mensuel_ht);
			//update dans la base de données 
			Modele.updateContrat(unContrat);
			//update dans le tableau d'affichage 
			Object ligne[] ={unContrat.getNum_contrat(), unContrat.getIdclient(), unContrat.getDate_souscription(), 
					unContrat.getEtat_du_contrat(), unContrat.getObjet_du_contrat(), unContrat.getMontant_mensuel_ht()}; 
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


















