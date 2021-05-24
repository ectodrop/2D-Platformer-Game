import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Moves and then waits over and over
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Saw extends SmoothMover
{
    /**
     * Act - do whatever the Saw wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed;
    int steps = 0;
    int delay;
    int maxSteps;
    public Saw(int maxSteps, int speed){
        setImage("saw.png");
        this.speed = speed;
        this.maxSteps = maxSteps;//the range that the saw can move between
    }
    public void act() 
    {
        // Add your action code here.
        steps++;
        if(steps >= maxSteps){
            speed*=-1;
            if (delay < 30) {// waits for 30 act cycles before continuing to move
                delay++;
                return;
            } 
            else {
                delay = 0;
                steps = 0;
            }
        }
        setLocation(getX()+speed, getY());
    }    
}
