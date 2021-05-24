import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Similar to MovingPlatform except it has no timer and instead
 * uses collision to tell when to turn ire
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovingFlexible extends Platform
{
    /**
     * Act - do whatever the MovingFlexible wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 2;
    Spikes spike;
    public MovingFlexible(Spikes s){
        spike = s;
    }
    public void act() 
    {
        // Add your action code here.
        if( hitAbove(Platform.class) || touchingBelow(Platform.class)){
            speed *=-1;
        }
        spike.setLocation(spike.getX(),spike.getY() + speed);
        setLocation(getX(), getY()+speed);
    }    
    
    public boolean hitAbove(Class c){
        boolean hitHead = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        
        if(getOneObjectAtOffset(x/2, y/-2 -20, c) != null ||
            getOneObjectAtOffset(x/-2, y/-2-20,c) != null){  //checks the top left and right of sprite
            hitHead = true;
        }
        return hitHead;
    }
    public boolean touchingBelow(Class c){
        boolean onGround = false;
        int xGround = getImage().getWidth();
        int yGround = getImage().getHeight();
        
        if(getOneObjectAtOffset(xGround/-2, yGround/2+20,c)!= null ||
           getOneObjectAtOffset(xGround/2, yGround/2+20,c) != null){  //checking left and bottom right of image
            onGround = true;
        }
        return onGround;
    }
}
