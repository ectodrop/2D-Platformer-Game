import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Visual representating for the palyer to hit
 */
public class Platform extends SmoothMover
{
    public Platform(int length, int width){
        setImage("tile.png");
        getImage().scale(length, width);
    }
    public Platform(){
        setImage("tile.png");
        getImage().scale(100, 20);
    }
    public void act() 
    {
        // Add your action code here.
    }    
    public void setWidth(int width){
        getImage().scale(width,getImage().getHeight());
    }
    public void setHeight(int height){
        getImage().scale(getImage().getWidth(),height);
    }
}
