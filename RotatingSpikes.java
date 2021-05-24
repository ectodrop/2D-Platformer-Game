import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rotates the spike class
 */
public class RotatingSpikes extends Spikes
{
    /**
     * Act - do whatever the RotatingSpikes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int direction;
    public RotatingSpikes(int length, int direction){
        super(length, 0);
        
        if(length < 300){
            setImage("rotatingSpikes.png");
        }
        else{
            setImage("rotatingSpikesLong.png");// if the spike length is too long the spikes will stretch and look strange
        }
        getImage().scale(length, 20);
        this.direction = direction;
    }
    public RotatingSpikes(int length, int width, int direction){//here if I want to specify a certain height
        super(length, 0);
        
        if(length < 300){
            setImage("rotatingSpikes.png");
        }
        else{
            setImage("rotatingSpikesLong.png");
        }
        getImage().scale(length,width);
        this.direction = direction;
    }
    public void act() 
    {
        // Add your action code here.
        turn(direction);
    }    
}
