import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    ImageHelper start = new ImageHelper(new GreenfootImage("Start", 48, Color.BLACK, null));

    ImageHelper easy = new ImageHelper(new GreenfootImage("Easy", 48, Color.BLACK, null));
    
    ImageHelper normal = new ImageHelper(new GreenfootImage("Normal", 48, Color.BLACK, null));
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 400, 1); 
        setBackground("background.png");
        getBackground().setFont(new Font(48));
        showText("Game", 400, 100);
        addObject(start, 400, 200);
        Greenfoot.start();
    }
    public void act(){
        if(Greenfoot.mouseClicked(start)){
            showText("Choose Difficulty",400, 250);
            addObject(easy, 300, 300);
            addObject(normal, 500, 300);
        }
        if(Greenfoot.mouseClicked(easy)){
            Greenfoot.setWorld(new MyWorld(true));
        }
        else if(Greenfoot.mouseClicked(normal)){
            Greenfoot.setWorld(new MyWorld(false));
        }
    }
}
