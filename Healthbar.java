
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * visual representation of player heath
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healthbar extends Actor
{
    /**
     * Act - do whatever the Scoreboard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage score = new GreenfootImage(400,100);
    GreenfootImage emptyPoint = new GreenfootImage("empty.png");
    GreenfootImage point = new GreenfootImage("Validation.png");
    boolean c1 = false, c2 = false, c3 = false;
    boolean easy;
    Player player;
    public Healthbar(Player p, boolean difficulty){
        emptyPoint.scale(50,50);
        point.scale(50,50);
        easy = difficulty;
        player = p;
        if(easy){
            score.drawImage(point, 0, 0);
        
            score.drawImage(point, 60, 0);
            
            score.drawImage(emptyPoint, 120, 0);
            
            score.drawImage(emptyPoint, 180, 0);
        }
        else{
            score.drawImage(point, 0, 0);
            
            score.drawImage(emptyPoint, 60, 0);
            
            score.drawImage(emptyPoint, 120, 0);
        }
        setImage(score);
    }
    public void act() 
    {
        // Add your action code here.
        updateHealth(player.health);
    }    
    
    /*
     * sets the visual representation of the health based on the parameters
     * e.g. if player has 2 health on easy mode
     * image will be 2 filled hearts with 2 empty hearts
     */
    public void updateHealth(int playerhealth){
        score.clear();
        if(playerhealth > 4){//player shouldn't have more than 4 health ever
            playerhealth = 4;
            player.health = 4;
        }
        if(easy){
            switch(playerhealth){
                case 0: score.drawImage(emptyPoint, 0, 0);
                score.drawImage(emptyPoint, 60, 0);
                score.drawImage(emptyPoint, 120, 0);
                score.drawImage(emptyPoint, 180, 0);
                break;
                case 1: score.drawImage(point, 0, 0);
                score.drawImage(emptyPoint, 60, 0);
                score.drawImage(emptyPoint, 120, 0);
                score.drawImage(emptyPoint, 180, 0);
                break;
                case 2: score.drawImage(point, 0, 0);
                score.drawImage(point, 60, 0);
                score.drawImage(emptyPoint, 120, 0);
                score.drawImage(emptyPoint, 180, 0);
                break;
                case 3: score.drawImage(point, 0, 0);
                score.drawImage(point, 60, 0);
                score.drawImage(point, 120, 0);
                score.drawImage(emptyPoint, 180, 0);
                break;
                case 4:score.drawImage(point, 0, 0);
                score.drawImage(point, 60, 0);
                score.drawImage(point, 120, 0);
                score.drawImage(point, 180, 0);
                break;
            }
        }else{
            switch(playerhealth){
                case 0: score.drawImage(emptyPoint, 0, 0);
                score.drawImage(emptyPoint, 60, 0);
                score.drawImage(emptyPoint, 120, 0);
                break;
                case 1: score.drawImage(point, 0, 0);
                score.drawImage(emptyPoint, 60, 0);
                score.drawImage(emptyPoint, 120, 0);
                break;
                case 2: score.drawImage(point, 0, 0);
                score.drawImage(point, 60, 0);
                score.drawImage(emptyPoint, 120, 0);
                break;
                case 3: score.drawImage(point, 0, 0);
                score.drawImage(point, 60, 0);
                score.drawImage(point, 120, 0);
                break;
                
            }
        }
    }
}
