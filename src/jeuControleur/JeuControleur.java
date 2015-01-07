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
import javax.swing.JLabel;
import javax.swing.Timer;

import Pista.Pista;
import PistaCurva.PistaCurva;
import PistaHorizontal.PistaHorizontal;
import PistaVertical.PistaVertical;
import Voitures.Voiture;

import java.awt.Polygon;


//------------------------------------------------------------------------
public class JeuControleur extends JFrame implements KeyListener {
 //==============================================Parametros
 BufferedImage backBuffer; //cela est le buffer
 BufferedImage backBuffer2;
 int FPS = 30;    // actualization de la fenetre 
 int W = 1000;   
 int H = 1000;   
 int largura=W/4;
 int altura=largura;
 char move;
 int time=0;
 boolean colisao=true;
int quadradosH=4;
int quadradosV=4;
BufferedImage backBufferVoiture; 
;

 //ImageIcon planFond = new ImageIcon("src/jeuControleur/.png");

//===============================================================================Imagens
//============================Carros
//ImageIcon voiture1ImageIcon = new ImageIcon("src/jeuControleur/car1.png");
//Image voiture1Image= voiture1ImageIcon.getImage();
Voiture voiture1= new Voiture(W/2,H/8,2);//cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc

//===========================Pista
ImageIcon linha= new ImageIcon("src/jeuControleur/linha de chegada.png");
 PistaVertical pistaVertical1= new PistaVertical(0, 1, altura, largura);
 PistaVertical pistaVertical2= new PistaVertical(0, 2, altura, largura);
 PistaVertical pistaVertical3= new PistaVertical(3, 1, altura, largura);
 PistaVertical pistaVertical4= new PistaVertical(3, 2, altura, largura);
 PistaCurva pistaCurva1= new PistaCurva(0,0,1,1,altura,largura);
 PistaCurva pistaCurva2= new PistaCurva(3,0,3,1,altura,largura);
 PistaCurva pistaCurva3= new PistaCurva(0,3,1,3,altura,largura);
 PistaCurva pistaCurva4= new PistaCurva(3,3,3,3,altura,largura);
 PistaHorizontal pistaHorizontal1= new PistaHorizontal(1,0,altura,largura);
 PistaHorizontal pistaHorizontal2= new PistaHorizontal(2,0,altura,largura);
 PistaHorizontal pistaHorizontal3= new PistaHorizontal(1,3,altura,largura);
 PistaHorizontal pistaHorizontal4= new PistaHorizontal(2,3,altura,largura);
 Pista cenario=new Pista();
 ImageIcon grama= new ImageIcon("src/jeuControleur/grama.png");
PistaHorizontal[] pistaHorizontal={pistaHorizontal1, pistaHorizontal2,pistaHorizontal3,pistaHorizontal4};
 PistaVertical[] pistaVertical={pistaVertical1,pistaVertical2,pistaVertical3,pistaVertical4};
 PistaCurva[] pistaCurva={pistaCurva1,pistaCurva2,pistaCurva3,pistaCurva4};
//=======================================
		 
		 
 
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
  chargerBackBuffer.drawImage(linha.getImage(), 1*largura,0,altura,largura, this);
  
  chargerBackBuffer.drawImage(grama.getImage(),largura,altura,altura,largura, this); 
  chargerBackBuffer.drawImage(grama.getImage(),2*largura,altura,altura,largura, this); 
  chargerBackBuffer.drawImage(grama.getImage(),largura,2*altura,altura,largura, this); 
  chargerBackBuffer.drawImage(grama.getImage(),2*largura,2*altura,altura,largura, this); 
  //================================================
  
  //Graphics chargerBackBuffer2=backBuffer2.getGraphics();
  Graphics2D voiture1Buffer = (Graphics2D) backBuffer.getGraphics();
  
  //chargerBackBuffer2 = (Graphics2D) backBuffer2.getGraphics();

