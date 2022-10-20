package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Contrat;
import controleur.Facture;
import controleur.Logiciel;
import controleur.Materiel;
import controleur.Prestation;
import controleur.TableauBord;
import controleur.User;
import controleur.Vfacture;
import controleur.Vprestation;

public class Modele {

	
	//pour les PC : 
	private static Bdd uneBdd = new Bdd("localhost:3307", "bdd_ppe_lourd", "root", "");
	//private static Bdd uneBdd = new Bdd("172.20.111.130", "bdd_ppe_lourd", "mehdi", "mehdi");
	public static User selectWhereUser (String email, String mdp )
	{
		User unUser = null;  
		String requete = "select * from user where email='"+email+"' and mdp='"+mdp+"' ;" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); 
			if (unResultat.next())  
			{
				 unUser = new User (
						 unResultat.getInt("iduser"),  unResultat.getString ("nom"), 
						 unResultat.getString ("prenom"), unResultat.getString ("email"),
						 unResultat.getString ("mdp")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
			exp.printStackTrace();
		}
		return unUser; 
	}
	
	
	
	/***************************************************** Gestion des clients ***************************************************************/
	/*****************************************************************************************************************************************/
	
	public static void insertClient(Client unClient)
	{
		String requete = "insert into client values (null, '" + unClient.getNom()
		+ "','" + unClient.getSiret()+"','"+unClient.getTel() + "','"
		+ unClient.getEmail() + "','" + unClient.getAdresse()+ "','" 
		+ unClient.getCP() + "','" + unClient.getVille() + "');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static ArrayList<Client> selectAllClients (String mot)
	{
		ArrayList<Client> lesClients = new ArrayList<Client>(); 
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from client ; "; 
		} else {
			requete = "select * from client where nom like '%"+mot+"%' or siret like '%"+mot+"%' " 
				  +	" or adresse like '%"+mot+"%' ;";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des rÃ©sultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				Client unClient = new Client (
						desResultats.getInt("idclient"),  desResultats.getString ("nom"), 
						desResultats.getString ("siret"), desResultats.getString ("tel"),
						desResultats.getString ("email"), desResultats.getString ("adresse"),
						desResultats.getString ("CP"), desResultats.getString ("ville")
						
						);
				//On ajoute cet objet Ã  la liste des clients 
				lesClients.add(unClient);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesClients; 
	}
	
	public static Client selectWhereClient (int idclient )
	{
		Client unClient = null;  
		String requete = "select * from client where idclient = "+ idclient +";" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un rÃ©sultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				 unClient = new Client (
						 unResultat.getInt("idclient"),  unResultat.getString ("nom"), 
						 unResultat.getString ("siret"), unResultat.getString ("tel"),
						 unResultat.getString ("email"), unResultat.getString ("adresse"),
						 unResultat.getString ("CP"), unResultat.getString ("ville")
						 
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unClient; 
	}
	public static void deleteClient(int idclient)
	{
		String requete = "delete from client where idclient = " + idclient +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	public static void updateClient(Client unClient)
	{
		String requete = "update client  set nom = '" + unClient.getNom()
		+ "', siret = '" + unClient.getSiret()+"', tel = '"+unClient.getTel() + "',email = '"
		+ unClient.getEmail() + "',adresse = '" + unClient.getAdresse()+ "', CP = '" 
		+ unClient.getCP() + "', ville = '" + unClient.getVille() + "' where idclient = " + unClient.getIdclient() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static Client selectWhereClient(String email, String tel) {
		Client unClient = null;  
		String requete = "select * from client where email='"+email+"' and tel='"+tel+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un rÃ©sultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				 unClient = new Client (
						 unResultat.getInt("idclient"),  unResultat.getString ("nom"), 
						 unResultat.getString ("siret"), unResultat.getString ("tel"),
						 unResultat.getString ("email"), unResultat.getString ("adresse"),
						 unResultat.getString ("CP"), unResultat.getString ("ville")
						
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unClient; 
	}
	
	/***************************************************** Gestion des contrats ***************************************************************/
	/*****************************************************************************************************************************************/
	
	public static void insertContrat(Contrat unContrat)
	{
		String requete = "insert into contrat values (null, " + unContrat.getIdclient()
		+ ",'" + unContrat.getDate_souscription()+"','"+unContrat.getEtat_du_contrat() + "','"
		+ unContrat.getObjet_du_contrat() + "'," + unContrat.getMontant_mensuel_ht()+ ");";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static ArrayList<Contrat> selectAllContrats (String mot)
	{
		ArrayList<Contrat> lesContrats = new ArrayList<Contrat>(); 
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from contrat ; "; 
		} else {
			requete = "select * from contrat where num_contrat like '%"+mot+"%' or date_souscription like '%"+mot+"%' " 
				  +	" or etat_du_contrat like '%"+mot+"%' or objet_du_contrat like '%"+mot+"%' ;";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des rÃ©sultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				Contrat unContrat = new Contrat (
						desResultats.getInt("num_contrat"), desResultats.getInt("idclient"), 
						desResultats.getString ("date_souscription"), desResultats.getString ("etat_du_contrat"),
						desResultats.getString ("objet_du_contrat"), desResultats.getFloat("montant_mensuel_ht")	
						);
				//On ajoute cet objet Ã  la liste des clients 
				lesContrats.add(unContrat);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesContrats; 
	}
	
	public static Contrat selectWhereContrat (int num_contrat )
	{
		Contrat unContrat = null;  
		String requete = "select * from contrat where num_contrat = "+ num_contrat +";" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un rÃ©sultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				 unContrat = new Contrat (
						 unResultat.getInt("num_contrat"),  unResultat.getInt("idclient"), 
						 unResultat.getString ("date_souscription"), unResultat.getString ("etat_du_contrat"),
						 unResultat.getString ("objet_du_contrat"), unResultat.getFloat("montant_mensuel_ht")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unContrat; 
	}
	
	
	public static void deleteContrat(int num_contrat)
	{
		String requete = "delete from contrat where num_contrat = " + num_contrat +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static void updateContrat(Contrat unContrat)
	{
		String requete = "update contrat  set idclient = '" + unContrat.getIdclient() + 
		"', date_souscription = '" + unContrat.getDate_souscription() +
		"', etat_du_contrat = '" + unContrat.getEtat_du_contrat() +
		"', objet_du_contrat = '" + unContrat.getObjet_du_contrat() + 
		"', montant_mensuel_ht = '" + unContrat.getMontant_mensuel_ht() + 
		"' where num_contrat = " + unContrat.getNum_contrat() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static Contrat selectWhereContrat(String date_souscription, String objet_du_contrat) {
		Contrat unContrat = null;  
		String requete = "select * from contrat where date_souscription='"+date_souscription+"' and objet_du_contrat='"+objet_du_contrat+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un rÃ©sultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				 unContrat = new Contrat (
						 unResultat.getInt("num_contrat"),  unResultat.getInt ("idclient"), 
						 unResultat.getString ("date_souscription"), unResultat.getString ("etat_du_contrat"),
						 unResultat.getString ("objet_du_contrat"), unResultat.getFloat ("montant_mensuel_ht")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unContrat; 
	}
	
	/***************************************************** Gestion des prestations********* **************************************************/
	/*****************************************************************************************************************************************/
	
	public static ArrayList<Prestation> selectAllPrestations (String mot)
	{
		ArrayList<Prestation> lesPrestations = new ArrayList<Prestation>(); 
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from prestation ; "; 
		} else {
			requete = "select * from prestation where num_prestation like '%"+mot+"%' or num_contrat like '%"+mot+"%' " 
				  +	" or nom_prestation like '%"+mot+"%' or prix_prestation like '%"+mot+"%' ;";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des rÃ©sultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				Prestation unePrestation = new Prestation (
						desResultats.getInt("num_prestation"), desResultats.getInt("num_contrat"), 
						desResultats.getString ("nom_prestation"), desResultats.getFloat("prix_prestation")	
						);
				//On ajoute cet objet Ã  la liste des clients 
				lesPrestations.add(unePrestation);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesPrestations; 
	}
	
	/***************************************************** Gestion des prestations Logiciels **************************************************/
	/*****************************************************************************************************************************************/
	
	
/*	public static void insertLogiciel(Logiciel unLogiciel)
	{
		String requete = "insert into logiciel values (null, '" + unLogiciel.getNum_contrat()
		+ "','" + unLogiciel.getNom_prestation()+"','"+unLogiciel.getPrix_prestation() + "',"
				+ " '" +unLogiciel.getNom_logiciel()+ "', '" +unLogiciel.getVersion()+"');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static ArrayList<Logiciel> selectAllLogiciels (String mot)
	{
		ArrayList<Logiciel> lesLogiciels = new ArrayList<Logiciel>(); 
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from logiciel ; "; 
		} else {
			requete = "select * from logiciel where nom_logiciel like '%"+mot+"%' or version like '%"+mot+"%';";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				Logiciel unLogiciel = new Logiciel (
						desResultats.getInt("num_prestation"),  desResultats.getInt ("num_contrat"), 
						desResultats.getString ("nom_prestation"),desResultats.getFloat ("prix_prestation"),
						desResultats.getString ("nom_logiciel"), desResultats.getString ("version")
						);
				//On ajoute cet objet à la liste des clients 
				lesLogiciels.add(unLogiciel);
				
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesLogiciels; 
	}
	
	public static Logiciel selectWhereLogiciel (int num_prestation )
	{
		Logiciel unLogiciel = null;  
		String requete = "select * from logiciel where num_prestation = "+ num_prestation +";" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				unLogiciel = new Logiciel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_logiciel"), unResultat.getString ("version")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unLogiciel; 
	}
	public static void deleteLogiciel(int num_prestation)
	{
		String requete = "delete from logiciel where num_prestation = " + num_prestation +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	public static void updateLogiciel(Logiciel unLogiciel)
	{
		String requete = "update logiciel  set num_contrat = '" + unLogiciel.getNum_contrat()
		+ "', nom_prestation = '" + unLogiciel.getNom_prestation()+"', prix_prestation  = '"+unLogiciel.getPrix_prestation() + "',nom_logiciel = '"
		+ unLogiciel.getNom_logiciel() + "',version = '" + unLogiciel.getVersion()+ "' where num_prestation = " + unLogiciel.getNum_prestation() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static Logiciel selectWhereLogiciel(String nom_logiciel, String version) {
		Logiciel unLogiciel = null;  
		String requete = "select * from logiciel where nom_logiciel='"+nom_logiciel+"' and version='"+version+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				 unLogiciel = new Logiciel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_logiciel"), unResultat.getString ("version")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unLogiciel; 
	}
	*/
	/***************************************************** Gestion des prestations Materiels **************************************************/
	/*****************************************************************************************************************************************/
	
	
	/*public static void insertMateriel(Materiel unMateriel)
	{
		String requete = "insert into logiciel values (null, '" + unMateriel.getNum_contrat()
		+ "','" + unMateriel.getNom_prestation()+"','"+unMateriel.getPrix_prestation() + "',"
				+ " '" +unMateriel.getNom_materiel()+ "', '" +unMateriel.getCout_materiel()+"');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static ArrayList<Materiel> selectAllMateriels (String mot)
	{
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>(); 
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from materiel ; "; 
		} else {
			requete = "select * from materiel where nom_materiel like '%"+mot+"%' or cout_materiel like '%"+mot+"%';";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				Materiel unMateriel = new Materiel (
						desResultats.getInt("num_prestation"),  desResultats.getInt ("num_contrat"), 
						desResultats.getString ("nom_prestation"),desResultats.getFloat ("prix_prestation"),
						desResultats.getString ("nom_materiel"), desResultats.getFloat ("cout_materiel")
						);
				//On ajoute cet objet à la liste des clients 
				lesMateriels.add(unMateriel);
				
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesMateriels; 
	}
	
	public static Materiel selectWhereMateriel (int num_prestation )
	{
		Materiel unMateriel = null;  
		String requete = "select * from materiel where num_prestation = "+ num_prestation +";" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				unMateriel = new Materiel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_materiel"), unResultat.getFloat ("cout_materiel")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unMateriel; 
	}
	public static void deleteMateriel(int num_prestation)
	{
		String requete = "delete from materiel where num_prestation = " + num_prestation +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	public static void updateMateriel(Materiel unMateriel)
	{
		String requete = "update materiel  set num_contrat = '" + unMateriel.getNum_contrat()
		+ "', nom_prestation = '" + unMateriel.getNom_prestation()+"', prix_prestation  = '"+unMateriel.getPrix_prestation() + "',nom_materiel = '"
		+ unMateriel.getNom_materiel() + "',cout_materiel = '" + unMateriel.getCout_materiel()+ "' where num_prestation = " + unMateriel.getNum_prestation() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static Materiel selectWhereMateriel(String nom_materiel, Float cout_materiel) {
		Materiel unMateriel = null;  
		String requete = "select * from logiciel where nom_materiel='"+nom_materiel+"' and cout_materiel='"+cout_materiel+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				unMateriel = new Materiel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_materiel"), unResultat.getFloat("cout_materiel")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unMateriel; 
	}
	*/
	
	/***************************************************** Gestion des prestations Logiciels **************************************************/
	/*****************************************************************************************************************************************/
	
	public static void insertLogiciel(Logiciel unLogiciel)
	{
		String requete = "insert into logiciel values (null, " + unLogiciel.getNum_contrat()
		+ ",'" + unLogiciel.getNom_prestation()+"',"+unLogiciel.getPrix_prestation() + ","
				+ " '" +unLogiciel.getNom_logiciel()+ "', '" +unLogiciel.getVersion()+"');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static ArrayList<Logiciel> selectAllLogiciels (String mot)
	{
		ArrayList<Logiciel> lesLogiciels = new ArrayList<Logiciel>(); 
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from logiciel ; "; 
		} else {
			requete = "select * from logiciel where num_prestation like '%"+mot+"%' or"
					+ " num_contrat like '%"+mot+"%' or nom_prestation like '%"+mot+"%' or"
					+ " prix_prestation like '%"+mot+"%' or " 
					+ " nom_logiciel like '%"+mot+"%' or version like '%"+mot+"%';";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				Logiciel unLogiciel = new Logiciel (
						desResultats.getInt("num_prestation"),  desResultats.getInt ("num_contrat"), 
						desResultats.getString ("nom_prestation"),desResultats.getFloat ("prix_prestation"),
						desResultats.getString ("nom_logiciel"), desResultats.getString ("version")
						);
				//On ajoute cet objet à la liste des clients 
				lesLogiciels.add(unLogiciel);
				
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesLogiciels; 
	}
	
	public static Logiciel selectWhereLogiciel (int num_prestation )
	{
		Logiciel unLogiciel = null;  
		String requete = "select * from logiciel where num_prestation = "+ num_prestation +";" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				unLogiciel = new Logiciel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_logiciel"), unResultat.getString ("version")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unLogiciel; 
	}
	public static void deleteLogiciel(int num_prestation)
	{
		String requete = "delete from logiciel where num_prestation = " + num_prestation +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	public static void updateLogiciel(Logiciel unLogiciel)
	{
		String requete = "update logiciel  set num_contrat = " + unLogiciel.getNum_contrat()
		+ ", nom_prestation = '" + unLogiciel.getNom_prestation()+"', prix_prestation  = "+unLogiciel.getPrix_prestation() + ",nom_logiciel = '"
		+ unLogiciel.getNom_logiciel() + "',version = '" + unLogiciel.getVersion()+ "' where num_prestation = " + unLogiciel.getNum_prestation() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static Logiciel selectWhereLogiciel(String nom_logiciel, String version) {
		Logiciel unLogiciel = null;  
		String requete = "select * from logiciel where nom_logiciel='"+nom_logiciel+"' and version='"+version+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				 unLogiciel = new Logiciel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_logiciel"), unResultat.getString ("version")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unLogiciel; 
	}
	
	
	/***************************************************** Gestion des prestations Materiels **************************************************/
	/*****************************************************************************************************************************************/
	
	public static void insertMateriel(Materiel unMateriel)
	{
		String requete = "insert into materiel values (null, " + unMateriel.getNum_contrat()
		+ ",'" + unMateriel.getNom_prestation()+"',"+unMateriel.getPrix_prestation() + ","
				+ " '" +unMateriel.getNom_materiel()+ "', " +unMateriel.getCout_materiel()+");";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static ArrayList<Materiel> selectAllMateriels (String mot)
	{
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from materiel ; "; 
		} else {
			requete = "select * from materiel where num_prestation like '%"+mot+"%' or"
					+ " num_contrat like '%"+mot+"%' or nom_prestation like '%"+mot+"%' or"
					+ " prix_prestation like '%"+mot+"%' or " 
					+ " nom_materiel like '%"+mot+"%' or cout_materiel like '%"+mot+"%';";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des résultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				Materiel unMateriel = new Materiel (
						desResultats.getInt("num_prestation"),  desResultats.getInt ("num_contrat"), 
						desResultats.getString ("nom_prestation"),desResultats.getFloat ("prix_prestation"),
						desResultats.getString ("nom_materiel"), desResultats.getFloat ("cout_materiel")
						);
				//On ajoute cet objet à la liste des clients 
				lesMateriels.add(unMateriel);
				
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesMateriels; 
	}
	
	public static Materiel selectWhereMateriel (int num_prestation )
	{
		Materiel unMateriel = null;  
		String requete = "select * from materiel where num_prestation = "+ num_prestation +";" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				unMateriel = new Materiel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_materiel"), unResultat.getFloat ("cout_materiel")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unMateriel; 
	}
	public static void deleteMateriel(int num_prestation)
	{
		String requete = "delete from materiel where num_prestation = " + num_prestation +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	public static void updateMateriel(Materiel unMateriel)
	{
		String requete = "update materiel  set num_contrat = " + unMateriel.getNum_contrat()
		+ ", nom_prestation = '" + unMateriel.getNom_prestation()+"', prix_prestation  = "+unMateriel.getPrix_prestation() + ",nom_materiel = '"
		+ unMateriel.getNom_materiel() + "',cout_materiel = " + unMateriel.getCout_materiel()+ " where num_prestation = " + unMateriel.getNum_prestation() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static Materiel selectWhereMateriel(String nom_materiel, Float cout_materiel) {
		Materiel unMateriel = null;  
		String requete = "select * from materiel where nom_materiel='"+nom_materiel+"' and cout_materiel='"+cout_materiel+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un résultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un résultat suivant 
			{
				//instancier la classe client : créer un objet client
				unMateriel = new Materiel (
						 unResultat.getInt("num_prestation"),  unResultat.getInt ("num_contrat"), 
						 unResultat.getString ("nom_prestation"), unResultat.getFloat ("prix_prestation"),
						 unResultat.getString ("nom_materiel"), unResultat.getFloat("cout_materiel")
						);
		}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return unMateriel; 
	}
	
	/***************************************************** Gestion des factures ***************************************************************/
	/*****************************************************************************************************************************************/
	
	public static void insertFacture(Facture uneFacture)
	{
		String requete = "insert into facture values (null, " + uneFacture.getNum_prestation()
		+ ",'" + uneFacture.getDate_facture() + "',"+uneFacture.getMontant() + ",'"
		+ uneFacture.getEtat()+ "');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static ArrayList<Facture> selectAllFactures (String mot)
	{
		ArrayList<Facture> lesFactures = new ArrayList<Facture>(); 
		String requete = ""; 
		if (mot.equals(""))
		{
			requete = "select * from vlesFacturesClients ; "; 
		} else {
			requete = "select * from vlesFacturesClients where num_facture like '%"+mot+"%' or num_prestation like '%"+mot+"%' " 
				  +	" or date_facture like '%"+mot+"%' or montant like '%"+mot+"%' or etat like '%"+mot+"%' ;";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll de PHP 
			//parcours des rÃ©sultats pour construire les instances de clients 
			while (desResultats.next()) //tant qu'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				Facture uneFacture = new Facture (
						desResultats.getInt("num_facture"), desResultats.getInt("num_prestation"), 
						desResultats.getString ("date_facture"), desResultats.getFloat("montant"),
						desResultats.getString ("etat")	
						);
				//On ajoute cet objet Ã  la liste des clients 
				lesFactures.add(uneFacture);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return lesFactures; 
	}
	
	public static Facture selectWhereFacture (int num_facture )
	{
		Facture uneFacture = null;  
		String requete = "select * from facture where num_facture = "+ num_facture +";" ; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un rÃ©sultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				 uneFacture = new Facture (
						 unResultat.getInt("num_facture"),  unResultat.getInt("num_prestation"), 
						 unResultat.getString ("date_facture"), unResultat.getFloat ("montant"),
						 unResultat.getString ("etat")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return uneFacture; 
	}
	
	
	public static void deleteFacture(int num_facture)
	{
		String requete = "delete from facture where num_facture = " + num_facture +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	
	public static void updateFacture(Facture uneFacture)
	{
		String requete = "update facture set num_prestation = '" + uneFacture.getNum_prestation() + 
		"', date_facture = '" + uneFacture.getDate_facture() +
		"', montant = " + uneFacture.getMontant() +
		" , etat = '" + uneFacture.getEtat() + 
		"' where num_facture = " + uneFacture.getNum_facture() + ";";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			unStat.execute(requete);
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
	}
	
	public static Facture selectWhereFacture (String date_facture, String etat) {
		Facture uneFacture = null;  
		String requete = "select * from facture where date_facture='"+date_facture+"' and etat='"+etat+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete); //fetch de PHP 
			//extraire un rÃ©sultat et construire une seule instance client
			if (unResultat.next()) //s'il y a un rÃ©sultat suivant 
			{
				//instancier la classe client : crÃ©er un objet client
				 uneFacture = new Facture (
						 unResultat.getInt("num_facture"),  unResultat.getInt ("num_prestation"), 
						 unResultat.getString ("date_facture"), unResultat.getFloat ("montant"),
						 unResultat.getString ("etat")
						);
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		
		return uneFacture; 
	}
	
	/********************************************************* VIEW **************************************************************************/
	/*****************************************************************************************************************************************/
	
	public static ArrayList<Vfacture> selectAllVfacture (String mot)
	{
		ArrayList<Vfacture> lesVfactures = new ArrayList<Vfacture>();
		String requete = "";
		if(mot.equals(""))
		{
			requete="select * from vlesFacturesClients ; ";
		}
		else
		{
			requete = "select * from vlesFacturesClients where "
					+ " num_facture like '%"+mot+"%' or "
					+ " num_contrat like '%"+mot+"%' or "
					+ " nom_client like '%"+mot+"%' or "
					+ " num_prestation like '%"+mot+"%' or "
					+ " nom_prestation like '%"+mot+"%' or "
					+ " date_facture like '%"+mot+"%' or "
					+ " montant like '%"+mot+"%' or "
					+ " etat like '%"+mot+"%' ; ";
		}
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll en php
			//parcours des résultats pour construire les instances de clients
			while (desResultats.next())
			{
				Vfacture unVfacture = new Vfacture (
						desResultats.getInt("num_facture"), 
						desResultats.getInt("num_contrat"),
						desResultats.getString("nom_client"),
						desResultats.getInt("num_prestation"),
						desResultats.getString("nom_prestation"),
						desResultats.getString("date_facture"),
						desResultats.getFloat("montant"),
						desResultats.getString("etat")	
						);
				// on ajoute cet objet à la liste des clients
				lesVfactures.add(unVfacture);	

			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur execution requete : "+ requete);
		}
		
		return lesVfactures;
	}
	
	public static Vfacture selectWhereVfacture(String date_facture, String etat) {
		//on vient de surcharger la mÃ©thode. 
		Vfacture unVfacture = null;  

		String requete = "select * from vlesFacturesClients where date_facture = '"+ date_facture +"'"
				+ "and etat = '"+ etat +"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				unVfacture = new Vfacture(
						unResultat.getInt("num_facture"), 
						unResultat.getInt("num_contrat"),
						unResultat.getString("nom_client"),
						unResultat.getInt("num_prestation"),
						unResultat.getString("nom_prestation"),
						unResultat.getString("date_facture"),
						unResultat.getFloat("montant"),
						unResultat.getString("etat")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return unVfacture;
	}
	
	public static ArrayList<Vprestation> selectAllVprestation(String mot)
	{
		ArrayList<Vprestation> lesVprestations = new ArrayList<Vprestation>();
		String requete = "";
		if(mot.equals(""))
		{
			requete="select * from vlesPrestationsLM ; ";
		}
		else
		{
			requete = "select * from vlesPrestationsLM where "
					+ " num_prestation like '%"+mot+"%' or "
					+ " num_contrat like '%"+mot+"%' or "
					+ " nom_prestation like '%"+mot+"%' or "
					+ " prix_prestation like '%"+mot+"%' or "
					+ " nom_materiel like '%"+mot+"%' or "
					+ " cout_materiel like '%"+mot+"%' or "
					+ " nom_logiciel like '%"+mot+"%' or "
					+ " version like '%"+mot+"%' or ";
		}
		try
		{
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete); //fetchAll en php
			//parcours des résultats pour construire les instances de clients
			while (desResultats.next())
			{
				Vprestation uneVprestation = new Vprestation (
						desResultats.getInt("num_prestation"), 
						desResultats.getInt("num_contrat"),
						desResultats.getString("nom_prestation"),
						desResultats.getFloat("prix_prestation"),
						desResultats.getString("nom_materiel"),
						desResultats.getFloat("cout_materiel"),
						desResultats.getString("nom_logiciel"),
						desResultats.getString("version")		
						);
				// on ajoute cet objet à la liste des clients
				lesVprestations.add(uneVprestation);
				

			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp)
		{
			System.out.println("Erreur execution requete : "+ requete);
		}
		
		return lesVprestations;
	}
	
	public static Vprestation selectWhereVprestation(int num_prestation, int num_contrat) {
		//on vient de surcharger la mÃ©thode. 
		Vprestation uneVprestation = null;  

		String requete = "select * from vlesPrestationsLM where num_prestation ="+num_prestation+" and num_contrat="+num_contrat+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet unResultat = unStat.executeQuery(requete);
			//extraction d'un pilote : fetch en PHP 
			if (unResultat.next()) //s'il y a un resultat 
			{
				uneVprestation = new Vprestation(
						unResultat.getInt("num_prestation"), 
						unResultat.getInt("num_contrat"), 
						unResultat.getString("nom_prestation"),
						unResultat.getFloat("prix_prestation"),
						unResultat.getString("nom_materiel"),
						unResultat.getFloat("cout_materiel"),
						unResultat.getString("nom_logiciel"),
						unResultat.getString("version")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete :"+requete);
		}
		return uneVprestation;
	}
	
	/***************************************************** Statistiques **********************************************************************/
	/*****************************************************************************************************************************************/
	
	public static int count(String table) {
		int nb = 0;
		String requete = "select count(*) as nb from " + table +";" ;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); //curseur 
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next())
			{
				nb = unResultat.getInt("nb");
			}
			unStat.close(); 
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur execution requete : " + requete);
		}
		return nb;
	}
	
	public static ArrayList<TableauBord> selectAllTableauBord()
	{
	    ArrayList<TableauBord> lesTableauBords = new ArrayList<TableauBord>();
	    String requete = "select * from tableaudebord; "; //selection sur la vue
	    try
	    {
	        uneBdd.seConnecter();
	        Statement unStat = uneBdd.getMaConnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete); //fetchAll en php
	        //parcours des rÃ©sultats pour construire les instances de techniciens
	        while (desResultats.next())
	        {
	            TableauBord leTableauBord = new TableauBord(
	                    desResultats.getString("nomc"),
	                    desResultats.getString("prenomc"),
	                    desResultats.getString("nomt"),
	                    desResultats.getString("prenomt"),
	                    desResultats.getString("matricule"),
	                    desResultats.getString("description"),
	                    desResultats.getString("dateinter")
	                    );
	            // on ajoute cet objet Ã  la liste des techniciens
	            lesTableauBords.add(leTableauBord);
	        }
	        unStat.close();
	        uneBdd.seDeConnecter();
	    }
	    catch(SQLException exp)
	    {
	        System.out.println("Erreur execution requete : "+ requete);
	    }
	    return lesTableauBords;
	}



	



	

	
}
