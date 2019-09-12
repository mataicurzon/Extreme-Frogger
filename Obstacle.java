import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.ImageObserver;
public abstract class Obstacle implements drawable{
    public int xPosition;
    public int yPosition;
    public int velocity;
    public int length;
    public ImageObserver observer;
    public int leftOrRight;//0 drives to the right
    public Obstacle(int x, int y){
	xPosition=x;
	yPosition = y;
	velocity = 1;
    }

    public void update(){
	xPosition -= velocity;

    }
    public abstract void draw(Graphics g, String biome);
    

}


class Truck extends Obstacle{
    public static BufferedImage truck;
    public static BufferedImage flippedTruck;
    
    
    public Truck(int x, int y, int a){
	super(x, y);
	this.length = 75;
	if (a==0)//Because of its usage in external classes, a will always be one or 0. This is just a way of determining which directions the vehicle drives
	    velocity*=-1;
	leftOrRight=a;
    
    
	
    }
    public void draw(Graphics g, String biome){
	if (leftOrRight==1)
	    g.drawImage(truck, xPosition, yPosition, observer);
	else
	    g.drawImage(flippedTruck, xPosition, yPosition, observer);

    }

    public static void readImage(){//Reads in the images of the Truck. This is called early in the main method of Frogger
        try{
            truck=ImageIO.read(new File("Truck.png"));
            
        }catch (IOException e){}
	try{
            flippedTruck=ImageIO.read(new File("flippedTruck.png"));
            
        }catch (IOException e){}
    }
    
}

class Sedan extends Obstacle{//This class will not be commented because it is essentially the same as Truck (found above). The only difference is its length and the specific images
    public static BufferedImage car;
    public static BufferedImage flippedCar;
    
    public Sedan(int x, int y, int a){
	super(x, y);
	this.length = 50;
	if (a==0)
	    velocity*=-1;
	leftOrRight=a;
    
    }
    public void draw(Graphics g, String biome){
	if (leftOrRight==1)
	    g.drawImage(car, xPosition, yPosition, observer);
	else
	    g.drawImage(flippedCar,xPosition,yPosition,observer);
	
    }

    public static void readImage(){
        try{
            car=ImageIO.read(new File("Car.png"));
            
        }catch (IOException e){}
	try{
            flippedCar=ImageIO.read(new File("flippedCar.png"));
            
        }catch (IOException e){}
    }
}
