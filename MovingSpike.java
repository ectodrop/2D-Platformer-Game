import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingSpike here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingSpike extends Spikes
{
    /**
     * Act - do whatever the MovingSpike wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int direction;
    int speed = 5;
    boolean up;
    public MovingSpike(int direction, boolean up){
        super(20,20,direction);
        setImage("movingSpike.png");
        getImage().scale(10,10);
        this.direction = direction;
        this.up = up;
    }
    public void act() 
    {
        // Add your action code here.
        if(up){
            setLocation(getX(),getY() + speed*direction);
        
        }
        else{
            setLocation(getX()+speed*direction,getY());
        }
        
        
        if(getOneIntersectingObject(Platform.class) != null || isAtEdge()){
            getWorld().removeObject(this);
        }
    }    
}
