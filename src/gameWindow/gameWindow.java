package gameWindow;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.*;

public class gameWindow {
	int height=750;
	int width=750;
	private JFrame fenetre;
			
	public static void main(String[] args) {
		// Fenetre
		JFrame fenetreJeu = new JFrame("~~Super`s Print~~");
		fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreJeu.setSize(750,750);
		fenetreJeu.setVisible(true);
		fenetreJeu.setLocationRelativeTo(null);
		//Panel
		
	JPanel background= new JPanel();
	background.setLayout(null);
	background.setBounds(0, 0, 750, 750);
	background.setBackground(Color.white);
	fenetreJeu.add(background);
	
	// Parcours
	ImageIcon planFond = new ImageIcon("src/jeuControleur/map1.png");
	JPanel parcours= new JPanel;
	parcours.setBounds(0, 0, 750, 750);
	parcours.add(planFond);
	
	   }
	
}

