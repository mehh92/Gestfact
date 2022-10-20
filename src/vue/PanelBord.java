package vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Tableau;
import modele.Modele;

public class PanelBord extends PanelDeBase
{
	private JPanel panelCount = new JPanel();
	
	public PanelBord()
	{
		super (new Color (186, 223, 255)); 
		
		this.panelCount.setLayout(new GridLayout(3,1));
		this.panelCount.setBounds(10, 10, 730, 200);
		this.panelCount.setBackground(new Color(186, 223, 255));
		// this.setBackground(Color.yellow);
		
		
		String entetes[] = { "NB Clients", "NB Contrats", "NB Prestations", "NB Factures"};
		Object matrice [][] = {{Modele.count("client"), Modele.count("contrat"), Modele.count("prestation"), Modele.count("facture")}};
		Tableau unTableau = new Tableau (entetes, matrice);
		JTable uneTable = new JTable(unTableau);
		
		/* Center les valeurs du tableau */
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0; i<uneTable.getColumnCount(); i++) {
			uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		//uneTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		// uneTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		JScrollPane uneScoll = new JScrollPane(uneTable);
		uneScoll.setBounds(40, 40, 250, 100);
		this.panelCount.add(uneScoll);
	
		 
		
		
		/*
		this.panelStat.add(new JLabel(" Nombre de Pilotes : " + Modele.countPilotes()));
		this.panelStat.add(new JLabel(" Nombre d'Avions : " + Modele.countAvions()));
		this.panelStat.add(new JLabel(" Nombre de Vols : " + Modele.countVols()));
		*/
		this.add(this.panelCount);
	}
	
	public void actualiser()
	{
		this.panelCount.removeAll();
		
		String entetes[] = { "NB Clients", "NB Contrats", "NB Prestations", "NB Factures"};
		Object matrice [][] = {{Modele.count("client"), Modele.count("contrat"), Modele.count("prestation"), Modele.count("facture")}};
		Tableau unTableau = new Tableau (entetes, matrice);
		unTableau.setDonnees(matrice);
		JTable uneTable = new JTable(unTableau);
		
		JScrollPane uneScoll = new JScrollPane(uneTable);
		uneScoll.setBounds(40, 40, 250, 100);
		this.panelCount.add(uneScoll);
	
		//this.setVisible(false);
		
//		this.panelCount.add(new JLabel("Nombre d'enregistrements par table : "));
//		this.panelCount.add(new JLabel("Nombre de clients      : "+Modele.count("client")));
//		this.panelCount.add(new JLabel("Nombre de contrats      : "+Modele.count("contrat")));
//		this.panelCount.add(new JLabel("Nombre de prestations      : "+Modele.count("prestation")));
//		this.panelCount.add(new JLabel("Nombre de factures      : "+Modele.count("facture")));
		//this.add(this.panelCount);
	}
	
	
	
	
}