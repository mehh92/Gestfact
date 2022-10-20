package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel
{
	private String entetes []; 
	private Object [][] donnees ; 
	
	public Tableau(String[] entetes, Object[][] donnees) {
		super();
		this.entetes = entetes;
		this.donnees = donnees;
	}

	@Override
	public int getRowCount() {
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int i, int j) {
		return this.donnees[i][j];
	}

	@Override
	public String getColumnName(int j) {
		return this.entetes[j];
	}
	
	public void ajouterLigne(Object ligne[])
	{
		Object matrice [][] = new Object[this.donnees.length+1][this.entetes.length]; 
		for (int i=0; i < this.donnees.length; i++)
		{
			matrice [i] = this.donnees[i];
		}
		matrice [this.donnees.length] = ligne; 
		//on ecrase la donneees par la matrice 
		this.donnees = matrice; 
		//actualiser les donnees 
		this.fireTableDataChanged();
	}

	public void supprimerLigne(int numLigne) {
		Object matrice [][ ] = new Object[this.donnees.length-1][this.entetes.length];
		int j = 0; 
		for (int i=0; i < this.donnees.length; i++)
		{
			if (i != numLigne)
				{
					matrice [j] = this.donnees[i];
					j++; 
				}
		}
		
		//on ecrase la donneees par la matrice 
		this.donnees = matrice; 
		//actualiser les donnees 
		this.fireTableDataChanged();
		
	}

	public void modifierLigne(int numLigne, Object[] ligne) {
		Object matrice [][ ] = new Object[this.donnees.length][this.entetes.length];
		
		for (int i=0; i < this.donnees.length; i++)
		{
			if (i != numLigne)
				{
					matrice [i] = this.donnees[i]; 
				}else {
					matrice [i] =ligne; 
				}
		}
		
		//on ecrase la donneees par la matrice 
		this.donnees = matrice; 
		//actualiser les donnees 
		this.fireTableDataChanged();
		
	}
	
	public void setDonnees (Object [][] matrice)
	{
		//on ecrase la donneees par la matrice 
		this.donnees = matrice; 
		//actualiser les donnees 
		this.fireTableDataChanged();
	}
	
}


















