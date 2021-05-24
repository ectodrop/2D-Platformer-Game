import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Animation
{
    /**
     * Act - do whatever the Animations wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    /*
     * 
     * Helper class to make aniamtion easier,
     * holds variables used by a function contained in seperate classes called animate()
     */
    public int delay;//how many cycles between frames
    public int counter;//increments to loop through images to create animation
    public Animation(){
        this.delay = 0;
        this.counter = 0;
    }
}
