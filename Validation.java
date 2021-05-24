import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Collectable that increases the player's max health
 * similar to the door class
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Validation extends Actor
{
    public int nextLevel;//when player collects the collectable they need to be spawned in the previous level
    int newSpawnX;
    int newSpawnY;
    public Validation(int next, int x, int y){
        nextLevel = next;
        newSpawnX = x;
        newSpawnY = y;
        getImage().scale(50,50);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
