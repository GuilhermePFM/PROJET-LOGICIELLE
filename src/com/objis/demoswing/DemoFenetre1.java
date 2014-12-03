package com.objis.demoswing;

import javax.swing.JFrame;

public class DemoFenetre1 {

	public static void main(String[] args) {
		// TODO 1. Instancier une JFrame
		JFrame maFenetre1 = new JFrame() ;// cree la fenetre
		// TODO 2. Afficher la JFrame
		maFenetre1.setVisible(true);
		// TODO 3. Modifier le titre de la fenetre
		maFenetre1.setTitle("~~Super`s Print~~"); // Titre de la fenetre
		// TODO 4. Longueur de la fenetre
		maFenetre1.setSize(500,500); // longueur de la fenetre
		// TODO 5. Parametre de la Fenetre
		maFenetre1.setResizable(false); // permit le utilizateur de changer le longueur de la fenetre
		// TODO 6. Centralizer la fenetre
		maFenetre1.setLocationRelativeTo(null); // centralize la ouverture de la fenetre
		
		
	}

}
