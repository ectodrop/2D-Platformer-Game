import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Holds a the integer for the next level
 * placed at the left and right edges of the levels
 * also hold the new respawn points and where the player will spawn
 */
public class Door extends Actor
{
    public int nextLevel;
    Color hidden = new Color (50, 200, 200, 255);
    GreenfootImage image;
    int newSpawnX;
    int newSpawnY;
    public Door(int next, int x, int y){
        nextLevel = next;
        image = new GreenfootImage(10,600);//used to help me place the doors correctly
        image.setColor(hidden);
        image.fill();
        setImage(image);
        image.setTransparency(0);
        newSpawnX = x;
        newSpawnY = y;
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
