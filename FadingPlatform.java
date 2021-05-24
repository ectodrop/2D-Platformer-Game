import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Starts to disappear after player has made contact
 * with the top of the platform
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FadingPlatform extends Platform
{
    /**
     * Act - do whatever the FadingPlatform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int a = 250;
    boolean touched = false;
    public void act() 
    {
        // Add your action code here.
        if(hitAbove(Player.class)){
            touched = true;
        }
        if(touched){
            getImage().setTransparency(a-=10);
        }
        if(getImage().getTransparency() <= 0){
            getWorld().removeObject(this);
        }
    }    
    
    public boolean hitAbove(Class c){
        boolean hitHead = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        for(int i = x/2; i >= x/-2; i--){
            if(getOneObjectAtOffset(i, y/-2-5, c) != null){
                hitHead = true;
                break;
            }
        }
        return hitHead;
    }
}
