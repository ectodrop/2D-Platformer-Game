import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shown at the end of the game
 * shows players total deaths and
 * total time played
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HighscoreBoard extends Actor
{
    /**
     * Act - do whatever the HighscoreBoard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int deadScore;
    int i = 0;
    GreenfootImage image = new GreenfootImage(400,400);
    GreenfootImage dead;
    GreenfootImage time;
    Font font = new Font(24);
    int minutes = 0;
    int seconds;
    public HighscoreBoard(int deaths, int totalTime){
        deadScore = deaths;
        formatTime(totalTime);
        //image.fill();
        
        dead = new GreenfootImage("Deaths: " + deaths,48, Color.BLACK, null);
        time = new GreenfootImage("Time: " + timeToString(),48, Color.BLACK, null);
        image.drawImage(dead, 0,100);
        image.drawImage(time, 0,0);
        setImage(image);
    }
    public void act() 
    {
        // Add your action code here.
        
        
    }    
    /*
     * calculates time so that time can be displayed in minutes and seconds
     */
    public void formatTime(int time){
        seconds = time/1000;
        if(seconds/60 > 0){
            minutes = seconds/60;
        }
        seconds -= minutes*60;
        
        
    }
    public String timeToString(){
        return minutes + " min " + seconds + "s";
    }
}
