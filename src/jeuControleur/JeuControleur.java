package jeuControleur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import CurveTrack.CurveTrack;
import Fenetre.Fenetre;
import HallOfFame.HallOfFame;
import HorizontalTrack.HorizontalTrack;
import Track.Track;
import VerticalTrack.VerticalTrack;
import Voitures.Voiture;

import java.awt.Polygon;


//------------------------------------------------------------------------
public class JeuControleur extends JFrame implements KeyListener {
 //==============================================Parametros
 BufferedImage backBuffer; //cela est le buffer
 BufferedImage backBuffer2;
 int FPS = 100;    // actualization de la fenetre 
 int W = 1000;   
 int H = 1000;   
 int largura=W/4;
 int altura=largura;
 char move;
 int time=0;
 int players;
 boolean colisao=true;
 int um=0;
int quadradosH=4;
int quadradosV=4;
boolean acabou=false;
BufferedImage backBufferVoiture; 
boolean jogo=false;


 //ImageIcon planFond = new ImageIcon("src/jeuControleur/.png");


// Inicialization de la premiere Voiture
Voiture voiture1;

// Inicialization de la piste
ImageIcon linha= new ImageIcon("src/jeuControleur/linha de chegada.png");

ImageIcon grama= new ImageIcon("src/jeuControleur/grama.png");
 VerticalTrack verticalTrack1= new VerticalTrack(0, 1, altura, largura);
 VerticalTrack verticalTrack2= new VerticalTrack(0, 2, altura, largura);
 VerticalTrack verticalTrack3= new VerticalTrack(3, 1, altura, largura);
 VerticalTrack verticalTrack4= new VerticalTrack(3, 2, altura, largura);
 CurveTrack curveTrack1= new CurveTrack(0,0,1,1,altura,largura);
 CurveTrack curveTrack2= new CurveTrack(3,0,3,1,altura,largura);
 CurveTrack curveTrack3= new CurveTrack(0,3,1,3,altura,largura);
 CurveTrack curveTrack4= new CurveTrack(3,3,3,3,altura,largura);
 HorizontalTrack horizontalTrack1= new HorizontalTrack(1,0,altura,largura);
 HorizontalTrack horizontalTrack2= new HorizontalTrack(2,0,altura,largura);
 HorizontalTrack horizontalTrack3= new HorizontalTrack(1,3,altura,largura);
 HorizontalTrack horizontalTrack4= new HorizontalTrack(2,3,altura,largura);
 Track cenario=new Track();
HorizontalTrack[] horizontalTrack={horizontalTrack1, horizontalTrack2,horizontalTrack3,horizontalTrack4};
 VerticalTrack[] verticalTrack={verticalTrack1,verticalTrack2,verticalTrack3,verticalTrack4};
 CurveTrack[] curveTrack={curveTrack1,curveTrack2,curveTrack3,curveTrack4};
 Voiture voiture2;
 
 
 
// Method pour imprimer des imagens du buffer dans la fenetre	 
 
