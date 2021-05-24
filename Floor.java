import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Distinguishes between regular platforms and platforms used as
 * the base floor for the player to stand on
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor extends Platform
{
    /**
     * Act - do whatever the Floor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage ground = new GreenfootImage("ground.png");
    Color brown = new Color(173,116,58);
    public Floor(){
        super();
        setImage(ground);
        
    }
    public Floor(String file, int length){
        super();
        setImage(file);
        getImage().scale(length,100);
    }
    public Floor(int length){
        this();
        getImage().scale(length,100);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
