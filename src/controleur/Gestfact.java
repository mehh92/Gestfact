package controleur;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import vue.VueConnexion;
import vue.VueGenerale;

public class Gestfact {
	
	private static VueConnexion uneVueConnexion ; 
	private static VueGenerale uneVueGenerale ; 
	
	public static void rendreVisibleVueConnexion (boolean action)
	{
		uneVueConnexion.setVisible(action);
	}
	
	public static void instancierVueGenerale (User unUser)
	{
		uneVueGenerale = new VueGenerale (unUser);
	}
	
	public static void main (String args[])
	{
		uneVueConnexion = new VueConnexion(); 
	}
	
	public static byte[] getSHA(String input)
    { 
		byte [] tab = null;  //tableau de int
		try {
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA1"); 
        tab = md.digest(input.getBytes(StandardCharsets.UTF_8));
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
		}
		catch(NoSuchAlgorithmException exp)
		{
			exp.printStackTrace();
		}
        
        return tab;
    }
    
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
	
	public static String getCrypteMDP(String mdp)
	{
		//cryptage du mdp
		
		return toHexString(getSHA(mdp));
	}
}