  //Ici on mettre les dessins sur la fenetre
  

  //chargerBackBuffer2.drawImage(paisagem1.getImage(), 0, 0, W, H, this);
  //chargerBackBuffer2.drawImage(paisagem1.getImage(),0, 0, W,H, this); 

  voiture1Buffer.rotate(voiture1.getTheta(),voiture1.getX()+voiture1.getW()/2,voiture1.getY()+voiture1.getH()/2);//cccccccccccccccccccccccccccccccccccccccccccccccccccc
  
  
  voiture1Buffer.drawImage(voiture1.getImage(), (int)voiture1.getX(), (int)voiture1.getY(), (int)voiture1.getW(),(int)voiture1.getH(), this);//ccccccccccccccccccccccccccccccccccccccccccccc
  
  
  
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
  Timer timer = new Timer(1000);
  Label label = new JLabel(this.time + "");
  
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
     time++;
     System.out.println(time);
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
 
 /*public boolean colisao(int pontoX, int pontoY, int x, int y, int w, int h) {
	  if ((pontoX >= x && pontoX <= x + w) && (pontoY >= y && pontoY <= y + h)) {
	   return true;
	  } else {
	   return false;
	  }
	 }*/
 
 boolean objeto1Colidiu = false;
 boolean colidiuEsquerda = false;
 boolean colidiuDireita = false;
 boolean colidiuCima = false;
 boolean colidiuBaixo = false;
 
 public void atualization() {
 for (int i=0;i<4;i++){
	 if (pistaHorizontal[i].pertence( voiture1.getX(),voiture1.getY())){
		 if (pistaHorizontal[i].testeColisao(voiture1.getX(),voiture1.getY())==true){
				 voiture1.moveVoiture(-10);
				 voiture1.setVitesse(0);
				 break; }
	 }
	 if (pistaVertical[i].pertence(voiture1.getX(),voiture1.getY())){	 
		 if ((pistaVertical[i]).testeColisao(voiture1.getX(),voiture1.getY())==true){
			 	voiture1.moveVoiture(-10);
			 	voiture1.setVitesse(0);
					 break; }
				 } 
	 if (pistaCurva[i].pertence(voiture1.getX(),voiture1.getY())){
		 if (pistaCurva[i].testeColisao(voiture1.getX(),voiture1.getY())==true){			 
			voiture1.moveVoiture(-10);	
			voiture1.setVitesse(0);
			 break; }				 			
				 }
	 //voiture1.acellerate();
	 voiture1.moveVoiture(1);}
	 
 }
 
 //================================cela montre quel est la piste que la voiture est
 public int determinarPistaX(){
	 int quadradoX=0;
	 int largura=this.largura;
	 for (int i=1; i<=W/largura; i++){
		 if(((i-1)*largura< voiture1.getX())&& (voiture1.getX()<=i*largura)){
			 quadradoX=i;
		 }
		 
	 }
	return quadradoX; 
 }
 public int determinarPistaY(){
	 int quadradoY=0;
	 int altura=this.altura;
	 for (int i=1; i<=H/altura; i++){
		 if(((i-1)*altura< voiture1.getY())&& (voiture1.getY()<=i*altura)){
			 quadradoY=i;
		 }
	 }
	return quadradoY; 
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
		   voiture1.acellerate();
		   atualization();
		   
		  }
	 if((e.getKeyCode() == e.VK_DOWN)&&colisao){
		   voiture1.frenage();		   
		   atualization();
		  }
	if(e.getKeyCode() == e.VK_LEFT){
			voiture1.turnRight();
			atualization();
			//xVoiture1-= 10;
			
		  }
	if(e.getKeyCode() == e.VK_RIGHT){
			voiture1.turnLeft();
			atualization();
			//xVoiture1+= 10;
			
		  }
			
}



public void keyReleased(KeyEvent e) {
	
	
}



public void keyTyped(KeyEvent e) {
	
	
}
 
}