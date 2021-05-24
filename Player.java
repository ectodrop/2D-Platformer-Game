import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Player character user controls
 */
public class Player extends SmoothMover
{
    private GreenfootImage image;
    //constants
    public static double GRAVITY = 0.5;
    public static int MAX_VELOCITY = 10;
    public static int JUMP_HEIGHT = -8;
    //variables
    
    public boolean died = false;
    boolean canDoubleJump = false;
    boolean left = false;
    boolean enemyHit = false;
    boolean jumpButton = false;
    boolean jumped = false;
    private double myVelocity;
    private double xMove;
    private int offset;
    private int maxJumps = 1;
    private int jumps = 0;
    int respawnDelay;
    Animation walk = new Animation();//each animation has their own versions of the variables count and delay
    Animation idle = new Animation();
    MyWorld myworld;
    private int iCounter = 0;
    int maxHealth;
    int health;
    GreenfootSound damage = new GreenfootSound("hurt.wav");
    GreenfootSound jump = new GreenfootSound("jump.wav");
    private boolean hurt = false;
    private int blinkCounter = 0;
    SimpleTimer timer = new SimpleTimer();
    int deaths = 0;
    public Player(MyWorld w,  boolean easy){
        //image = new GreenfootImage();
        setImage("spriteIdleRight(1).gif");
        respawnDelay = 0;
        xMove =0;
        myVelocity = 0;
        offset = 5;
        myworld = w;
        if(easy) maxHealth = 2;
        else maxHealth = 1;
        health = maxHealth;
    }
    
