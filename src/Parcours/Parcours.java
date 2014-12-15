package Parcours;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.*;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Parcours extends JFrame implements KeyListener {
	BufferedImage backBuffer; //cela est le buffer 
	int W = 900;   
	int H = 900;   
	ImageIcon voiture1ImageIcon = new ImageIcon("src/jeuControleur/car1.png");
	Image voiture1Image= voiture1ImageIcon.getImage();
	ImageIcon rue = new ImageIcon("src/jeuControleur/pista1.png");
	ImageIcon paisage = new ImageIcon("src/jeuControleur/paisagem1.png");
	

}
