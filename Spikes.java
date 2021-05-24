import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Hurts the player if touched
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spikes extends Actor
{
    /**
     * Act - do whatever the Spikes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int width;
    int height;
    public Spikes(int width, int height, int rotation){
        setImage("Spike.png");
        this.width = width;
        this.height = height;
        
        turn(rotation);
        getImage().scale(width,height);
    }
    public Spikes(int width, int rotation){
        setImage("spikes.png");
        this.width = width;
        //this.height = 20;
        turn(rotation);
        getImage().scale(width,40);
        
    }
    public void act() 
    {
        // Add your action code here.
    }    
    
}
