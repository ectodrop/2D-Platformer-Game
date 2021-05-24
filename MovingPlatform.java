import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MovingPlatform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingPlatform extends Platform
{
    /**
     * Act - do whatever the MovingPlatform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    double speed;
    int steps;
    int height;
    int width;
    int maxSteps;
    boolean vertical;
    public MovingPlatform(int width, int height, int maxSteps, double initspeed, boolean vertical){
        
        super(width,height);
        speed = initspeed;
        steps = 0;
        this.height = height;
        this.width = width;
        this.maxSteps = maxSteps;
        this.vertical = vertical;
    }
    public MovingPlatform(int maxSteps, double initspeed, boolean vertical){
        
        super(100,20);
        speed = initspeed;
        steps = 0;
        this.height = height;
        this.width = width;
        this.maxSteps = maxSteps;
        this.vertical = vertical;
    }
    public void act() 
    {
        // Add your action code here.
        steps++;
        if(steps >= maxSteps ){
            speed*=-1;
            steps = 0;
        }
        else if(vertical){
            setLocation(getX(), getY() + speed);
        }
        else{
            setLocation(getX() + speed, getY());  
        }
    
    
}    
}