    boolean invincible = false;
    
    
    public void act() 
    {
        //background.playLoop();
        fall();
        ifHurt();
        if(!invincible)checkHurt();
        if(!died)checkKeys();
        checkDoors();
        
        setLocation(getX() +xMove, getY() + myVelocity);
        
        
    }
    public void ifHurt(){//what to do if the player is hurt
        if(hurt && health != 0){
            invincible = true;//player will have a window of invincibility
            iCounter++;//counts down and resets invincibility
            blink();
            if(iCounter == 120){
                hurt = false;
                invincible = false;
                iCounter = 0;
            }
        }
        if(hurt && health == 0){
            died = true;
            
            invincible = true;
            if(iCounter == 0){
                for(int i =0; i < 7; i++){
                    getWorld().addObject(new Particle(i*60), getX(),getY());
                }
            }
            setLocation(myworld.spawnX,myworld.spawnY);
            iCounter++;
            getImage().setTransparency(0);
            xMove = 0;
            if(iCounter == 60){
                deaths++;
                myworld.roomSwitched = true;
                health = maxHealth;
                hurt = false;
                invincible = false;
                iCounter = 0;
                died = false;
                getImage().setTransparency(255);
            }
        }   
    }
    public void blink(){//makes the player invisible and visible again the player every 10 acts
        blinkCounter++;
        if(blinkCounter == 10){
            if(getImage().getTransparency() == 0)
                getImage().setTransparency(255);
            else{
                getImage().setTransparency(0);   
            }
            blinkCounter = 0;
        }
        if(blinkCounter == 20){
            blinkCounter = 0;
        }
    }
    /*
     * Checks for collision with the door class and obtains the next level values
     * from the door instance to change the level in the world
     */
    public void checkDoors(){
        if(getOneIntersectingObject(Door.class)!=null){
            
            Door d = (Door)getOneIntersectingObject(Door.class);
            myworld.setCurrLevel(d.nextLevel);//current level in world is set to the level of the corresponding door
            myworld.roomSwitched = true;
            
            //System.out.println(d.newSpawnX + " " + d.newSpawnY);
            setLocation(d.newSpawnX, getY());//player is teleported to the coordinates given by the door
            myworld.spawnX = d.newSpawnX;//respawn location is reset if the player dies
            myworld.spawnY = d.newSpawnY;
        }
        if(getOneIntersectingObject(SecretDoor.class)!=null){
            
            SecretDoor sd = (SecretDoor)getOneIntersectingObject(SecretDoor.class);
            myworld.setCurrLevel(sd.nextLevel);//current level in world is set to the level of the corresponding door
            myworld.roomSwitched = true;
            
            //System.out.println(d.newSpawnX + " " + d.newSpawnY);
            setLocation(sd.newSpawnX, sd.newSpawnY);
            myworld.spawnX = sd.newSpawnX;
            myworld.spawnY = sd.newSpawnY;
        }
        if(intersectingWith(Validation.class)){
            
            switch(myworld.getLevel()){
                case 11: myworld.setLevelCompleted(11);
                break;
                case 12: myworld.setLevelCompleted(12);
                break;
            }
            Validation v = (Validation)getOneIntersectingObject(Validation.class);
            myworld.setCurrLevel(v.nextLevel);//current level in world is set to the level of the corresponding door
            myworld.roomSwitched = true;
            //System.out.print  ln(d.newSpawnX + " " + d.newSpawnY);
            setLocation(v.newSpawnX, v.newSpawnY);
            myworld.spawnX = v.newSpawnX;
            myworld.spawnY = v.newSpawnY;
            
            maxHealth++;
            health = maxHealth;
        }
    }
    /*
     * checking for controls and player movement
     */
    public void checkKeys(){
        xMove = 0;
        if(Greenfoot.isKeyDown("w") && (touchingBelow(Platform.class) || canDoubleJump) && !jumped){
            jumped = true;
            jump();
            canDoubleJump = false;
            
        }

        if(Greenfoot.isKeyDown("a") && (!hittingLeft(Platform.class) || hittingLeft(MoveablePlatform.class) )){//left
            left = true;
            xMove-=3;
            if(hittingLeft(MoveablePlatform.class)){
                MoveablePlatform mp = (MoveablePlatform)returnHittingLeft(MoveablePlatform.class);
                if(!mp.hittingLeft(Platform.class) && mp.getVelocity() == 0){
                    xMove = -2;
                    mp.speed += xMove-1;
                }
                else{
                    xMove = 0;
                }
            }
        }
        if(Greenfoot.isKeyDown("d")&& (!hittingRight(Platform.class) || hittingRight(MoveablePlatform.class)) && !hittingRight(Boss.class)){//right
            left = false;
            xMove +=3;
            if(hittingRight(MoveablePlatform.class)){
                MoveablePlatform mp = (MoveablePlatform)returnHittingRight(MoveablePlatform.class);
                if(!mp.hittingRight(Platform.class) && mp.getVelocity() == 0){
                    xMove = 2;
                    mp.speed += xMove+1;
                }
                else{
                    xMove = 0;
                }
            }
        }
        
        
        if(xMove == 0){
            if(left){
                animate(idle, 12, 1, "spriteIdleLeft");
            }
            else{
                animate(idle, 12, 1, "spriteIdleRight");
            }
        }
        else{
            if(left){
                animate(walk, 3, 7, "spriteWalkingLeft");
            }
            else{
                animate(walk,3, 7, "spriteWalkingRight");
            }
        }
        if(returnTouchingBelow(MovingPlatform.class) != null){//player follows the moving platform if standing on it
            MovingPlatform m = (MovingPlatform)returnTouchingBelow(MovingPlatform.class);
            if(!m.vertical){
                xMove += m.speed; 
                
            }
            else myVelocity += m.speed;
            //Greenfoot.stop();
        }

        if(!Greenfoot.isKeyDown("w")) jumped = false; // can only double jump after letting go of up key
    
        
    }
    /*
     * sereies of statements to check if the player is touching hazards
     */
    public void checkHurt(){
        
        if(intersectingWith(Spikes.class) || intersectingWith(Saw.class) ||
        intersectingWith(Attacks.class) || intersectingWith(Boss.class)){//if the player
            health--;//decreases health
            hurt = true;//activates ifHurt() function
            damage.play();
        }
        if(hittingRight(MovingPlatform.class)){//hitting right
            setLocation(getX() -2, getY());
        }
        if(hittingLeft(MovingPlatform.class)){//hitting left
            setLocation(getX() +2, getY());
        }
    }
    
    
    public boolean intersectingWith(Class c){//simpler boolean version of getOneIntersectingObject
        if(getOneIntersectingObject(c) != null){
            return true;
        }
        return false;
    }
    
    /*
     * checking for all points on the botom of the player and returns true if any of those
     * points are intersecting with the class
     */
    public boolean touchingBelow(Class c){
        boolean onGround = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        
        for(int i = x/2-1; i > -x/2; i--){
            if(getOneObjectAtOffset(i, y/2, c) != null){
                onGround = true;
                break;
            }
        }   
        return onGround;
    }
    
