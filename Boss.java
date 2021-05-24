
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**Final and only boss
 * Generates a random number to decide attacks
 * Attacks were made by adding objects to the world for a set duration while also
 * playing an animation along with it to telegraph and make it easier for the
 * player to predict attacks and prepare
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends SmoothMover
{
    /**
     * 
     * 
     */
    double myVelocity = 0;
    int MAX_VELOCITY = 30;
    
    ImageHelper bossHealth;
    int attackDuration = 0;
    int attackNum;
    boolean attacking = true;
    Player player;
    int delay = 10;
    boolean jumpAttack;
    boolean shockAttack = false;
    boolean rainAttack = false;
    boolean shootAttack = false;
    boolean bulletHellAttack = false;
    Animation stomp = new Animation();
    Animation tantrum = new Animation();
    Animation idle = new Animation();
    Animation shoot = new Animation();
    GreenfootSound music = new GreenfootSound("bossMusic.mp3");
    GreenfootSound crush = new GreenfootSound("tantrum.wav");
    double dX;
    double dY;
    int previousAttack;
    Cap c;
    
    int energy = 10;//basically the lifespan of the boss, energy goes down after each attack
    public Boss(Player p, ImageHelper b){
        player = p;
        bossHealth = b;
        setImage("standingBoss.png");
    }
    public void act() 
    {
        // Add your action code here.
        if(energy > 0 && player.getY() > 160){//boss fight will only start if the player is below a certain y level
            music.play();
            if(player.died) music.stop();
            setLocation(getX(), getY()+myVelocity);
            fall();
            delay--;
            if(delay == 0){
                
                previousAttack = attackNum;
                attackNum = Greenfoot.getRandomNumber(4);
            }
            if(delay > 0){//waits between attacks
                animate(idle, 3, 31, "bossIdle");//go through the idle animation while the boss isn't attacking
                return;
            }
            chooseAttack();
            
            if(rainAttack){
                rain();
            }
            if(shockAttack){
                shock(); 
            }
            if(shootAttack){
                shoot();
            }
            if(bulletHellAttack){
                bulletHell();
            }
            updateEnergy();
        }else if(energy <= 0){
            music.stop();
            getWorld().addObject(new BossDead(), getX(),getY());
            getWorld().removeObject(this);
        }else{
            music.stop();
        }
    }    
    public void updateEnergy(){
        getWorld().removeObject(bossHealth);
        bossHealth = new ImageHelper(new GreenfootImage("Boss Turns:" + energy, 24,Color.BLACK, null));
        getWorld().addObject(bossHealth, 700, 380);
    }
    /*
     * generates a random number to choose an attack
     */
    public void chooseAttack(){
        while(attackNum == previousAttack){
            attackNum = Greenfoot.getRandomNumber(4);
            System.out.println(attackNum);
        }
        switch(attackNum){
            case 0: shootAttack = true;
            break;
            case 1: shockAttack = true;
            break;
            case 2: rainAttack = true;
            break;
            case 3: bulletHellAttack = true;
            break;
        }
    }
    /*
     *  Each attack function sets an attack duration at the start and decrements the
     *  time every act cycle it's called
     *  
     *  the timing of the attacks are based on intervals
     *  
     *  after attack is over everything is reset and ready for the next attack
     */
    public void rain(){
            if(attackDuration == 0){
                attackDuration = 480;
                
                setLocation(getX()+10, getY());
            }
            if(attackDuration > 0){
                attackDuration--;
            }
            
            animate(tantrum, 2, 13, "bossTantrum");
            crush.setVolume(90);
            crush.play();
            if(attackDuration%120 == 0){
               int spawnX = 0;
               int spawnChance = Greenfoot.getRandomNumber(15);
               int spawnChance2 = Greenfoot.getRandomNumber(15);
               while(spawnChance == spawnChance2){
                   spawnChance2 = Greenfoot.getRandomNumber(15);
               }
               for(int i = 0; i < 15; i++){
                   spawnX += 50;
                   if(spawnChance != i && spawnChance2 != i){//2 spikes will not appear allowing the player to avoid the attack
                       getWorld().addObject(new FallingHazard(), spawnX,50);
                       
                   }
                   
               }
            }
            
            if(attackDuration == 1){
                rainAttack = false;
                attackDuration = 0;
                delay = 180;
                setImage("standingBoss.png");
                setLocation(getX() -10, getY());
                energy--;
            }
    }
    /*
     * generates several objects that follow the player and move towards them
     */
    public void shoot(){
            if(attackDuration == 0){
                attackDuration = 240;
            }
            if(attackDuration > 0){
                attackDuration--;
            }
            animate(shoot, 3, 30, "bossShoot");
            
            if(attackDuration%10 == 0 && attackDuration < 120){
               int spawnX = Greenfoot.getRandomNumber(600)+100;
               int spawnY = Greenfoot.getRandomNumber(150)+50;
               getWorld().addObject(new Orb(player), spawnX, spawnY);
               
            }
            if(attackDuration == 1){
                shootAttack = false;
                attackDuration = 0;
                delay = 180;
                crush.stop();
                setImage("standingBoss.png");
                energy--;
            }   
        
    }
    /*
     * sends a shockwave that quickly speeds up from the energy of stamping the ground
     */
    public void shock(){
            if(attackDuration == 0){
                attackDuration = 276;
            }
            
            if(attackDuration > 0){
                attackDuration--;
            }
            animate(stomp, 3, 22, "bossStomp");
            
            if(stomp.counter == 22){
               getWorld().addObject(new Shockwave(), getX(),getY() + 80);
               if(crush.isPlaying()) crush.stop();
               crush.setVolume(100);
               crush.play();
               
            }
            
            if(attackDuration == 1){
                shockAttack = false;
                setImage("standingBoss.png");
                attackDuration = 0;
                delay = 180;
                stomp.counter = 0;
                energy--;
            }  
        
    }
    /*
     * releases many projectiles from a central point that the players will have to dodge
     */
    public void bulletHell(){
            
            if(attackDuration == 0){
                setLocation(getX()+12, getY());
                attackDuration = 600;
                setImage("Capless.png");
                c = new Cap();
                getWorld().addObject(c, getX()+4, getY() -44);
                dX = (getWorld().getWidth()/2 -c.getX())/100;
                dY = 0.4;
            }
            if(attackDuration > 0){
                attackDuration--;
            }
            if(attackDuration < 600 && attackDuration > 500){
                c.setLocation(c.getExactX() + dX, c.getExactY() + dY);
            }
            if(attackDuration < 400 && attackDuration%40 == 0){
                int random = Greenfoot.getRandomNumber(30)+30;
                for(int i = 0; i < 360/random; i++){
                    getWorld().addObject(new Bullet(i*random), c.getX(),c.getY());
                }
            }
            
            if(attackDuration == 1){
                bulletHellAttack = false;
                setImage("standingBoss.png");
                attackDuration = 0;
                delay = 180;
                getWorld().removeObject(c);
                setLocation(getX()-12, getY());
                energy--;
            }  
        
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
        if(a.counter == c+1) a.counter = 0;
    }
    public void fall(){
        
        if(touchingBelow(Platform.class)){
            myVelocity = 0;
            while(touchingBelow(Platform.class)){
                setLocation(getX(), getY() -1);//push player out of ground if in ground
            }
            setLocation(getX(), getY() +1);
        }
        else if(myVelocity <= MAX_VELOCITY){
            myVelocity += 0.5;//acceleration
        }
        
    }
    public boolean touchingBelow(Class c){
        boolean onGround = false;
        int xGround = getImage().getWidth();
        int yGround = getImage().getHeight();
        
        if(getOneObjectAtOffset(xGround/-2, yGround/2-4,c)!= null ||
           getOneObjectAtOffset(xGround/2, yGround/2-4,c) != null){  //checking left and bottom right of image
            onGround = true;
        }
        return onGround;
    }
    
    
    
    
}
