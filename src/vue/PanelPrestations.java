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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Contrat;
import controleur.Logiciel;
import controleur.Materiel;
import controleur.Prestation;
import controleur.Tableau;
import modele.Modele;

public class PanelPrestations extends PanelDeBase implements ActionListener, KeyListener
{

	private JPanel panelForm = new JPanel();
	private JPanel panelForm2 = new JPanel();
	private JPanel panelForm3 = new JPanel(); 
	private JPanel panelForm4 = new JPanel(); 
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	
	private JRadioButton RBlogiciel = new JRadioButton ("Logicielle");
	private JRadioButton RBmateriel = new JRadioButton ("Materielle");
	
	private JComboBox<Integer> txtnum_contrat = new JComboBox<Integer>();
	private JTextField txtnomPrestation = new JTextField();
	private JTextField txtprixPrestation = new JTextField();
	private JTextField txtnomLogiciel = new JTextField();
	private JTextField txtVersion = new JTextField();
	private JTextField txtnomMateriel = new JTextField();
	private JTextField txtcoutMateriel = new JTextField();

	private JTable uneTable ; 
	private JScrollPane uneScroll ;
	private Tableau unTableau ; 
	
	private JTable uneTable2 ; 
	private JScrollPane uneScroll2 ;
	private Tableau unTableau2 ; 
	
	private JPanel panelRechercher = new JPanel(); 
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");
	
