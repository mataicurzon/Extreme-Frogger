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


public class Frog {

    private int xCoordinate;
    private int yCoordinate;
    public int xVelocity;
    public final int sizeX=50;
    public final int sizeY=50;
    public BufferedImage still;//This is the basic frog
    public BufferedImage jumpUp;//The frog image to be applied when it jumps
    public BufferedImage jumpDown;
    public BufferedImage jumpLeft;
	public BufferedImage jumpRight;
	public BufferedImage fire;
	public BufferedImage ice;
    public ImageObserver observer;
    public int count;
    public Boolean left;
    public Boolean right;
    public Boolean up;
    public Boolean down;
    private int movementTimer;
    private final int defaultTimerValue = 400;
    public boolean frozen;
    public boolean burned;


    
    

    public Frog() {
	//Initialize the frog at the center of the bottom segment
	xCoordinate= Frogger.WIDTH/2-25;
	yCoordinate= Frogger.HEIGHT-sizeY;
	try{
	    still=ImageIO.read(new File("Frog.png"));//Read in the image of the frog and set it as the static variable. The image is included in the code files
	
	    jumpUp=ImageIO.read(new File("frogJumpUp.png"));
	    jumpDown=ImageIO.read(new File("frogJumpDown.png"));
	    jumpLeft=ImageIO.read(new File("frogJumpLeft.png"));
		jumpRight=ImageIO.read(new File("frogJumpRight.png"));
		fire=ImageIO.read(new File("Fire.png"));
		ice=ImageIO.read(new File("Ice.png"));
	}catch (IOException e){
	    System.out.println("Image file not found");
	    System.exit(0);
	}//you can't play without a frog

	movementTimer=defaultTimerValue;


    }


    public int getTime(){
	return movementTimer;
    }
    public void resetTimer(){
	movementTimer=defaultTimerValue;
    }
    public void tickTimer(){
	movementTimer--;
    }
    public void checkTimer(String biome){
	if (biome.equals("Desert")&&getTime()==0)
	    burned=true;
	if (biome.equals("Tundra")&&getTime()==0)
	    frozen=true;
    }
    public void drawMovementTimer(Graphics g, String biome){
	g.setColor(Color.RED);
	g.fillRect(30, 16, getTime()/3, 30);
	g.setColor(Color.BLACK);
	String i;
	g.drawString("Movement timer. Move quickly so you don't " + (i = biome.equals("Tundra") ? "freeze!" : "burn your feet!"), 30, 12);
    }
    


    
    
    public int getY(){
	return yCoordinate;
    }
    public int getX(){
	return xCoordinate;
    }



    public void jump(char c){
	
	/*////////////////////////////////////////////////////////////////
	 *This moves the frog in the direction given by wasd. If you leave the screeen, it pulls you back on
	 *
	 *////////////////////////////////////////////////////////////////
	resetTimer();
	switch (c){
	case 'w':
		yCoordinate -= 50;
		up=true;
		down=false;
		right=false;
		left=false;
		count=10;
	    break;
	case 's':
		yCoordinate += 50;
		down=true;
		right=false;
		left=false;
		up=false;
		count=10;
	    break;
	case 'a':
		xCoordinate -= 50;
		left=true;
		right=false;
		down=false;
		up=false;
		count=10;
	    break;
	case 'd':
		xCoordinate += 50;
		right=true;
		down=false;
		left=false;
		up=false;
		count=10;
	    break;
	default:
	    return;
	}
	
	if (xCoordinate==-sizeX)
	    xCoordinate=0;
	if (xCoordinate==Frogger.WIDTH)
	    xCoordinate=Frogger.WIDTH-sizeX;
	if(yCoordinate==Frogger.HEIGHT)
	    yCoordinate=Frogger.HEIGHT-sizeY;    
    }
    public void draw(Graphics g, String biome){
	//Obviously, this draws the frog
	drawTemp(g, biome);
	if(count>0 && up){
		g.drawImage(jumpUp, xCoordinate, yCoordinate, observer);
		count--;
	}
	else if(count>0 && down){
		g.drawImage(jumpDown, xCoordinate, yCoordinate, observer);
		count--;
	}
	else if(count>0 && right){
		g.drawImage(jumpRight, xCoordinate, yCoordinate, observer);
		count--;
	}
	else if(count>0 && left){
		g.drawImage(jumpLeft, xCoordinate, yCoordinate, observer);
		count--;
	}
	else{
		g.drawImage(still, xCoordinate, yCoordinate, observer);
		left=false;
		right=false;
		down=false;
		up=false;
		count--;
	}
	

    }
    private void drawTemp (Graphics g, String biome){
	
	if (biome.equals("Desert")){

	    if (movementTimer==0){
		g.setColor(Color.WHITE);
		g.drawString("You got burnt!", getX(), getY()+sizeY+18);
		if (getY()==Frogger.HEIGHT-sizeY){
		    g.drawString("You got burnt!", getX(), Frogger.HEIGHT-sizeY-8);
		}
		g.setColor(Color.RED);
		g.drawImage(fire, getX(), getY(), observer);
	    }
	    else if (movementTimer<=150){
		g.setColor(Color.RED);
		if (movementTimer%2==0){
			g.drawImage(fire, getX(), getY(), observer);
		}
		g.setColor(Color.WHITE);
		g.drawString("It's getting hot...", getX(), getY()+sizeY+18);
		if (getY()==Frogger.HEIGHT-sizeY){
		    g.drawString("It's getting hot...", getX(), Frogger.HEIGHT-sizeY-8);
		}
	    }
	    
	}
	if (biome.equals("Tundra")){

	    if (movementTimer==0){
		g.setColor(Color.WHITE);
		g.drawString("You froze!", getX(), getY()+sizeY+18);
		if (getY()==Frogger.HEIGHT-sizeY){
		    g.drawString("You froze!", getX(), Frogger.HEIGHT-sizeY-8);
		}
		g.setColor(Color.BLUE);
		g.drawImage(ice, getX(), getY(), observer);
	    }
	    else if (movementTimer<=150){
		g.setColor(Color.BLUE);
		if (movementTimer%2==0)
			g.drawImage(ice, getX(), getY(), observer);
		g.setColor(Color.WHITE);
		g.drawString("It's getting cold...", getX(), getY()+sizeY+18);
		if (getY()==Frogger.HEIGHT-sizeY){
		    g.drawString("It's getting cold...", getX(), Frogger.HEIGHT-sizeY-8);
		}
	    }
	    
	}
    }

}



