package jeuControleur;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class JeuControleur extends JFrame {
 
 BufferedImage backBuffer; //cela est le buffer
 int FPS = 30;    // actualization de la fenetre 
 int W = 500;   
 int H = 500;   
 ImageIcon voiture = new ImageIcon("src/jeuControleur/ferrari.png");
 ImageIcon planFond = new ImageIcon("src/jeuControleur/rue.png");

 public void atualization() {
 
 }
 

 public void dessinerGraphiques() 
 {//--------------------------------------------------------------------
  Graphics dessinerBuffer = getGraphics();
  Graphics chargerBackBuffer = backBuffer.getGraphics();

//Ici on mettre les dessins sur la fenetre
  
  chargerBackBuffer.drawImage(planFond.getImage(),0, 0, W,H, this);
  chargerBackBuffer.drawImage(voiture.getImage(), 200, 200,this);
  
  //QUI VAMOS ROTACIONAR A IMAGEM EM 45º
  //PARA ISSO VAMOS USAR OUTRA VARIAVEL DO TIPO Graphics2D
  //SE NÃO usarmos outra variável tudo que tá no buffer será rotacionado também!
  
  Graphics2D chargerBackBuffer2 = (Graphics2D) backBuffer.getGraphics();//Cet une variable pour changer la position de la voiture
  //chargerBackBuffer2.translate((voiture.getIconWidth()/2)+300, (voiture.getIconHeight()/2)+300); 
  //chargerBackBuffer2.rotate(45*(Math.PI/180)); 
  //chargerBackBuffer2.translate(-((voiture.getIconWidth()/2)+300), -((voiture.getIconHeight()/2)+300));
  chargerBackBuffer.drawImage(voiture.getImage(),300,300,this); //desenha a imagem

  
  // Toujours au fin
  //Cela dessine le buffer dans la fenetre dans les coordenees (0,0)
  dessinerBuffer.drawImage(backBuffer, 0, 0, this);
 }//-----------------------------------------------------------------------
 
 //Cela cree la fenetre 
 public void inicialization() {
  setTitle("~~Super`s Print");
  setSize(W, H);
  setResizable(false);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  setLayout(null);
  setVisible(true);
  backBuffer = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);//Cree le buffer image
 }
 
 //Cela est le run
 // il a une loop infint
 public void run() {
  inicialization();
  while (true) {
   atualization();
   dessinerGraphiques();
    try {
     Thread.sleep(1000/FPS); // cela fait le program attend pour 1000/FPS miliseconds
    } catch (Exception e) {
     System.out.println("Thread interrompida!");
    }
  }
 }
 
 public static void main(String[] args) {
	 JeuControleur game = new JeuControleur();//CRIAMOS UM OBJETO A PARTIR DESSA PROPRIA CLASSE
	 game.run();//CHAMAMOS O METODO RUN(), O MÉTODO RUN() EXECUTA O INICIALIZAR(), ATUALIZAR() E DESENHARGRAFICOS()
 }
 
}