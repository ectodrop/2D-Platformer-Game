import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shockwave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shockwave extends Attacks
{
    /**
     * Act - do whatever the Shockwave wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    double speed;
    public Shockwave(){
        setImage("Shockwave.png");
        speed = 5;
    }
    public void act() 
    {
        // Add your action code here.
        setLocation(getX() - speed, getY());
        speed *= 1.05;//starts slow and speeds up quickly
        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }    
}
