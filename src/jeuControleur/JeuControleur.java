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
import java.awt.Polygon;


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
 int sinal = 1;
 double thetaVoiture1=0;
 boolean colisao=true;
 //Graphics2D chargerBackBuffer2;
 Point[] poly;

 

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
   //testeColisao();
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

//========================================================================================Poligonos 
/* int vitesseVoitures= 4;
 public boolean testeColisao(){
	 
	 int hMax=383;
	 int wMax=494;
	 int[][] tri1={{(9),(233),(11),(240),(467),(494)},{41,219,319,130,37,318}};
	 int[][] tri2={{41,233,11,382,494,449},{41,359,356,127,37,358}};
	 int[][] tri3={{9,368,52,397,493,497},{331,355,356,278,308,358}};
	 int[][] ret1={{128,141,128,110},{130,130,278,268}};
	 Polygon[] triangulos= new Polygon[6];
	 
	 int ySup=39;
	 int yInf=359;
	 Rectangle voitures= new Rectangle(xVoiture1,yVoiture1,wVoiture1,hVoiture1);
	 for (int i=1 ; i < 7 ; i++){
		 triangulos[i]= Polygon((tri1[1][i]),(tri2[1][i],tri2[2][i]),(tri3[1][i], tri3[2][i]), 3);
	 }
	for (int j=1 ; j < 7 ; j++){
		boolean teste=false;
		teste= triangulos[j].intersects(voitures);
		if (teste){
			vitesseVoitures--;
		}else{
			
		}
	}
 }*/
 //====================================================================colisao entre 2 objetos
 /*public boolean colisao(int obj1X, int obj1Y, int obj1W, int obj1H,
		   int obj2X, int obj2Y, int obj2W, int obj2H) {
		  if ((obj1X >= obj2X && obj1X <= obj2X + obj2W)
		    && (obj1Y >= obj2Y && obj1Y <= obj2Y + obj2H)) {
		   return true;
		  } else if ((obj1X + obj1W >= obj2X && obj1X + obj1W <= obj2X + obj2W)
		    && (obj1Y >= obj2Y && obj1Y <= obj2Y + obj2H)) {
		   return true;
		  } else if ((obj1X >= obj2X && obj1X <= obj2X + obj2W)
		    && (obj1Y + obj1H >= obj2Y && obj1Y + obj1H <= obj2Y + obj2H)) {
		   return true;
		  } else if ((obj1X + obj1W >= obj2X && obj1X + obj1W <= obj2X + obj2W)
		    && (obj1Y + obj1H >= obj2Y && obj1Y + obj1H <= obj2Y + obj2H)) {
		   return true;
		  } else {
		   return false;
		  }
		 }*/
 
 public boolean colisao(int pontoX, int pontoY, int x, int y, int w, int h) {
	  if ((pontoX >= x && pontoX <= x + w) && (pontoY >= y && pontoY <= y + h)) {
	   return true;
	  } else {
	   return false;
	  }
	 }
 public void moveObjeto1()
 
 {
	 
	 yVoiture1+=  sinal*vitesseVoiture1*Math.sin(thetaVoiture1);
	 xVoiture1+= sinal*vitesseVoiture1*Math.cos(thetaVoiture1);
	 
 }
 
 boolean objeto1Colidiu = false;
 boolean colidiuEsquerda = false;
 boolean colidiuDireita = false;
 boolean colidiuCima = false;
 boolean colidiuBaixo = false;
 
 public void atualization() 
 
 {
	 
	 
	 if (colidiuSuperior()==true){
		 yVoiture1 = 100;
		 vitesseVoiture1=0;
	 }
	 if (colidiuInferior()==true){
		 yVoiture1= conversaoMedidasY(330);
		 vitesseVoiture1=0;
	 }
	 moveObjeto1();
	 
 }
 
 public int conversaoMedidasX(int x){
	 return x*W/494;
 }
 
 public int conversaoMedidasY(int y){
	 return y*H/383;
 }
 
 public boolean colidiuSuperior(){
		colidiuEsquerda = colisao(xVoiture1, yVoiture1+hVoiture1/2, 0, 0, conversaoMedidasY(494), conversaoMedidasX(39));
	 colidiuDireita = colisao(xVoiture1+wVoiture1, yVoiture1+hVoiture1/2, 0, 0, conversaoMedidasY(494), conversaoMedidasX(39));
	 colidiuCima = colisao(xVoiture1+wVoiture1/2, yVoiture1, 0, 0,conversaoMedidasY(494), conversaoMedidasX(39));
	 colidiuBaixo = colisao(xVoiture1+wVoiture1/2, yVoiture1+hVoiture1, 0, 0, conversaoMedidasY(494), conversaoMedidasX(39));
	 boolean retorno=false;
	 if (colidiuCima|colidiuDireita|colidiuEsquerda|colidiuBaixo){
		 retorno= true; 
	 }
	 return retorno;
 }
 public boolean colidiuInferior(){
	colidiuEsquerda = colisao(xVoiture1, yVoiture1+hVoiture1/2, 0, conversaoMedidasY(359), conversaoMedidasX(494), conversaoMedidasY(24));
	 colidiuDireita = colisao(xVoiture1+wVoiture1, yVoiture1+hVoiture1/2, 0, conversaoMedidasY(359), conversaoMedidasX(494), conversaoMedidasY(24));
	 colidiuCima = colisao(xVoiture1+wVoiture1/2, yVoiture1, 0, conversaoMedidasY(359),conversaoMedidasX(494), conversaoMedidasY(24));
	 colidiuBaixo = colisao(xVoiture1+wVoiture1/2, yVoiture1+hVoiture1, 0, conversaoMedidasY(383), conversaoMedidasX(494), conversaoMedidasY(24));
	 boolean retorno=false;
	 if (colidiuCima|colidiuDireita|colidiuEsquerda|colidiuBaixo){
		 retorno= true; 
	 }
	 return retorno;
}
 
public void keyPressed(KeyEvent e) {
	//TODO Clavier
	 move = e.getKeyChar();
	 if((e.getKeyCode() == e.VK_UP)&&colisao){
		   vitesseVoiture1 -= 3;
		   sinal=1;
		   atualization();
		   
		  }
	 if((e.getKeyCode() == e.VK_DOWN)&&colisao){
		   vitesseVoiture1 += 3;		   
		   sinal=1;
		   atualization();
		  }
	if(e.getKeyCode() == e.VK_LEFT){
			thetaVoiture1 -= Math.toRadians(20);
			//xVoiture1-= 10;
			
		  }
	if(e.getKeyCode() == e.VK_RIGHT){
			thetaVoiture1 += Math.toRadians(20);
			//xVoiture1+= 10;
			
		  }
			
}



public void keyReleased(KeyEvent e) {
	
	
}



public void keyTyped(KeyEvent e) {
	
	
}
 
}