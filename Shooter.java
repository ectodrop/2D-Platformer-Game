import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shoots a projectile in a certain direction,
 * at a certian speed and at a certain frequency
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shooter extends Actor
{
    /**
     * Act - do whatever the Shooter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int timer;
    boolean vertical = false;
    int direction = 0;
    int frequency;
    public Shooter(int dir, boolean ver, int freq){
        setImage("shooter.png");
        timer = 1;
        direction = dir;//movement speed
        vertical = ver; 
        frequency = freq;
    }
    public void act() 
    {
        // Add your action code here.
        timer--;//delays shooter until next timer is 0
        if(timer == 0){
            if(vertical){
               getWorld().addObject(new MovingSpike(direction, vertical), getX(), getY()+(30*direction));
            }else{
               getWorld().addObject(new MovingSpike(direction, vertical), getX() + (20*direction), getY());
            }
            timer = frequency;
        }
    }    
}
