package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import controleur.Gestfact;
import controleur.User;
import modele.Modele;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	private JPanel panelConnexion = new JPanel(); 
	private JTextField txtEmail = new JTextField("adminm@gmail.com");
	private JPasswordField txtMdp =new JPasswordField("123");
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btConnexion = new JButton("Connexion");
	
	Font unePolice = new Font("Arial", Font.PLAIN, 16);
	
	private JLabel email = new JLabel("Email :");
	
	private JLabel mdp = new JLabel("MDP :");
	
	public  VueConnexion() {
		 this.setTitle("Gesfact");
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setBounds(200, 70, 700, 330);
		 this.setLayout(null);
		 this.getContentPane().setBackground(new Color (13,25,80));
		 this.setResizable(false);
		 
		 //construction du panelConnexion 
		 this.panelConnexion.setLayout(new GridLayout(3,2,5,5));
		 this.panelConnexion.setBounds(340, 50, 300, 200);
		 this.panelConnexion.setBackground(new Color (13,25,80));
		 this.panelConnexion.add(this.email).setForeground(Color.white); 
		 this.panelConnexion.add(this.txtEmail);
		 this.panelConnexion.add(this.mdp).setForeground(Color.white); 
		 this.panelConnexion.add(this.txtMdp);
		 this.panelConnexion.add(this.btAnnuler); 
		 this.panelConnexion.add(this.btConnexion);
		 this.add(this.panelConnexion);
		 
		 this.email.setFont(unePolice);
		 this.mdp.setFont(unePolice);
		 this.txtEmail.setFont(unePolice);
		 this.txtMdp.setFont(unePolice);
		 this.btAnnuler.setFont(unePolice);
		 this.btConnexion.setFont(unePolice);
		 
		 //placement de l'image 
		 ImageIcon uneImage = new ImageIcon("src/images/gestfact.png");
		 JLabel logo = new JLabel(uneImage);
		 logo.setBounds(10, 20, 300, 250);
		 this.add(logo); 
		 
		 //rendre les boutons cliquables 
		 this.btAnnuler.addActionListener(this);
		 this.btConnexion.addActionListener(this);
		//rendre les zones de textes ecoutables 
		 this.txtEmail.addKeyListener(this);
		 this.txtMdp.addKeyListener(this);
		 
		 this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == this.btAnnuler)
		 {
			 this.txtEmail.setText("");
			 this.txtMdp.setText("");
		 }
		 else if(e.getSource() == this.btConnexion)
		 {
			 traitement (); 
		 }
	}
	
	public void traitement ()
	{
		String email = this.txtEmail.getText(); 
		String mdp = new String (this.txtMdp.getPassword()); 
		 
		//crytpage du mot de passe
		mdp = Gestfact.getCrypteMDP(mdp);
		
		 //on v√©rifie dans la bdd : modele 
		 User unUser = Modele.selectWhereUser(email, mdp);
		 if(unUser == null)
		 {
			 JOptionPane.showMessageDialog(this, "VÈrifiez vos identifiants");
		 }
		 else {
			 JOptionPane.showMessageDialog(this, 
					 "\n   Bienvenue "+unUser.getPrenom() 
					 + " " + unUser.getNom() + "\n\n"
					 ); 
			 //instancier la vue generale 
			 Gestfact.instancierVueGenerale(unUser);
			 Gestfact.rendreVisibleVueConnexion(false);
			 
			 this.txtEmail.setText("");
			 this.txtMdp.setText("");
		 }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			traitement (); 
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}






