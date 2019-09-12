import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
public class Road extends Segment {

 

    
    int laneMarkerStart;//This is just part of the drawing. We need it to be randomized once, rather than every cycle, so it needs to be an instance variable.

    public Road(int yCoordinateTop){
	super();
	this.yCoordinateTop=yCoordinateTop;
	
	
	obstacles = new ObjectHolder<Obstacle>();
	Random rand = new Random();
	laneMarkerStart=rand.nextInt(80);//Start location of white lines when drawing lanes
	int leftOrRight=rand.nextInt(2);//0 means it drives to the right
	
	for (int i=0; i<150; i++){//This code adds 150 obstacles to the road, spacing them semi-randomly.
	    
	    int l = rand.nextInt(200);//Determines the initial locations of the first obstacles and prevents them from all lining up and moving in line with each other
	    int j = rand.nextInt(10);//Determines what type of obstacle

	    int k = rand.nextInt(50);//Determines spacing between obstacles
	    if (j>5){//There are essentially 4 options for obstacles. Each of the two types and each of two directions. All obstacles in one road are same direction, but everything else (including spacing) gets randomized on a per car basis.
		if (leftOrRight==0)
		    obstacles.append (new Sedan(Frogger.WIDTH-l-i*(k+300), yCoordinateTop, leftOrRight));
		else
		    obstacles.append (new Sedan(l+i*(k+300), yCoordinateTop, leftOrRight));
	    }
	    
	    else{
		if (leftOrRight==0)
		    obstacles.append (new Truck(Frogger.WIDTH-l-i*(k+300), yCoordinateTop, leftOrRight));
		else
		    obstacles.append (new Truck(l+i*(k+300), yCoordinateTop, leftOrRight));
	    }
	}
	
    }

    public void update(){
	obstacles.update();
    }
    
   
	

    public void draw(Graphics g, String biome){
	
	
	g.setColor(Color.BLACK);
	for (int i=0; i<Frogger.WIDTH; i+=4)
	    for (int j=yCoordinateTop; j<yCoordinateTop+50; j+=5)
		g.drawRect(i, j, 0, 0);
	
	g.setColor(Color.WHITE);
	for (int i= laneMarkerStart; i<Frogger.WIDTH; i+=80)
	    g.fillRect(i, yCoordinateTop, 25, 5);
	
	obstacles.draw(g, biome);
    }
	    
	    
    
	

}



    

