import java.awt.Graphics;
public interface drawable{
    /*
    *This is the interface implemented by ObjectHolder. 
    *It mainly exists because ObjectHolder has a draw and update method that requires its elements to have the same
    */

    public void draw(Graphics g, String biome);
    public void update();
}
