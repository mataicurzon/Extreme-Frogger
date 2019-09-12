import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.awt.Image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.ImageObserver;
public class Enemy{
    public double x;
    public double y;
    public double xVelocity;
    public double yVelocity;
    public int size = 20;
    public boolean shot;
    public int health;
	public static double velocity=1.5;
	public static BufferedImage enemyDown;
	public static BufferedImage enemyUp;
	public ImageObserver observer;


    public Enemy(){
	Random rand = new Random();
	x=rand.nextInt(Frogger.WIDTH);
	y=rand.nextInt(200);
	health=10;//right now this equals the hardcoded power value (hardcoded in Frogger where the projectiles are genrated. That is just what is passed into the contructor.)
    }
    public Enemy(int x, int y){
	this.x=x;
	this.y=y;
	health=10;//right now this equals the hardcoded power value (hardcoded in Frogger where the projectiles are genrated)
    }

    public void update(int frogX, int frogY){

	//This method determines the angle direction the enemies need to go to catch the frog
	//It calculates the components of velocity in x and y directions and adds them to position

	double theta = Math.atan(   ((double) (frogY-y))   /   ((double) (frogX-x))  );
	if (frogX<x)
	  theta+=Math.PI;
	xVelocity =  (velocity*Math.cos(theta));
	yVelocity =  (velocity*Math.sin(theta));
	x+=xVelocity;
	y+=yVelocity;
    }

    public void draw(Graphics g){
	//Simply draws the enmemies if and only if they are still alive. It was easier to not draw the dead ones than to remove them from the game. They are all purged when a new world is created anyway
	if (!shot && yVelocity>=0){
	    g.drawImage(enemyDown, (int)x-size, (int)y-size, observer);
	   
	}
	if (!shot && yVelocity<0){
	    g.drawImage(enemyUp, (int)x-size, (int)y-size, observer);
	    
	}
    }
	
    public static void readImage(){//Reads in the images of the enemies
	try{
	    enemyDown=ImageIO.read(new File("enemyDown.png"));	    
	    enemyUp=ImageIO.read(new File("enemyUp.png"));
            
	}catch (IOException e){}
    }
    
}
