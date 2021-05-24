import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ImageHelper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageHelper extends Actor
{
    /**
     * Act - do whatever the ImageHelper wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image;
    public ImageHelper(String file){
        image = new GreenfootImage(file);
        setImage(image);
    }
    public ImageHelper(GreenfootImage imageName){
        setImage(imageName);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
