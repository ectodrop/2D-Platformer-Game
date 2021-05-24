import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * distinguishes regular doors from doors that lead to secret rooms
 * because secret doors are usually placed at the top or bottom while regular doors 
 * are placed at the end
 * 
 * similar to the door class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SecretDoor extends Actor
{
    /**
     * Act - do whatever the SecretDoor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int nextLevel;
    Color hidden = new Color (50, 200, 200, 255);
    GreenfootImage image;
    int newSpawnX;
    int newSpawnY;
    public SecretDoor(int next, int x, int y){
        nextLevel = next;
        image = new GreenfootImage(800,10);
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
