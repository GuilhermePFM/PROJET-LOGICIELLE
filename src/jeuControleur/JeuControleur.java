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

import Pista.Pista;
import PistaCurva.PistaCurva;
import PistaHorizontal.PistaHorizontal;
import PistaVertical.PistaVertical;

import java.awt.Polygon;


//------------------------------------------------------------------------
public class JeuControleur extends JFrame implements KeyListener {
 
 BufferedImage backBuffer; //cela est le buffer
 BufferedImage backBuffer2;
 int FPS = 30;    // actualization de la fenetre 
 int W = 1000;   
 int H = 1000;   
 int largura=W/4;
 int altura=largura;
 ImageIcon voiture1ImageIcon = new ImageIcon("src/jeuControleur/car1.png");
 ImageIcon paisagem1 = new ImageIcon("src/jeuControleu/paisagem1.png");
 Image voiture1Image= voiture1ImageIcon.getImage();
 //ImageIcon planFond = new ImageIcon("src/jeuControleur/.png");
 //=========================================
/*ImageIcon horizontal = new ImageIcon("src/jeuControleur/pista horizontal");
 ImageIcon vertical = new ImageIcon("src/jeuControleu/pista vertical.png");
 ImageIcon direitoSup = new ImageIcon("src/jeuControleu/direitoSup.png");
 ImageIcon direitoInf = new ImageIcon("src/jeuControleu/direitoInf.png");
 ImageIcon esquerdoSup = new ImageIcon("src/jeuControleu/esquerdoSup.png");
 ImageIcon esquerdoInf = new ImageIcon("src/jeuControleu/esquerdoInf.png");	 
*/
 PistaVertical pistaVertical1= new PistaVertical(0, altura, altura, largura);
 PistaVertical pistaVertical2= new PistaVertical(0, 2*altura, altura, largura);
 PistaVertical pistaVertical3= new PistaVertical(3*largura, altura, altura, largura);
 PistaVertical pistaVertical4= new PistaVertical(3*largura, 2*altura, altura, largura);
 PistaCurva pistaCurva1= new PistaCurva(0,0,altura,largura,altura,largura);
 PistaCurva pistaCurva2= new PistaCurva(3*largura,0,largura*3,altura,altura,largura);
 PistaCurva pistaCurva3= new PistaCurva(0,3*altura,largura,altura*3,altura,largura);
 PistaCurva pistaCurva4= new PistaCurva(3*largura, 3*altura, 3*largura, 3*altura,altura,largura);
 PistaHorizontal pistaHorizontal1= new PistaHorizontal(largura,0,altura,largura);
 PistaHorizontal pistaHorizontal2= new PistaHorizontal(2*largura,0,altura,largura);
 PistaHorizontal pistaHorizontal3= new PistaHorizontal(largura,3*altura,altura,largura);
 PistaHorizontal pistaHorizontal4= new PistaHorizontal(2*largura,3*altura,altura,largura);
 Pista cenario=new Pista();
PistaHorizontal[] pistaHorizontal={pistaHorizontal1, pistaHorizontal2,pistaHorizontal3,pistaHorizontal4};
 PistaVertical[] pistaVertical={pistaVertical1,pistaVertical2,pistaVertical3,pistaVertical4};
 PistaCurva[] pistaCurva={pistaCurva1,pistaCurva2,pistaCurva3,pistaCurva4};
 
 //Pista[][] pistas= {{pistaCurva1,pistaHorizontal1,pistaHorizontal2,pistaCurva2},{pistaVertical1,cenario,cenario,pistaVertical2},{pistaVertical3,cenario,cenario,pistaVertical4},{pistaCurva3,pistaHorizontal3,pistaHorizontal4,pistaCurva4}};
 
//=======================================
		 
		 
 char move;
 int xVoiture1 = W/2;
 int yVoiture1 = H/8;
 int wVoiture1= 20;
 int hVoiture1= 20;
 int vitesseVoiture1=0;
 int sinal = 1;
 double thetaVoiture1=0;
 boolean colisao=true;
 Point[] poly;
int quadradosH=4;
int quadradosV=4;
BufferedImage backBufferVoiture; 

