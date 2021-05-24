import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math; 
/**
 * Bullet shot by the boss during the bullet hell attack
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Attacks
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int direction;
    public Bullet(int direction){
        this.direction = direction;
        setRotation(direction);
        setImage("bullet.png");
    }
    public void act() 
    {
        // Add your action code here.
        move(5);
        if(isAtEdge()){
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
