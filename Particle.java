import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * visual effect for when the player dies
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Particle extends Actor
{
    /**
     * Act - do whatever the Particle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image = new GreenfootImage(20,20);
    int dir;
    int speed = 5;
    int t;
    public Particle(int direction){
        image.drawOval(0,0, 5, 5);
        setImage(image);
        dir = direction;
        setRotation(dir);
        t = 250;
    }
    public void act() 
    {
        // Add your action code here.
        move(speed);
        if(t > 0){
            image.setTransparency(t-=10);
        }
        if(t <= 0){
            getWorld().removeObject(this);
        }
    }    
}
