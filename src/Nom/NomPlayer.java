package Nom;

import javax.swing.JOptionPane;

public class NomPlayer {
	
	public static void main (String args[])
	{ String nom;
	boolean n=true;
	while (n)
	{nom=JOptionPane.showInputDialog ( "~~Super`s Print~~ ","Entrez votre nom");
	if (nom==null) System.out.println("Pas de texte saisi");
	else 
		n=false;
		System.out.println("nom choisi: "+nom);
	}
	}

}