 public void dessinerGraphiques(double contador) 
 {

	 Graphics dessinerBuffer = getGraphics();
	 Graphics chargerBackBuffer = backBuffer.getGraphics();

	 chargerBackBuffer.drawImage(horizontalTrack1.getImage(),largura,0,altura,largura, this); 
	 chargerBackBuffer.drawImage(horizontalTrack2.getImage(),2*largura,0,altura,largura, this); 
	 chargerBackBuffer.drawImage(horizontalTrack3.getImage(),largura,3*altura,altura,largura, this); 
	 chargerBackBuffer.drawImage(horizontalTrack4.getImage(),2*largura, 3*altura, altura, largura, this); 
	 chargerBackBuffer.drawImage(verticalTrack1.getImage(),0, altura, altura, largura, this);
	 chargerBackBuffer.drawImage(verticalTrack2.getImage(), 0, 2*altura, altura, largura, this);
	 chargerBackBuffer.drawImage(verticalTrack3.getImage(),3*largura, altura, altura, largura, this);
	 chargerBackBuffer.drawImage(verticalTrack4.getImage(),3*largura, 2*altura, altura, largura, this);
	 chargerBackBuffer.drawImage(curveTrack1.getImage(),0,0,altura,largura, this); 
	 chargerBackBuffer.drawImage(curveTrack2.getImage(),3*largura,0,altura,largura, this); 
	 chargerBackBuffer.drawImage(curveTrack3.getImage(),0,3*altura,altura,largura, this); 
	 chargerBackBuffer.drawImage(curveTrack4.getImage(),3*largura,3*altura,altura,largura, this);  
	 chargerBackBuffer.drawImage(linha.getImage(), 1*largura,0,altura,largura, this);
	 chargerBackBuffer.drawImage(grama.getImage(),largura,altura,altura,largura, this); 
	 chargerBackBuffer.drawImage(grama.getImage(),2*largura,altura,altura,largura, this); 
	 chargerBackBuffer.drawImage(grama.getImage(),largura,2*altura,altura,largura, this); 
	 chargerBackBuffer.drawImage(grama.getImage(),2*largura,2*altura,altura,largura, this); 
	 Graphics2D voiture1Buffer = (Graphics2D) backBuffer.getGraphics();
	 
	 voiture1Buffer.rotate(voiture1.getTheta(),voiture1.getX()+voiture1.getW()/2,voiture1.getY()+voiture1.getH()/2);//cccccccccccccccccccccccccccccccccccccccccccccccccccc

	 voiture1Buffer.drawImage(voiture1.getImage(), (int)voiture1.getX(), (int)voiture1.getY(), (int)voiture1.getW(),(int)voiture1.getH(), this);//ccccccccccccccccccccccccccccccccccccccccccccc
	 if(players==2){
		 Graphics2D voiture2Buffer = (Graphics2D) backBuffer.getGraphics();
		 
		 voiture2Buffer.rotate(voiture2.getTheta(),voiture2.getX()+voiture2.getW()/2,voiture2.getY()+voiture2.getH()/2);

		 voiture2Buffer.drawImage(voiture2.getImage(), (int)voiture2.getX(), (int)voiture2.getY(), (int)voiture2.getW(),(int)voiture2.getH(), this);
	 }
	 chargerBackBuffer.setFont(new Font("Serif", Font.BOLD,30));
	 chargerBackBuffer.drawString("Temps: "+ (int)contador, 10,4*largura-10);
	 chargerBackBuffer.drawString("Laps: "+ voiture1.getLap(), 10, 4*largura-35);
	 dessinerBuffer.drawImage(backBuffer, 0, 0, this);


 }

 
 //Cela initialize la fenetre 
 public void inicializationJogo() {
  setTitle("~~Super`s Print~~");
  setSize(W, H);
  setResizable(false);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  setLayout(null);
  setVisible(true);
  setLocationRelativeTo(null);
  addKeyListener(this);
  //graficoPaisagem = paisagem.createGraphics();
  backBuffer = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);//Cree le buffer image
  backBuffer2 = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);//Cree le buffer image
 
 }
 public void menu(){
	 Fenetre menuInicial= new Fenetre(W,H);
	 
 }
 //---------------------------------------------------------------------------------------------------
 //Cela est le run
 // il a une loop infint
 public void run() {
	 Fenetre menuInicial= new Fenetre(W,H);
	 menuInicial.setButtons();
	 double contador=0;
	 while (acabou==false) {
		 if(menuInicial.getIniciar()){
			 if(this.um==0){
				 this.um++;
			 voiture1= new Voiture(W/2,H/8,2,Fenetre.nom.getPlayer1());
			 if(menuInicial.getPlayers()==2){
				 this.um++;
				 players=2;
				 voiture2= new Voiture(W/2,H/8-45,1,Fenetre.nom.getPlayer2());
			 }
			 }
			 if(contador==0){
				 menuInicial.dispose();
				inicializationJogo();
			}
			 // testeColisao();
			 atualization();
			 contador=contador + 0.03;
			 dessinerGraphiques(contador);

			 //testeColisao();
			 try {

				 Thread.sleep(1000/FPS); // cela fait le program attend pour 1000/FPS miliseconds

			 } catch (Exception e) {
				 System.out.println("Erreur");
			 }
		 }else{
			 menuInicial.desenharMenu();
		 }
		 
	 }if(acabou==true){
		 if(menuInicial.getPlayers()==2){
		 HallOfFame hall=new HallOfFame(2,voiture1.getName(),(int)contador,voiture2.getName(),(int)contador);}
		 else{
			 HallOfFame hall=new HallOfFame(1,voiture1.getName(),(int)contador, "",0);
		 }
		
	 }
 }
 public void setHall(){
	 ArrayList<Object> resultado =new ArrayList();
	 resultado.add("Player 1" );
	 resultado.add(""+this.time);
	 //HallOfFame.addResultado(resultado);
	 
 }
 //===================================================================================================================
 public static void main(String[] args) {
	 JeuControleur game = new JeuControleur();//CRIAMOS UM OBJETO A PARTIR DESSA PROPRIA CLASSE
	 game.run();//CHAMAMOS O METODO RUN(), O MÉTODO RUN() EXECUTA O INICIALIZAR(), ATUALIZAR() E DESENHARGRAFICOS()
 }//==================================================================================================================

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
	 if( voiture1.getCx()<=(largura+voiture1.getW()/20)&&voiture1.getCx()>=(largura-voiture1.getW()/20)&&voiture1.getCx()<=altura){
		 if(voiture1.getLap()<=3){
			 voiture1.umaVolta();
		 }else {
			 acabou=true;
		 }
	 }

 for (int i=0;i<4;i++){
	if (acabou==true){
		voiture1.setVitesse(0);
	}else{
	 if (horizontalTrack[i].pertence(voiture1.getX(), voiture1.getY())){
		 if (horizontalTrack[i].testeColisao(voiture1.getCx(),voiture1.getCy())){
				 voiture1.moveVoiture(-10);
				 voiture1.setVitesse(0);
				 break; }
	 }
	 if (verticalTrack[i].pertence(voiture1.getX(),voiture1.getY())){	 
		 if ((verticalTrack[i]).testeColisao(voiture1.getCx(),voiture1.getCy())){
			 	voiture1.moveVoiture(-10);
			 	voiture1.setVitesse(0);
					 break; }
				 } 
	 if (curveTrack[i].pertence(voiture1.getX(),voiture1.getY())){
		 if (curveTrack[i].testeColisao(voiture1.getCx(),voiture1.getCy())){			 
			voiture1.moveVoiture(-10);	
			voiture1.setVitesse(0);
			 break; }	
	 }
				 }
	 
	 //voiture1.acellerate();
	if(players==2){

		 if( voiture2.getCx()<=(largura+voiture2.getW()/20)&&voiture2.getCx()>=(largura-voiture2.getW()/20)&&voiture2.getCx()<=altura){
			 if(voiture2.getLap()<=3){
				 voiture2.umaVolta();
			 }else {
				 acabou=true;
			 }
		 }

	 for (int j=0;j<4;j++){
		if (acabou==true){
			voiture2.setVitesse(0);
		}else{
		 if (horizontalTrack[j].pertence(voiture2.getX(), voiture2.getY())){
			 if (horizontalTrack[j].testeColisao(voiture2.getCx(),voiture2.getCy())){
					 voiture2.moveVoiture(-10);
					 voiture2.setVitesse(0);
					 break; }
		 }
		 if (verticalTrack[j].pertence(voiture2.getX(),voiture2.getY())){	 
			 if ((verticalTrack[j]).testeColisao(voiture2.getCx(),voiture2.getCy())){
				 	voiture2.moveVoiture(-10);
				 	voiture2.setVitesse(0);
						 break; }
					 } 
		 if (curveTrack[j].pertence(voiture2.getX(),voiture2.getY())){
			 if (curveTrack[j].testeColisao(voiture2.getCx(),voiture2.getCy())){			 
				voiture2.moveVoiture(-10);	
				voiture2.setVitesse(0);
				 break; }	
		 }
					 }
		 
		 //voiture1.acellerate();
		
		 voiture2.moveVoiture(1);}
	}
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

	if(players==2){
		if((e.getKeyCode() == e.VK_W)&&colisao){
			   voiture2.acellerate();
			   atualization();
			   
			  }
		 if((e.getKeyCode() == e.VK_S)&&colisao){
			   voiture2.frenage();		   
			   atualization();
			  }
		if(e.getKeyCode() == e.VK_A){
				voiture2.turnRight();
				atualization();
				//xvoiture2-= 10;
				
			  }
		if(e.getKeyCode() == e.VK_D){
				voiture2.turnLeft();
				atualization();
				//xVoiture1+= 10;
				
			  }
	}
			
}



public void keyReleased(KeyEvent e) {
	
	
}



public void keyTyped(KeyEvent e) {
	
	
}

 
}