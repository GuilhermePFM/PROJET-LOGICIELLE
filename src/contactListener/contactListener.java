package contactListener;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class contactListener  {
	
	 Point[] MakePoly(BufferedImage spr,int d,int angle){

		//creates an outline of a transparent image, points are stored in an array
		//arg0 - BufferedImage source image 
		//arg1 - Int detail (lower = better)
		//arg2 - Int angle threshold in degrees (will remove points with angle differences below this level; 15 is a good value)
//		      making this larger will make the body faster but less accurate;


		    int w= spr.getWidth(null);  int h= spr.getHeight(null);

		    // increase array size from 255 if needed
		    int[] vertex_x=new int[255], vertex_y=new int[255], vertex_k=new int[255]; 

		    int numPoints=0, tx=0,ty=0,fy=-1,lx=0,ly=0; vertex_x[0]=0; vertex_y[0]=0; vertex_k[0]=1; 
		    for (tx=0;tx<w;tx+=d)  for (ty=0;ty<h;ty+=1)       if((spr.getRGB(tx,ty)>>24) != 0x00 ) 
		        {vertex_x[numPoints]=tx; vertex_y[numPoints]=h-ty; vertex_k[numPoints]=1; numPoints++; if (fy<0) fy=ty; lx=tx; ly=ty; break;  }      
		    for (ty=0;ty<h;ty+=d)  for (tx=w-1;tx>=0;tx-=1)    if((spr.getRGB(tx,ty)>>24)  != 0x00 && ty > ly)
		        {vertex_x[numPoints]=tx; vertex_y[numPoints]=h-ty; vertex_k[numPoints]=1; numPoints++; lx=tx; ly=ty; break;  }     
		    for (tx=w-1;tx>=0;tx-=d)  for (ty=h-1;ty>=0;ty-=1) if((spr.getRGB(tx,ty)>>24) != 0x00 && tx < lx)
		        {vertex_x[numPoints]=tx; vertex_y[numPoints]=h-ty; vertex_k[numPoints]=1; numPoints ++; lx=tx; ly=ty; break; }     
		    for (ty=h-1;ty>=0;ty-=d)  for (tx=0;tx<w;tx+=1)    if((spr.getRGB(tx,ty)>>24) != 0x00 && ty < ly && ty > fy)
		        {vertex_x[numPoints]=tx; vertex_y[numPoints]=h-ty; vertex_k[numPoints]=1; numPoints ++; lx=tx; ly=ty; break; }      
		    double ang1,ang2;       for (int i=0;i<numPoints-2;i++) {
		        ang1 = PointDirection(vertex_x[i],vertex_y[i], vertex_x[i+1],vertex_y[i+1]);
		        ang2 = PointDirection(vertex_x[i+1],vertex_y[i+1], vertex_x[i+2],vertex_y[i+2]);
		         if (Math.abs(ang1-ang2) <= angle)   vertex_k[i+1] = 0;         }
		    ang1 = PointDirection(vertex_x[numPoints-2],vertex_y[numPoints-2], vertex_x[numPoints-1],vertex_y[numPoints-1]);
		    ang2 = PointDirection(vertex_x[numPoints-1],vertex_y[numPoints-1], vertex_x[0],vertex_y[0]);
		     if (Math.abs(ang1-ang2) <= angle)      vertex_k[numPoints-1] = 0; 
		    ang1 = PointDirection(vertex_x[numPoints-1],vertex_y[numPoints-1], vertex_x[0],vertex_y[0]);
		    ang2 = PointDirection(vertex_x[0],vertex_y[0], vertex_x[1],vertex_y[1]);
		     if (Math.abs(ang1-ang2) <= angle)      vertex_k[0] = 0;
		     int n=0;for (int i=0;i<numPoints;i++)if(vertex_k[i]==1)n++;
		    Point[] poly= new Point[n]; n=0; for (int i=0;i<numPoints;i++) if (vertex_k[i]==1)
		    { poly[n]=new Point(); poly[n].x=vertex_x[i]; poly[n].y=h-vertex_y[i];n++;} return poly;
		}

		double PointDirection(double xfrom,double yfrom,double xto,double yto){
		    return  Math.atan2(yto-yfrom,xto-xfrom)*180/Math.PI ;
		}	   
	
}
