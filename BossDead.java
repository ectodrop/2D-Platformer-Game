import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossDead here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossDead extends Actor
{
    /**
     * Act - do whatever the BossDead wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //animates the boss' death
    //can't do this in the boss class because if player hits the boss they take damage
    Animation explode = new Animation();
    Animation shake = new Animation();
    SimpleTimer timer = new SimpleTimer();
    public BossDead(){
        timer.mark();
    }
    public void act() 
    {
        // Add your action code here.
        setLocation(700,190);
        if(timer.millisElapsed() <= 2800){//make it so animation does not infinitely repeat
            animate(shake, 3,25, "bossShake");
        }
        else if(timer.millisElapsed() > 2800 && explode.counter < 33){
            animate(explode, 10, 33, "bossExplode");
        }else{
            setImage("bossExplode(33).gif");
        }
    }    
    public void animate(Animation a, int d, int c, String name){
        a.delay++;
        
        if(a.delay == d){
            setImage(name + "(" + a.counter +")" + ".gif");//cycles through each image
            a.counter++;
            a.delay = 0;
        }
        if(a.counter == c+1) a.counter = 0;
    }
}
