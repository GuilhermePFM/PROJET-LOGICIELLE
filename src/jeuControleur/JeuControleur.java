package jeuControleur;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;



//------------------------------------------------------------------------
public class JeuControleur extends JFrame implements KeyListener {
 
 BufferedImage backBuffer; //cela est le buffer
 BufferedImage backBuffer2;
 int FPS = 30;    // actualization de la fenetre 
 int W = 900;   
 int H = 900;   
 ImageIcon voiture1ImageIcon = new ImageIcon("src/jeuControleur/car1.png");
 ImageIcon paisagem1 = new ImageIcon("src/jeuControleu/paisagem1.png");
 Image voiture1Image= voiture1ImageIcon.getImage();
 ImageIcon planFond = new ImageIcon("src/jeuControleur/map1.png");
 char move;
 int xVoiture1 = W/2;
 int yVoiture1 = H/4;
 int wVoiture1= 100;
 int hVoiture1= 100;
 int vitesseVoiture1=0;
 double thetaVoiture1=0;
 boolean colisao=true;
 //Graphics2D chargerBackBuffer2;
 Point[] poly;
 public void atualization() {
 }
 

 public void dessinerGraphiques() 
 {
	 //--------------------------------------------------------------------
  Graphics dessinerBuffer = getGraphics();

  Graphics chargerBackBuffer = backBuffer.getGraphics();
  Graphics chargerBackBuffer2=backBuffer2.getGraphics();
  Graphics2D voiture1Buffer = (Graphics2D) backBuffer.getGraphics();
  //chargerBackBuffer2 = (Graphics2D) backBuffer2.getGraphics();

  //Ici on mettre les dessins sur la fenetre
  
  chargerBackBuffer.drawImage(planFond.getImage(),0, 0, W,H, this); 
  //chargerBackBuffer2.drawImage(paisagem1.getImage(), 0, 0, W, H, this);
  chargerBackBuffer2.drawImage(paisagem1.getImage(),0, 0, W,H, this); 
  voiture1Buffer.rotate(thetaVoiture1,(xVoiture1+wVoiture1/2),(yVoiture1+hVoiture1/4));
  
  voiture1Buffer.drawImage(voiture1Image, xVoiture1, yVoiture1, this);
  
  dessinerBuffer.drawImage(backBuffer, 0, 0, this);
 }//-----------------------------------------------------------------------

 
 //Cela cree la fenetre 
 public void inicialization() {
  setTitle("~~Super`s Print~~");
  setSize(W, H);
  setResizable(false);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  
  
  setLayout(null);
  setVisible(true);
  addKeyListener(this);
  //graficoPaisagem = paisagem.createGraphics();
  backBuffer = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);//Cree le buffer image
  backBuffer2 = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);//Cree le buffer image
  
 }
 //---------------------------------------------------------------------------------------------------
 //Cela est le run
 // il a une loop infint
 public void run() {
  inicialization();
  while (true) {
	 // testeColisao();
   atualization();
   dessinerGraphiques();
   testeColisao();
    try {
     Thread.sleep(1000/FPS); // cela fait le program attend pour 1000/FPS miliseconds
    } catch (Exception e) {
     System.out.println("Erreur");
    }
  }
 }
 //===================================================================================================================
 public static void main(String[] args) {
	 JeuControleur game = new JeuControleur();//CRIAMOS UM OBJETO A PARTIR DESSA PROPRIA CLASSE
	 game.run();//CHAMAMOS O METODO RUN(), O MÉTODO RUN() EXECUTA O INICIALIZAR(), ATUALIZAR() E DESENHARGRAFICOS()
 }//==================================================================================================================

public void keyPressed(KeyEvent e) {
	//TODO Clavier
	 move = e.getKeyChar();
	 if((e.getKeyCode() == e.VK_UP)&&colisao){
		   vitesseVoiture1 -= 3;
		   yVoiture1+=  10;
		   
		  }
	 if((e.getKeyCode() == e.VK_DOWN)&&colisao){
		   vitesseVoiture1 += 3;
		   
		   yVoiture1-=  10;
		   
		  }
	if(e.getKeyCode() == e.VK_LEFT){
			//thetaVoiture1 -= Math.toRadians(10);
			xVoiture1+= 10;
			
		  }
	if(e.getKeyCode() == e.VK_RIGHT){
			//thetaVoiture1 += Math.toRadians(10);
			xVoiture1-= 10;
			
		  }
			
}



public void keyReleased(KeyEvent e) {
	
	
}



public void keyTyped(KeyEvent e) {
	
	
}
 
}