	public PanelPrestations ()
	{
		
		super (new Color (186, 223, 255));
		//construction du Panel Form 
		
	    this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.setBounds(20, 40, 250, 140);
		this.panelForm.setBackground(new Color (186, 223, 255));
		this.panelForm.add(new JLabel(" Num Contrat : ")); 
		this.panelForm.add(this.txtnum_contrat);
		this.panelForm.add(new JLabel("Nom prestation : "));
		this.panelForm.add(this.txtnomPrestation);
		this.panelForm.add(new JLabel("Prix prestation : "));
		this.panelForm.add(this.txtprixPrestation);
		this.add(this.panelForm);
		
		this.panelForm4.setLayout(new GridLayout(1,2));
		this.panelForm4.setBounds(20, 330, 250, 30);
		this.panelForm4.setBackground(new Color (186, 223, 255));
		this.panelForm4.add(this.btAnnuler);
		this.panelForm4.add(this.btEnregistrer);
		this.add(this.panelForm4);
		
		this.RBlogiciel.setBounds(50, 200, 100, 30);
		this.RBlogiciel.setBackground(new Color (186, 223, 255));
		this.add(this.RBlogiciel);
		
		this.RBmateriel.setBounds(160, 200, 100, 30);
		this.RBmateriel.setBackground(new Color (186, 223, 255));
		this.add(this.RBmateriel);
		
		this.RBlogiciel.setSelected(true);
		this.RBmateriel.setSelected(false);
		
		this.panelForm2.setLayout(new GridLayout(2,2));
		this.panelForm2.setBounds(20, 240, 250, 80);
		this.panelForm2.setBackground(new Color (186, 223, 255));
		this.panelForm2.add(new JLabel("Nom logiciel : "));
		this.panelForm2.add(this.txtnomLogiciel);
		this.panelForm2.add(new JLabel("Version logiciel : "));
		this.panelForm2.add(this.txtVersion);
		this.add(this.panelForm2);
		
		
		this.panelForm3.setLayout(new GridLayout(2,2));
		this.panelForm3.setBounds(20, 240, 250, 80);
		this.panelForm3.setBackground(new Color (186, 223, 255));
		this.panelForm3.add(new JLabel("Nom materiel : "));
		this.panelForm3.add(this.txtnomMateriel);
		this.panelForm3.add(new JLabel("Coût materiel : "));
		this.panelForm3.add(this.txtcoutMateriel);
		this.add(this.panelForm3);
		
		this.panelForm3.setVisible(false);
		
		//remplir les combo 
		for (Contrat unContrat : Modele.selectAllContrats(""))
		{
			this.txtnum_contrat.addItem(unContrat.getNum_contrat());
		}
		
		//construction du panel Rechercher 
		this.panelRechercher.setLayout(new GridLayout(1,3));
		this.panelRechercher.setBounds(300, 40, 460, 20);
		this.panelRechercher.setBackground(new Color (186, 223, 255));
		this.panelRechercher.add(new JLabel("Filtrer les prestations : "));
		this.panelRechercher.add(this.txtMot); 
		this.panelRechercher.add(this.btRechercher); 
		this.add(this.panelRechercher); 
		
		//rendre les boutons cliquables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.RBlogiciel.addActionListener(this);
		this.RBmateriel.addActionListener(this);
		
		
		this.btRechercher.addActionListener(this);
		this.txtMot.addKeyListener(this);
		this.txtnum_contrat.addKeyListener(this);
		this.txtnomPrestation.addKeyListener(this);
		this.txtprixPrestation.addKeyListener(this);
		this.txtnomLogiciel.addKeyListener(this);
		this.txtVersion.addKeyListener(this);
		this.txtnomMateriel.addKeyListener(this);
		this.txtcoutMateriel.addKeyListener(this);

		
		//construction de  la table 
		String entetes [] = {"Num prestation", "Num contrat", "Nom prestation", "Prix prestation",
				"Nom logiciel", "Version"};
		
		Object donnees [][] = this.getDonnees ("") ; //select tous les logiciels  
		this.unTableau = new Tableau (entetes, donnees); 
		
		this.uneTable = new JTable(unTableau); 
		this.uneScroll = new JScrollPane(this.uneTable); 
		this.uneScroll.setBounds(290, 80, 470, 100);
		this.add(this.uneScroll);
		
		String entetes2 [] = {"Num prestation", "Num contrat", "Nom prestation", "Prix prestation",
				"Nom materiel", "Cout materiel"};
		
		Object donnees2 [][] = this.getDonnees2 ("") ; //select tous les materiaux  
		this.unTableau2 = new Tableau (entetes2, donnees2); 
		
		this.uneTable2 = new JTable(unTableau2); 
		this.uneScroll2 = new JScrollPane(this.uneTable2); 
		this.uneScroll2.setBounds(290, 200, 470, 100);
		this.add(this.uneScroll2);
		
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
					int retour = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer la prestation ?", 
							"Suppression Prestation Logicielle", JOptionPane.YES_NO_OPTION); 
					if(retour == 0) {
						int numPrestation = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString());
						int numContrat = Integer.parseInt(unTableau.getValueAt(numLigne, 1).toString());
						//on supprime le logiciel dans la base 
						Modele.deleteLogiciel(numPrestation);
						//on le supprime de l'affichage 
						unTableau.supprimerLigne(numLigne);
						viderChamps();
					}
				}else if (nbClic==1)
				{
					
					txtnomPrestation.setText(unTableau.getValueAt(numLigne,2).toString());
					txtprixPrestation.setText(unTableau.getValueAt(numLigne,3).toString());
					txtnomLogiciel.setText(unTableau.getValueAt(numLigne,4).toString());
					txtVersion.setText(unTableau.getValueAt(numLigne,5).toString());
					

					btEnregistrer.setText("Modifier");
				}
			}			
		});
		
		this.uneTable2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int nbClic = e.getClickCount();
				int numLigne = uneTable2.getSelectedRow();
				if (nbClic == 2)
				{
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer la prestation ?",
							"Suppresion Prestation Materielle", JOptionPane.YES_NO_OPTION);
					if(retour == 0)
					{
						int numPrestation = Integer.parseInt(unTableau2.getValueAt(numLigne, 0).toString());
						int numContrat = Integer.parseInt(unTableau2.getValueAt(numLigne, 1).toString());
						//on supprime le materiel dans la base 
						Modele.deleteMateriel(numPrestation);
						//on le supprime de l'affichage 
						unTableau2.supprimerLigne(numLigne);
						viderChamps();
					}
				}
					else if (nbClic == 1)
					{
						txtnomPrestation.setText(unTableau2.getValueAt(numLigne, 2).toString());
						txtprixPrestation.setText(unTableau2.getValueAt(numLigne, 3).toString());
						txtnomMateriel.setText(unTableau2.getValueAt(numLigne, 4).toString());
						txtcoutMateriel.setText(unTableau2.getValueAt(numLigne, 5).toString());
						
						btEnregistrer.setText("Modifier");
					}
				
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	


	public Object [][] getDonnees (String mot)
	{
		ArrayList<Logiciel> lesLogiciels= Modele.selectAllLogiciels(mot); 
		Object [][] matrice = new Object [lesLogiciels.size()][6]; 
		int i=0; 
		for (Logiciel unLogiciel : lesLogiciels)
		{
			matrice[i][0] = unLogiciel.getNum_prestation(); 
			matrice[i][1] = unLogiciel.getNum_contrat(); 
			matrice[i][2] = unLogiciel.getNom_prestation(); 
			matrice[i][3] = unLogiciel.getPrix_prestation();
			matrice[i][4] = unLogiciel.getNom_logiciel();
			matrice[i][5] = unLogiciel.getVersion();
			i++; 
		}
		return matrice ; 
	}
	
	public Object [][] getDonnees2 (String mot)
	{
		ArrayList<Materiel> lesMateriels = Modele.selectAllMateriels(mot); 
		Object [][] matrice = new Object [lesMateriels.size()][6]; 
		int i=0; 
		for (Materiel unMateriel : lesMateriels)
		{
			matrice[i][0] = unMateriel.getNum_prestation(); 
			matrice[i][1] = unMateriel.getNum_contrat(); 
			matrice[i][2] = unMateriel.getNom_prestation(); 
			matrice[i][3] = unMateriel.getPrix_prestation();
			matrice[i][4] = unMateriel.getNom_materiel();
			matrice[i][5] = unMateriel.getCout_materiel();
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
			//mise Ã  jour de l'affichage 
			this.unTableau.setDonnees (this.getDonnees(mot)); 
			this.unTableau2.setDonnees (this.getDonnees2(mot)); 
		}
		
		else if (e.getSource() == this.RBlogiciel)
		{
			this.RBlogiciel.setSelected(true);
			this.RBmateriel.setSelected(false);
			this.panelForm2.setVisible(true);
			this.panelForm3.setVisible(false);
		}
		else if (e.getSource() == this.RBmateriel)
		{
			this.RBlogiciel.setSelected(false);
			this.RBmateriel.setSelected(true);
			this.panelForm2.setVisible(false);
			this.panelForm3.setVisible(true);
		}
	}
	public void viderChamps()
	{
		this.txtnomPrestation.setText("");
		this.txtprixPrestation.setText("");
		this.txtnomLogiciel.setText("");
		this.txtVersion.setText("");
		this.txtnomMateriel.setText("");
		this.txtcoutMateriel.setText("");

		this.btEnregistrer.setText("Enregistrer");
		this.txtnomPrestation.setBackground(Color.white);
		this.txtprixPrestation.setBackground(Color.white);
	}
	
	
	public void traitement (int choix)
	{
		String nomPrestation = this.txtnomPrestation.getText(); 

		String nomLogiciel = this.txtnomLogiciel.getText();
		String Version = this.txtVersion.getText();
		
		String nomMateriel = this.txtnomMateriel.getText();
		
		
		String chaine = this.txtnum_contrat.getSelectedItem().toString();
		String tab [] = chaine.split("-");
		int num_contrat = Integer.parseInt(tab[0].trim());
		
		float prixPrestation = 0;
		float coutMateriel = 0;
		
		boolean verif = true;
		try {
			prixPrestation = Float.parseFloat(this.txtprixPrestation.getText());
			this.txtprixPrestation.setBackground(Color.white);
			
		}
		catch (NumberFormatException exp) {
			this.txtprixPrestation.setBackground(Color.red);
			verif = false;
			
//			JOptionPane.showMessageDialog(this, "Veuillez entrer un prix valide > 0");
		}
		if(this.RBmateriel.isSelected())
		{
			try {
				coutMateriel = Float.parseFloat(this.txtcoutMateriel.getText());
				this.txtcoutMateriel.setBackground(Color.white);
				
			}
			catch (NumberFormatException exp) {
				this.txtcoutMateriel.setBackground(Color.red);
	//			JOptionPane.showMessageDialog(this, "Veuillez entrer un prix valide > 0");
				verif = false;
			}
		}
		
		if (nomPrestation.equals("")) {
			this.txtnomPrestation.setBackground(Color.red);
			verif = false;
		}else {
			this.txtnomPrestation.setBackground(Color.white);
		}
		if (prixPrestation == 0) {
			this.txtprixPrestation.setBackground(Color.red);
			verif = false;
		}else {
			this.txtprixPrestation.setBackground(Color.white);
		}
		
		if (verif ==false){
			JOptionPane.showMessageDialog(this, "Veuillez remplir les champs obligatoires");
			//this.viderChamps();
		}
		else { if (choix == 0) {
			if (this.RBlogiciel.isSelected())
			{
				//instancier la classe Client 
				Logiciel unLogiciel = new Logiciel(0, num_contrat, nomPrestation, prixPrestation, nomLogiciel, Version); 
				
				//Insertion dans la BDD 
				Modele.insertLogiciel(unLogiciel);
				
				JOptionPane.showMessageDialog(this, "Insertion réussie dans la base");
				
				//on récupère le logiciel inséré pour son nouveau ID 
				unLogiciel = Modele.selectWhereLogiciel(nomLogiciel, Version); 
				
				Object ligne[] ={unLogiciel.getNum_prestation(), unLogiciel.getNum_contrat(), unLogiciel.getNom_prestation(), 
						unLogiciel.getPrix_prestation(), unLogiciel.getNom_logiciel(), unLogiciel.getVersion()}; 
				
				this.unTableau.ajouterLigne(ligne);
			} // fin if selected
			
			if (this.RBmateriel.isSelected())
			{
				//instancier la classe Client 
				Materiel unMateriel = new Materiel(0, num_contrat, nomPrestation, prixPrestation, nomMateriel, coutMateriel); 
				
				//Insertion dans la BDD 
				Modele.insertMateriel(unMateriel);
				
				JOptionPane.showMessageDialog(this, "Insertion réussie dans la base");
				
				//on récupère le matériel inséré pour son nouveau ID 
				unMateriel = Modele.selectWhereMateriel(nomMateriel, coutMateriel); 
				
				Object ligne[] ={unMateriel.getNum_prestation(), unMateriel.getNum_contrat(), unMateriel.getNom_prestation(), 
						unMateriel.getPrix_prestation(), unMateriel.getNom_materiel(), unMateriel.getCout_materiel()}; 
				
				this.unTableau2.ajouterLigne(ligne);
			} // fin if selected
		}
		else //choix =1 c'est la modif 
		{
			if (this.RBlogiciel.isSelected()){
			int numLigne = this.uneTable.getSelectedRow(); 
			int numPrestation = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			Logiciel unLogiciel = new Logiciel(numPrestation, num_contrat, nomPrestation, prixPrestation, nomLogiciel, Version);
			//update dans la base de donnéees 
			Modele.updateLogiciel(unLogiciel);
			//update dans le tableau d'affichage 
			Object ligne[] ={unLogiciel.getNum_prestation(), unLogiciel.getNum_contrat(), unLogiciel.getNom_prestation(), 
					unLogiciel.getPrix_prestation(), unLogiciel.getNom_logiciel(), unLogiciel.getVersion()}; 
			this.unTableau.modifierLigne (numLigne, ligne); 
		}
		
		
			if (this.RBmateriel.isSelected()) {
			int numLigne = this.uneTable2.getSelectedRow(); 
			System.out.println(numLigne);
			int numPrestation = Integer.parseInt(this.unTableau2.getValueAt(numLigne, 0).toString()); 
			Materiel unMateriel = new Materiel(numPrestation, num_contrat, nomPrestation, prixPrestation, nomMateriel, coutMateriel);
			//update dans la base de donnéees 
			Modele.updateMateriel(unMateriel);
			//update dans le tableau d'affichage 
			Object ligne[] ={unMateriel.getNum_prestation(), unMateriel.getNum_contrat(), unMateriel.getNom_prestation(), 
					unMateriel.getPrix_prestation(), unMateriel.getNom_materiel(), unMateriel.getCout_materiel()}; 
			this.unTableau2.modifierLigne (numLigne, ligne); 
		}
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
			//mise Ã  jour de l'affichage 
			this.unTableau.setDonnees (this.getDonnees(mot));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
/*	public void actualiserCombo() 
	{
		this.txtnum_contrat.removeAllItems();
		//remplir le combo 
		for (Vprestation uneVprestation : Modele.selectAllVprestation(""))
		{
			this.txtnum_contrat.addItem(+uneVprestation.getNum_contrat());
		}
		
	}*/
}