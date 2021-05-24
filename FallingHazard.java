import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Falling spikes always fall with some gaps so the player can fit through
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FallingHazard extends Attacks
{
    /**
     * Act - do whatever the FallingHazard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int delay = 60;
    public FallingHazard(){
        setImage("FallingSpike.png");
    }
    public void act() 
    {
        // Add your action code here.
        delay--;//waits in position to give player time to react
        if(delay > 0){
            return;
        }
        setLocation(getX(), getY()+8);
        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }    
}
