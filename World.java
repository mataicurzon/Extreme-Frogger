import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class World {

    
    public Frog frog;

    
    int height;
    int width;
    ObjectHolder<Segment> segments;
    String biome;
    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> enemies;
    public static boolean enemiesAllowed;
    ObjectHolder<Life> lives;
  

  
 
    public World(int initWidth, int initHeight){//This is kind of like a level
		width = initWidth;
		height = initHeight;
		frog = new Frog();
		segments = new ObjectHolder<Segment>();//Create the segment datastructure
		for (int i=0; i<15; i++){//Fill the data structure
		    if (i==0 || i==7 || i==14)//Add safeAreas
			segments.append(new SafeArea(Frogger.HEIGHT-(50+i*50)));
		    else segments.append (new Road(Frogger.HEIGHT-(50+i*50)));//Add the roads
	    
		}
		lives = new ObjectHolder<Life>();//These 3 lines are used for adding lives. For now, they're disabled, but we're leaving this in just in case somebody wants them later
		/*for (int i=0; i<2; i++)
	 		lives.append(new Life());*/
	    
		biome = createBiome();
		projectiles = new ArrayList<Projectile>();//Create projectiles data structure and enemies on the line below.
		enemies = new ArrayList<Enemy>();
		if (enemiesAllowed){//Create three enemies if they havn't been disabled
	    	for (int i=0; i<3; i++)
			enemies.add(new Enemy());
	}
	
	
 
    }
    
    public String createBiome(){
	Random rand = new Random();
	int numBiomes=4;
	String[] biomes = new String []{"Basic", "Tundra", "Marsh", "Desert"};
	return biomes[rand.nextInt(numBiomes)];
    }
    
    public void updateSegments(){
	segments.update();
    }
	    
	    
    
    public void updateProjectiles(){
	for(Projectile p: projectiles){
	    p.update();
	    // if (p.x>width || p.x<0 || p.y>height || p.y<0)
	    //	projectiles.remove(p);
	}
    }
    public  boolean updateEnemies(){//UPDATES AND ALSO RETURNS BOOLEAN VALUE OF WHETHER THEY CAUGHT THE FROG
	for (Enemy e:enemies){
	    e.update(frog.getX(), frog.getY());
	}
	for (Enemy e:enemies){
	    if (
		frog.getX()+50>e.x
		&& frog.getX()<e.x+e.size
		&&frog.getY()+50>e.y
		&& frog.getY()<e.y+e.size
		&& !e.shot//If the enemy is dead, it can't eat a frog
		)
		return true;
	}
	return false;
    }
    public void checkEnemiesShot(){//This checks to see if the projectiles have entered the hurt box for the enemies. If so, it sets the enemies to be dead and also created a new enemy for each one killed
	int numDead=0;
	for (Enemy e : enemies){
	    for (Projectile p : projectiles){
		if (
		    p.x+p.size >= e.x-e.size
		    && p.x-p.size<=e.x+e.size*2
		    && p.y+p.size >= e.y-e.size
		    && p.y-p.size<=e.y+e.size*2
		    )
		    {
		    e.health-=p.power;
		    if (e.health==0){
			e.shot=true;
			numDead++;
		    }
		    }
	    }
	}
	Random rand= new Random();
	for (int i=0; i<numDead; i++){
	    enemies.add(new Enemy(rand.nextInt(Frogger.WIDTH), rand.nextInt(50)));
	}
    }


    public void addMoreEnemies(){//This is activated from Frogger with a key command
	for (int i=0; i<3; i++)
	    enemies.add(new Enemy());
    }

    
    
    public void drawSegments(Graphics g, String biome){
	segments.draw(g, biome);	
    }

    public void drawLives (Graphics g, String s){
	lives.draw(g, s);
    }
    
}