    //similar to touchingBelow
    public Actor returnTouchingBelow(Class c){
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        Actor a = null;
        for(int i = x/2-1; i > -x/2; i--){
            if(getOneObjectAtOffset(i, y/2,c)!= null){  //checking left and bottom right of image
                return (Actor)getOneObjectAtOffset(i, y/2,c);
            }
        }
        return a;
    }
    
    //similar to touchingBelow
    public boolean hitAbove(Class c){
        boolean hitHead = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        
        if(getOneObjectAtOffset(x/2, y/-2 - offset, c) != null ||
            getOneObjectAtOffset(x/-2, y/-2 - offset,c) != null){  //checks the top left and right of sprite
            hitHead = true;
        }
        return hitHead;
    }
    
    //similar to touchingBelow
    public boolean hittingRight(Class c){
        boolean hitRight = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        
        for(int i = y/2-1; i > -y/2; i--){//cycles through all the points on one side to check if it is intersecting
            if(getOneObjectAtOffset(x/2 + offset, i, c) != null){
                hitRight = true;
                break;
            }
        }   
        return hitRight; //hitting nothing
    }
    
    //similar to touchingBelow
    public Actor returnHittingRight(Class c){
        boolean hitRight = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        
        for(int i = y/2-1; i > -y/2; i--){
            if(getOneObjectAtOffset(x/2 + offset, i, c) != null){
                return (Actor)getOneObjectAtOffset(x/2 + offset, i, c);
                //break;
            }
        }   
        return null; //hitting nothing
    }
    
    //similar to touchingBelow
    public Actor returnHittingLeft(Class c){
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        
        for(int i = y/2-1; i > -y/2; i--){
            if(getOneObjectAtOffset(x/-2 - offset, i, c) != null){
                
                return (Actor)getOneObjectAtOffset(x/-2 - offset, i, c);
                //break;
            }
        }   
        return null; //hitting nothing
    }
    
    //similar to touchingBelow
    public boolean hittingLeft(Class c){
        boolean hitLeft = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        for(int i = y/2-1; i > -y/2; i--){//loops from the top of the image to the bottom
            if(getOneObjectAtOffset(x/-2 - offset, i, c) != null){
                hitLeft = true;
                break;
            }
        }   
        return hitLeft; //hitting nothing
    }
    /*
     * takes an instance of the animation class and increments it's variables to cycle through
     * and delay frames of an animation
     */
    public void animate(Animation a, int d, int c, String name){
        a.delay++;
        
        if(a.delay == d){
            setImage(name + "(" + a.counter +")" + ".gif");//cycles through each image
            a.counter++;
            a.delay = 0;
        }
        if(a.counter == c+1) a.counter = 0;//resets the counter after upper limit is reached
    }
    
    public void fall(){
        
        if(touchingBelow(Platform.class)){
            myVelocity = 0;
            while(touchingBelow(Platform.class)){
                setLocation(getX(), getY() -1);//push player out of ground if in ground
            }
            setLocation(getX(), getY() +1);//make it so the player is touching ground
        }
        else if(myVelocity <= MAX_VELOCITY){
            myVelocity += GRAVITY;//acceleration
        }
        
        
        if(((hitAbove(Platform.class) || isAtEdge()) && myVelocity < 0)){
            while(hitAbove(Platform.class)){
                setLocation(getX(), getY() + 1);
            }
            myVelocity = 0;
        }
        
        canDoubleJump();
    }
    
    /*
     * conditons for double jumping
     */
    public void canDoubleJump(){
        
        if(touchingBelow(Platform.class)){
            jumps = 0;
            canDoubleJump = false;
        }
        if(!touchingBelow(Platform.class) && jumps <= 1){
            canDoubleJump = true;
        }
        if(jumped == false && myVelocity > 0 && jumps < 1){//makes it so if player walks off platform they can only air jump once
            timer.mark();//player has a window of 100ms to jump again and will act like the player has 2 jumps
        }
        if(timer.millisElapsed() <= 100){
            jumps = 1;
            canDoubleJump = true;
        }
        if(jumps > maxJumps){
            canDoubleJump = false;
        }
    }
    //set velocity to a negative velocity and gravity decelerates and makes it fall
    
    public void jump(){
        jumps++;
        setLocation(getX(), getY() - 1);//fixes a bug where you can jump 3 times when moving
    
        myVelocity = JUMP_HEIGHT;
        jump.setVolume(100);
        jump.play();
        
    }
}
