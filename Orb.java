import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Orb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Orb extends Attacks
{
    /**
     * Act - do whatever the Orb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Player player;
    int delay = 60;
    int lifeSpan = 300;//about 5 seconds in game
    int direction;
    public Orb(Player p){
        player = p;
        setImage("orb.png");
    }
    public void act() 
    {
        
        delay--;
        if(delay > 0){//orb will wait for 60 frames before it starts moving
            turnTowards(player.getX(), player.getY());
            return;
        }
        
        move(5);
        
        if(lifeSpan == 0 || isAtEdge()){
            getWorld().removeObject(this);
        }
    }
     private void moveAtAngle(double angle, int speed){
        double x,y;
        double radian = Math.toRadians(angle);
        x = 5*Math.cos(radian);
        y = 5*Math.sin(radian);
        setLocation((double)getX()+x,(double)getY()-y);
    }
}
