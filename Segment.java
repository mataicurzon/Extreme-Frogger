import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public abstract class Segment implements drawable{//Parent for roads and safeareas
    public final static int height=50;
    int yCoordinateTop;
    ObjectHolder<Obstacle> obstacles;
    //CONTAINER OF OBSTACLES
    



    public abstract void update();
    public abstract void draw(Graphics g, String biome);
    
}
