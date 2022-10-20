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
import controleur.Facture;
import controleur.Prestation;
import controleur.Tableau;
import controleur.Vfacture;
import modele.Modele;

public class PanelFactures extends PanelDeBase implements ActionListener, KeyListener
{
	private JPanel panelForm = new JPanel(); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	
	private JComboBox<String> txtNum_prestation = new JComboBox<String>();
	private JTextField txtDate_facture = new JTextField();
	private JTextField txtMontant = new JTextField();
	private JComboBox<String> txtEtat = new JComboBox<String>();
	

	
	
	private JTable uneTable ; 
	private JScrollPane uneScroll ;
	private Tableau unTableau ; 
	
	private JPanel panelRechercher = new JPanel(); 
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	public PanelFactures ()
	{
		super (new Color (186, 223, 255));
		//construction du Panel Form 
		this.panelForm.setLayout(new GridLayout(5,2,2,2));
		this.panelForm.setBounds(20, 40, 250, 340);
		this.panelForm.setBackground(new Color (186, 223, 255));
		this.panelForm.add(new JLabel("Prestation : "));
		this.panelForm.add(this.txtNum_prestation); 
		this.panelForm.add(new JLabel("Date facture : "));
		this.panelForm.add(this.txtDate_facture);
		this.panelForm.add(new JLabel("Montant : "));
		this.panelForm.add(this.txtMontant);
		this.panelForm.add(new JLabel("Etat : "));
		this.panelForm.add(this.txtEtat);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		//remplir les combo 
		for (Prestation unePrestation : Modele.selectAllPrestations(""))
		{
			this.txtNum_prestation.addItem(unePrestation.getNum_prestation()+" - "+unePrestation.getNom_prestation());
		}
		
		this.txtEtat.addItem("Payer");
		this.txtEtat.addItem("Non payer");
		
		
		//construction du panel Rechercher 
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		this.panelRechercher.setBackground(new Color (186, 223, 255));
		this.panelRechercher.add(new JLabel("Filtrer les Factures : "));
		this.panelRechercher.add(this.txtMot); 
		this.panelRechercher.add(this.btRechercher); 
		this.add(this.panelRechercher); 
		
		//rendre les boutons cliquables et ecoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btRechercher.addActionListener(this);
		this.txtMot.addKeyListener(this);
		this.txtNum_prestation.addKeyListener(this);
		this.txtDate_facture.addKeyListener(this);
		this.txtMontant.addKeyListener(this);
		this.txtEtat.addKeyListener(this);
		
		//construction de  la table 
		String entetes [] = {"N∞ facture", "N∞ contrat", "Nom client", "N∞ prestation", "Nom prestation", "Date facture", "Montant", "Etat"};
		//{"N∞ facture", "N∞ prestation", "Date facture", "Montant", "Etat"}
		
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
					int retour = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer la facture ?", 
							"Suppression de la Facture", JOptionPane.YES_NO_OPTION); 
					if(retour == 0) {
						int num_facture = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString()); 
						//on supprime le client dans la base 
						Modele.deleteFacture(num_facture);
						//on le supprime de l'affichage 
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				}else if (nbClic==1)
				{
					txtNum_prestation.getModel().setSelectedItem(unTableau.getValueAt(numLigne,3).toString()+" - "+ unTableau.getValueAt(numLigne, 4).toString());
					txtDate_facture.setText(unTableau.getValueAt(numLigne,5).toString());
					txtMontant.setText(unTableau.getValueAt(numLigne,6).toString());
					txtEtat.getModel().setSelectedItem(unTableau.getValueAt(numLigne,7).toString());
					
					btEnregistrer.setText("Modifier");
					
				}
			}
		});
		
	}

	public Object [][] getDonnees (String mot)
	{
		ArrayList<Vfacture> lesVFactures = Modele.selectAllVfacture(mot); 
		Object [][] matrice = new Object [lesVFactures.size()][8]; 
		int i=0; 
		for (Vfacture uneVFacture : lesVFactures)
		{
			matrice[i][0] = uneVFacture.getNum_facture(); 
			matrice[i][1] = uneVFacture.getNum_contrat(); 
			matrice[i][2] = uneVFacture.getNom_client(); 
			matrice[i][3] = uneVFacture.getNum_prestation(); 
			matrice[i][4] = uneVFacture.getNom_prestation();
			matrice[i][5] = uneVFacture.getDate_facture(); 
			matrice[i][6] = uneVFacture.getMontant();
			matrice[i][7] = uneVFacture.getEtat(); 
			
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
			//mise √† jour de l'affichage 
			this.unTableau.setDonnees (this.getDonnees(mot)); 
		}
	}
	public void viderChamps()
	{
		//this.txtIdclient.setText("");
		this.txtDate_facture.setText("");
		//this.txtEtat_du_contrat.setText("");
		this.txtMontant.setText("");
		
		this.btEnregistrer.setText("Enregistrer");
		
		this.txtNum_prestation.setBackground(Color.white);
		this.txtDate_facture.setBackground(Color.white);
		this.txtMontant.setBackground(Color.white);
		this.txtEtat.setBackground(Color.white);

	}
	public void traitement (int choix)
	{
		String chaine = this.txtNum_prestation.getSelectedItem().toString();
		String tab [] = chaine.split("-");
		int num_prestation = Integer.parseInt(tab[0].trim());
		
		String date_facture = this.txtDate_facture.getText();
		
		float montant = 0;
		try {
		 montant = Float.parseFloat(this.txtMontant.getText());
		 this.txtMontant.setBackground(Color.white);
		}
		catch(NumberFormatException exp) {
			this.txtMontant.setBackground(Color.red);
			JOptionPane.showMessageDialog(this, "Veuillez un montant valide >0");	
		}
		String etat = this.txtEtat.getSelectedItem().toString();
		
		
		
		
		if (date_facture.equals("")) {
			this.txtDate_facture.setBackground(Color.red);
		}else {
			this.txtDate_facture.setBackground(Color.white);
		}
		
		if (montant == 0){
			this.txtMontant.setBackground(Color.red);
		}else {
			this.txtMontant.setBackground(Color.white);
		}
		
		if (date_facture.equals("")  || montant==0){
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
			//this.viderChamps();
		}
		else { if (choix == 0) {
						//instancier la classe Client 
						Facture uneFacture = new Facture(0, num_prestation, date_facture, 
								montant, etat); 
						
						//Insertion dans la BDD 
						Modele.insertFacture(uneFacture);
						
						JOptionPane.showMessageDialog(this, "Insertion rÈussie dans la base");
						
						//on r√©cup√®re le client ins√©r√© pour son nouveau ID 
						Vfacture unVfacture = Modele.selectWhereVfacture(date_facture, etat);
						
						Object ligne[] ={unVfacture.getNum_facture(), unVfacture.getNum_contrat(), unVfacture.getNom_client(), 
								unVfacture.getNum_prestation(), unVfacture.getNom_prestation() , unVfacture.getDate_facture()
								, unVfacture.getMontant() , unVfacture.getEtat()}; 
						
						this.unTableau.ajouterLigne(ligne); 
		}else {
			int numLigne = this.uneTable.getSelectedRow(); 
			int num_facture = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			Facture uneFacture = new Facture (num_facture, num_prestation, date_facture, montant, etat);
			//update dans la base de donn√©es 
			Modele.updateFacture(uneFacture);
			//update dans le tableau d'affichage 
			Vfacture unVfacture = Modele.selectWhereVfacture(date_facture, etat);
			
			Object ligne[] ={unVfacture.getNum_facture(), unVfacture.getNum_contrat(), unVfacture.getNom_client(), 
					unVfacture.getNum_prestation(), unVfacture.getNom_prestation() , unVfacture.getDate_facture()
					, unVfacture.getMontant() , unVfacture.getEtat()}; 
			
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
			//mise √† jour de l'affichage 
			this.unTableau.setDonnees (this.getDonnees(mot));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
