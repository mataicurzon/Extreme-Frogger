
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
public class ObjectHolder<E extends drawable>{

    /*
     *ObjectHolder is a generic LinkedList. It is unique in that it has a draw and update method that iterate through all of its elements and calls their respective draw/update methods.

     */

    public int length;
    public GenericNode<E> end;
    public ObjectHolder(){
	end = null;
	length=0;
    }
    
    public void append(E toAppend){
	this.length++;
	GenericNode<E> addition= new GenericNode<E> (toAppend);
	addition.next=end;
	end=addition;
    }
    
     public void update(){
	

	GenericNode<E> updating = this.end;
	for (int i=0; i<this.length; i++){
	    updating.elem.update();
	    updating=updating.next;

	}
     }

    public void draw(Graphics g, String biome){

	GenericNode<E> drawing=this.end;
	for (int i=0; i<this.length(); i++){
	    drawing.elem.draw(g, biome);
	    drawing=drawing.next;

	}
    }


	
    public void remove(int index){
	boolean done=false;
	GenericNode<E> check=end;
	if (index==length-1)
	    end=end.next;
	else{
	    int i=length-1;
	    while (!done){
		if (i-1==index){
		    check.next=check.next.next;
		    done=true;
		}
		check=check.next;
		i--;	    
	    }	    
	}
	length--;
    }

    public E locate(int index){//Returns the object at the specific index in the ObjectHolder
	boolean done=false;
	GenericNode<E> check=end;
	if (index==length-1)
	    return end.elem;
	else{
	    int i=length-1;
	    while (!done){
		if (i-1==index){
		    return check.next.elem;
		    
		}
		check=check.next;
		i--;	    
	    }	    
	}
	return null;//SHOULDN'T BE REACHABLE, BUT THE COMPILER NEEDED IT to work
    }

    
    public String toString(){//Not actually used in Frogger, but I had already written it for the lab
	String printing="";
	GenericNode<E> adding=end;
	while (adding!=null){
	    printing=adding.elem.toString()+" "+printing;
	    adding=adding.next;
	}
	return printing;
	
    }
    public int length(){
	return this.length;
    }
}


class GenericNode<E>{//This is a helper class for ObjectHolder. ObjectHolders are linked lists of GenericNodes
    public GenericNode<E> next;
    public E elem;
    
    public GenericNode(E elem){
	this.elem=elem;
    }
    public GenericNode(){
    }
}