 public void dessinerGraphiques() 
 {
	 //--------------------------------------------------------------------
  Graphics dessinerBuffer = getGraphics();
  Graphics chargerBackBuffer = backBuffer.getGraphics();
  //chargerBackBuffer.drawImage(planFond.getImage(),0, 0, W,H, this); 
 
	   
  //================================================
  chargerBackBuffer.drawImage(pistaHorizontal1.getImage(),largura,0,altura,largura, this); 
  chargerBackBuffer.drawImage(pistaHorizontal2.getImage(),2*largura,0,altura,largura, this); 
  chargerBackBuffer.drawImage(pistaHorizontal3.getImage(),largura,3*altura,altura,largura, this); 
  chargerBackBuffer.drawImage(pistaHorizontal4.getImage(),2*largura, 3*altura, altura, largura, this); 
  chargerBackBuffer.drawImage(pistaVertical1.getImage(),0, altura, altura, largura, this);
  chargerBackBuffer.drawImage(pistaVertical2.getImage(), 0, 2*altura, altura, largura, this);
  chargerBackBuffer.drawImage(pistaVertical3.getImage(),3*largura, altura, altura, largura, this);
  chargerBackBuffer.drawImage(pistaVertical4.getImage(),3*largura, 2*altura, altura, largura, this);
  chargerBackBuffer.drawImage(pistaCurva1.getImage(),0,0,altura,largura, this); 
  chargerBackBuffer.drawImage(pistaCurva2.getImage(),3*largura,0,altura,largura, this); 
  chargerBackBuffer.drawImage(pistaCurva3.getImage(),0,3*altura,altura,largura, this); 
  chargerBackBuffer.drawImage(pistaCurva4.getImage(),3*largura,3*altura,altura,largura, this);  
  //================================================
  
  //Graphics chargerBackBuffer2=backBuffer2.getGraphics();
  Graphics2D voiture1Buffer = (Graphics2D) backBuffer.getGraphics();
  
  //chargerBackBuffer2 = (Graphics2D) backBuffer2.getGraphics();

  //Ici on mettre les dessins sur la fenetre
  

  //chargerBackBuffer2.drawImage(paisagem1.getImage(), 0, 0, W, H, this);
  //chargerBackBuffer2.drawImage(paisagem1.getImage(),0, 0, W,H, this); 
  int cxVoiture1=xVoiture1+wVoiture1;
  int cyVoiture1=yVoiture1+hVoiture1;
  voiture1Buffer.rotate(thetaVoiture1,(cxVoiture1),(cyVoiture1));
  
  
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
 
 public void atualization() {
 for (int i=0;i<4;i++){
	 if (pistaHorizontal[i].pertence(xVoiture1,yVoiture1)){
		 if (pistaHorizontal[i].testeColisao(xVoiture1,yVoiture1)==true){
				// yVoiture1 = yVoiture1-10;
				 //xVoiture1=xVoiture1-10;
				 vitesseVoiture1=0;
				 yVoiture1-=  sinal*10*Math.sin(thetaVoiture1);
				 xVoiture1-= sinal*10*Math.cos(thetaVoiture1);
				 break; }
	 }
	 if (pistaVertical[i].pertence(xVoiture1,yVoiture1)){	 
		 if ((pistaVertical[i]).testeColisao(xVoiture1,yVoiture1)==true){
					// yVoiture1 = yVoiture1-10;
					// xVoiture1=xVoiture1-10;
					 vitesseVoiture1=0;
					 yVoiture1-=  sinal*10*Math.sin(thetaVoiture1);
					 xVoiture1-= sinal*10*Math.cos(thetaVoiture1);
					 break; }
				 } 
	 if (pistaCurva[i].pertence(xVoiture1,yVoiture1)){
		 if (pistaCurva[i].testeColisao(xVoiture1,yVoiture1)==true){			 
						// yVoiture1 = yVoiture1-10;
						// xVoiture1=xVoiture1-10;
						 vitesseVoiture1=0;
						 yVoiture1-=  sinal*10*Math.sin(thetaVoiture1);
						 xVoiture1-= sinal*10*Math.cos(thetaVoiture1);
						 break; }				 			
				 }
	 
	 moveObjeto1();}
	 
 }
 
 //================================cela montre quel est la piste que la voiture est
 public int determinarPistaX(){
	 int quadradoX=0;
	 int largura=this.largura;
	 for (int i=1; i<=W/largura; i++){
		 if(((i-1)*largura< xVoiture1)&& (xVoiture1<=i*largura)){
			 quadradoX=i;
		 }
		 
	 }
	return quadradoX; 
 }
 public int determinarPistaY(){
	 int quadradoY=0;
	 int altura=this.altura;
	 for (int i=1; i<=H/altura; i++){
		 if(((i-1)*altura< yVoiture1)&& (yVoiture1<=i*altura)){
			 quadradoY=i;
		 }
	 }
	return quadradoY; 
 }
 
 public int conversaoMedidasX(int x){
	 return x*W/494;
 }
 
 public int conversaoMedidasY(int y){
	 return y*H/383;
 }
 public boolean testarCurvas(){
	 return true;
 }
 
 /*public boolean testeCurva(int x, int y, int cx, int cy){
	 boolean retorno=false;
	 if( ((x-cx)^2+(y-cy)^2)>=Rm){
		 retorno= true;
	 }else if(((x-cx)^2+(y-cy)^2)<=RM){
		 retorno= true;
	 }
	 return retorno;
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
 */
public void keyPressed(KeyEvent e) {
	//TODO Clavier
	 move = e.getKeyChar();
	 if((e.getKeyCode() == e.VK_UP)&&colisao){
		   vitesseVoiture1 -= 1;
		   sinal=1;
		   atualization();
		   
		  }
	 if((e.getKeyCode() == e.VK_DOWN)&&colisao){
		   vitesseVoiture1 += 1;		   
		   sinal=1;
		   atualization();
		  }
	if(e.getKeyCode() == e.VK_LEFT){
			thetaVoiture1 -= Math.toRadians(15);
			//xVoiture1-= 10;
			
		  }
	if(e.getKeyCode() == e.VK_RIGHT){
			thetaVoiture1 += Math.toRadians(15);
			//xVoiture1+= 10;
			
		  }
			
}



public void keyReleased(KeyEvent e) {
	
	
}



public void keyTyped(KeyEvent e) {
	
	
}
 
}