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
public class SafeArea extends Segment{


	public static BufferedImage cactus;
	public static BufferedImage snowman;
	public static ImageObserver observer;
	public int[] decorLocations;



    public SafeArea(int yCoordinateTop){
	super();
	this.yCoordinateTop=yCoordinateTop;
	decorLocations = new int[3];
	Random random=new Random();
	int j= random.nextInt(350);
	int space=0;
	for(int i=0; i<3; ++i){
		decorLocations[i]=random.nextInt(250)+space;
		space+=300;
	}
}
    public void update(){//This allows it to implement drawable and be held in an ObjectHolder
	
    }
    public void draw(Graphics g, String biome){
	Color grass = new Color (0, 50, 20);
	g.setColor(grass);
	if (biome.equals("Desert")){
		Color sand = new Color (223, 193, 99);
	    g.setColor(sand);
	}
	if (biome.equals("Tundra")){
	    Color tundra = new Color (216, 229, 242);
	    g.setColor(tundra);
	}
	g.fillRect(0, yCoordinateTop, Frogger.WIDTH, 50);
	g.setColor(Color.BLACK);
	for (int i=5; i<Frogger.WIDTH; i+=5)//This gives the are some texture
	    for (int j=yCoordinateTop; j<yCoordinateTop+50; j+=5)
		g.drawRect(i, j, 0, 0);
	
	Random rand=new Random();
	if (biome.equals("Desert")){
		for(int i=0; i<decorLocations.length; ++i){
			g.drawImage(cactus, decorLocations[i], yCoordinateTop, observer);
		}
	}
	if (biome.equals("Tundra")){
		for(int i=0; i<decorLocations.length; ++i)
			g.drawImage(snowman, decorLocations[i], yCoordinateTop, observer);
		}
	}
	
	public static void readImage(){
        try{
			snowman=ImageIO.read(new File("Snowman.png"));
			cactus=ImageIO.read(new File("Cactus.png"));
            
        }catch (IOException e){}
    }
}
