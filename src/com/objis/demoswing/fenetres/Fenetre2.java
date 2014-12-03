package com.objis.demoswing.fenetres;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Nom.NomPlayer;
import java.awt.GridBagLayout;;


public class Fenetre2 extends JFrame implements ActionListener {
	private int height=750;
	private int width=750;
	private JFrame fenetre;
	private Container panel;
	private Component bouton1;
	private Component bouton2;
		
    //getContentPane().setLayout(new GridBagLayout());
    //GridBagConstraints c = new GridBagConstraints();
   // GridBagConstraints d = new GridBagConstraints();
	
	public void setFrame() {
		// Fenetre------------------------------------------
		 
        
        JFrame getFenetre=new JFrame("~~Super`s Print~~");		
		// TODO 3. Modifier le titre de la fenetre
        //getFenetre().setTitle("~~Super`s Print~~"); // Titre de la fenetre
		// TODO 4. Longueur de la fenetre
        
		// TODO 5. Parametre de la Fenetre
        getFenetre().setResizable(false); // permit le utilizateur de changer le longueur de la fenetre
     // TODO 7. Ajouter des Panel pour mettre des boutons
        getFenetre().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}
	
	private void montreFenetre() {
		// TODO 6. Centralizer la fenetre
        //fenetre.setLocationRelativeTo(null); // centralize la ouverture de la fenetre
        getFenetre().pack();
        getFenetre().setSize(800,20); // longueur de la fenetre
        getFenetre().setVisible(true);
	}
	public void setPrincipalPanel() {
		// Panel-------------------------------------------
		JPanel panel= new JPanel();
		getFenetre().add(panel);
		
		// setContentPane(panel);
		//setLocationRelativeTo(null);
		// panel.setLayout(new BorderLayout());
		
	}
	
	public void setButtons() {
		
		// Bouton 1-------------------------------------
		JButton bouton1 = new JButton("1 Player ");//creer le bouton
		//c.gridy = 0; // ligne  
		//c.gridx = 0; // columns 
		//d.gridy = 1; // ligne  
		//d.gridx = 0;
		//bouton1.setSize(300, 200);
		//bouton1.addActionListener(this);// ecouter le bouton
		
		//Bouton 2---------------------------------------
		JButton bouton2 = new JButton("2 Players");//creer le bouton
		//bouton2.setSize(300, 200);
		//bouton2.addActionListener(this);// ecouter le bouton
		getPanel().add(bouton1);// ajouter le bouton dans la panel
		getPanel().add(bouton2);// ajouter le bouton dans la panel
		
	}
	
	/*public void setButtonsPanel() {
		// Panel-------------------------------------------
		JPanel buttonsPanel= new JPanel();
		buttonsPanel.add(getPanel());
		
	*/
	}	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public JFrame getFenetre() {
		return fenetre;
	}

	public void setFenetre(JFrame fenetre) {
		this.fenetre = fenetre;
	}

	public Container getPanel() {
		return panel;
	}

	public void setPanel(Container panel) {
		this.panel = panel;
	}



}

//colocar o botao na tela
