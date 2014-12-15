package jeuControleur;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.*;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JeuControleur extends JFrame implements KeyListener {
 
 BufferedImage backBuffer; //cela est le buffer
 BufferedImage backBuffer2;
 int FPS = 30;    // actualization de la fenetre 
 int W = 900;   
 int H = 900;   
 ImageIcon voiture1ImageIcon = new ImageIcon("src/jeuControleur/car1.png");
 Image voiture1Image= voiture1ImageIcon.getImage();
 ImageIcon planFond = new ImageIcon("src/jeuControleur/map1.png");
 ImageIcon paisagem = new ImageIcon("src/jeuControleur/paisagem1.png");
 
 char move;
 int xVoiture1 = W/2;
 int yVoiture1 = H/4;
 int wVoiture1= 100;
 int hVoiture1= 100;
 int vitesseVoiture1=0;
 double thetaVoiture1=0;
 boolean colisao=true;
 Area areaProibida;

 public void atualization() {
 
 }
 

 public void dessinerGraphiques() 
 {
	 //--------------------------------------------------------------------
  Graphics dessinerBuffer = getGraphics();
  Graphics dessinerBuffer2 = getGraphics();
  Graphics chargerBackBuffer = backBuffer.getGraphics();
  Graphics2D voiture1Buffer = (Graphics2D) backBuffer.getGraphics();


  //Ici on mettre les dessins sur la fenetre
  
  chargerBackBuffer.drawImage(planFond.getImage(),0, 0, W,H, this);
  
  dessinerBuffer2.drawImage(paisagem.getImage(), 0, 0, W, H, this);
  
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
  backBuffer = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);//Cree le buffer image
  try {
	  areaProibida= createArea(backBuffer2, 200);
  } catch( Exception e){
	  System.out.println("erro no plano de fundo");
  }
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
 
 //-------------------------------------------------------------------------------------------------
	    public static Area createArea(BufferedImage image, int maxTransparency) {    	
	        Area area = new Area();
	        Rectangle rectangle = new Rectangle();
	        for (int x = 0; x < image.getWidth(); x++) {
	            for (int y = 0; y < image.getHeight(); y++) {
	                int rgb = image.getRGB(x, y);
	                rgb = rgb >>> 24;
	                if (rgb >= maxTransparency) {
	                    rectangle.setBounds(x, y, 1, 1);
	                    area.add(new Area(rectangle));
	                }
	            }
	        }
	        return area;
	    }
//--------------------------------------------------------------------------------------------
void testeColisao(){
	if ( areaProibida.intersects(xVoiture1,yVoiture1,wVoiture1,hVoiture1)){
		colisao=false;
	} else {
		colisao= true;
	}
}

public void keyPressed(KeyEvent e) {
	//TODO Clavier
	 move = e.getKeyChar();
	 if((e.getKeyCode() == e.VK_UP)&&colisao){
		   vitesseVoiture1 -= 3;
		   xVoiture1+= Math.sin(thetaVoiture1)*vitesseVoiture1;
		   yVoiture1+=  Math.cos(thetaVoiture1)*vitesseVoiture1;
		   
		  }
	 if((e.getKeyCode() == e.VK_DOWN)&&colisao){
		   vitesseVoiture1 += 3;
		   xVoiture1-= Math.sin(thetaVoiture1)*vitesseVoiture1;
		   yVoiture1-=  Math.cos(thetaVoiture1)*vitesseVoiture1;
		   
		  }
	if(e.getKeyCode() == e.VK_LEFT){
			thetaVoiture1 -= Math.toRadians(10);
			
		  }
	if(e.getKeyCode() == e.VK_RIGHT){
			thetaVoiture1 += Math.toRadians(10);
			
		  }
			
}



public void keyReleased(KeyEvent e) {
	
	
}



public void keyTyped(KeyEvent e) {
	
	
}
 
}