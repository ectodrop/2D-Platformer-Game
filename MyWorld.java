import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class MyWorld extends World
{

    /**
     * Game
     * 
     * use the the wasd keys to move and jump
     * 
     * select between easy or normal mode
     * normal: start out with 1 heart, max of 3
     * easy: start out with 2 hearts, max of 4
     * 
     * Jump to navigate each level
     * If you die, you get respawned at the beginning of each level
     * discover 2 difficult secret levels to max out your health
     * fight a boss at the end that will test your skill and reaction time
     * 
     * Note:
     * If you are having difficulty on a level there IS a toggleable godmode that can be activated
     * or deactivated by pressing 'i' on the keyboard
     * 
     * You can set the room by pressing 'r' and entering an integer between 0-8 & 11,12, fair
     * warning however, it is quite buggy and where you end up spawning may breaking the game
     * 
     * Credits:
     * youtube channel elektrikpulse61 for a helpful tutorial on the basics of platformer physics
     * 
     * Toby Fox, for the song megalovania in my boss fight
     * 
     * bugs:
     * likely due to some big files, game will occasionally freeze for 1 second and all movement seems to
     * be stopped, then it continues as normal, however, this is not majorly gamebreaking
     * 
     * On 1 platform the player can sometimes stick to the bottom of it when the platform
     * is going down, this is mostly a visual bug and doesn't provide much benefit or harm to the player
     */
    Player player;
    public int spawnX;
    public int spawnY;
    private int currLevel;
    boolean roomSwitched = false;
    Healthbar healthbar;
    boolean[] levelComplete = new boolean[15];
    SimpleTimer timer = new SimpleTimer();
    public MyWorld(boolean easymode)
    {    
        // Create a new world with 800x400 cells with a cell size of 1x1 pixels.
        super(800, 400, 1);
        
        player = new Player(this, easymode);
        healthbar = new Healthbar(player, easymode);
        addObject(healthbar, 250, 50);
        
        spawnX = 100;
        spawnY = 320;
        addObject(player, spawnX, spawnY);
        currLevel = 0;
        setLevel(currLevel);//sets the beginning level
        prepare();
        timer.mark();
        setPaintOrder(ImageHelper.class, Healthbar.class,
        Player.class, Shooter.class,MovingSpike.class, 
        Platform.class, Shockwave.class, Spikes.class);//set order of objects, e.g spikes will appear under platforms
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    String key;
    public void act(){
        if(roomSwitched){//don't infinitely restart a level
            setLevel(currLevel);
            roomSwitched = false;
        }
        key = Greenfoot.getKey();
        if(key != null && key.equals("r")){//set room manually: TESTING PURPOSES ONLY, BUGGY
            /*String level = "";
            int newLevel;
            try{
                newLevel = Integer.parseInt(Greenfoot.ask(level));
            }catch(Exception e){
                newLevel = currLevel;
                System.out.println("Enter a vaild integer from 0-8 & 11,12");
            }*/
            //currLevel++;
            setCurrLevel(7);
            setLevel(currLevel);
        }
        if(key != null && key.equals("i") && !player.invincible) player.invincible = true;
        else if(key != null && key.equals("i") && player.invincible) player.invincible = false;//make player invincible TESTING PURPOSES ONLY
    
    }
    /*
     * clears all objects on current level and replaces it with new level
     */
    private void setLevel(int c){
        clearLevel();
        switch(c){
            case 0: setLevel0();
            break;
            case 1: setLevel1();
            break;
            case 2: setLevel2();
            break;
            case 3: setLevel3();
            break;
            case 4: setLevel4();
            break;
            case 5: setLevel5();
            setLevelCompleted(4);//only here to move a box
            break;
            case 6: setLevel6();
            break;
            case 7: setLevel7();
            break;
            case 8: setLevel8();
            break;
            case 11: setSecretLevel1();
            spawnX = 670;
            spawnY = 300;
            break;
            case 12: setSecretLevel2();
            break;
        }
    }
    public void setCurrLevel(int newLevel){
        currLevel = newLevel;
    }
    public int getLevel(){
        return currLevel;
    }
    public void setLevelCompleted(int i){
        levelComplete[i] = true;
    }

    private void clearLevel(){//remove all objects to be replaced by next level
        this.removeObjects(this.getObjects(Platform.class));
        this.removeObjects(this.getObjects(Door.class));
        this.removeObjects(this.getObjects(Spikes.class));
        this.removeObjects(this.getObjects(Shooter.class));
        this.removeObjects(this.getObjects(MovingSpike.class));
        this.removeObjects(this.getObjects(SecretDoor.class));
        this.removeObjects(this.getObjects(Validation.class));
        this.removeObjects(this.getObjects(Saw.class));
        this.removeObjects(this.getObjects(Boss.class));
        this.removeObjects(this.getObjects(BossDead.class));
        this.removeObjects(this.getObjects(Attacks.class));
        this.removeObjects(this.getObjects(ImageHelper.class));
    }
    
   
    /*
     * Add objects to the world to create the levels
     */
    private void setLevel0()
    {
        //platforms
        //floor + cieling
        //walls
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        //left
        Door door = new Door(1, 50, 329);
        addObject(door,getWidth(),getHeight()-100); 
        
        Platform platform = new Platform();
        addObject(platform,266,341);
        Platform platform2 = new Platform(20, 100);
        addObject(platform2,556,299);
        
        Spikes spikes = new Spikes(50, 0);
        addObject(spikes,267,330);
        
        Spikes spikes2 = new Spikes(20, 20, 0);
        addObject(spikes2,556,250);
        
        ImageHelper instructions = new ImageHelper("instructions.png");
        addObject(instructions,200,184);
    }
    
    private void setLevel1(){
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        Platform platform = new Platform(100, 100);
        addObject(platform,534,333);
        Spikes spikes = new Spikes(300,0);
        addObject(spikes,396,350);
        FadingPlatform fadingPlatform = new FadingPlatform();
        addObject(fadingPlatform,370,278);
        
        
        Door door = new Door(2, 50, 329);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(0, 750, 329);
        addObject(door2,0,getHeight()-100);
    }
    private void setLevel2(){
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        //left
        Door door = new Door(3, 50, 329);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(1, 750, 329);
        addObject(door2,0,getHeight()-100);
        
        MovingPlatform movingPlatform = new MovingPlatform(50, 20, 120,-2, false);
        addObject(movingPlatform,350,250);
        MovingPlatform movingPlatform2 = new MovingPlatform(50, 20, 120, 2, false);
        addObject(movingPlatform2,450,250);
        Spikes spikes = new Spikes(500, 0);
        addObject(spikes,400,350);
        Shooter shooter = new Shooter(-1, true, 30);
        addObject(shooter,398,360);
    }
    private void setLevel3(){
        
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        Floor floor2 = new Floor("tile.png",800);
        addObject(floor2,getWidth()/2-85,0);
        //left
        Door door = new Door(4, 50, 329);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(2, 750, 329);
        addObject(door2,0,getHeight()-100);
        //secret
        SecretDoor secretdoor = new SecretDoor(11, 670, getHeight()-100);
        addObject(secretdoor,700,0);
        
        Spikes spikes = new Spikes(500,0);
        addObject(spikes,400,350);
        Spikes spikes2 = new Spikes(500,180);
        addObject(spikes2,400,50);
        
        Platform platform = new Platform();
        addObject(platform,755,120);
        Platform platform2 = new Platform(20,100);
        addObject(platform2,705,80);
        Platform platform3 = new Platform(20,100);
        addObject(platform3,800,0);
        
        Spikes spikes3 = new Spikes(100, 0);
        addObject(spikes3, 208, 297);
        MovingFlexible movingFlexible = new MovingFlexible(spikes3);
        addObject(movingFlexible,208,282);
        
        Spikes spikes4 = new Spikes(100, 0);
        addObject(spikes4, 382, 257);
        MovingFlexible movingFlexible2 = new MovingFlexible(spikes4);
        addObject(movingFlexible2,382,242);
        
        Spikes spikes5 = new Spikes(100, 0);
        addObject(spikes5, 581, 215);
        MovingFlexible movingFlexible3 = new MovingFlexible(spikes5);
        addObject(movingFlexible3,581,200);
        
        
        
        if(levelComplete[11]){
            floor2.setLocation(getWidth()/2, 0);
        }
    }
    private void setLevel4(){
        
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        
        Door door = new Door(5, 50, 329);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(3, 750, 329);
        addObject(door2,0,getHeight()-100);
        
        MoveablePlatform moveablePlatform = new MoveablePlatform();
        addObject(moveablePlatform,466,110);
        
        Platform platform2 = new Platform(300, 20);
        addObject(platform2,382,143);
        Spikes spikes2 = new Spikes(10, 20, 0);
        addObject(spikes2,400,125);
        Spikes spikes4 = new Spikes(10, 20, 0);
        addObject(spikes4,300,125);
        
        Spikes spikes = new Spikes(200, 0);
        addObject(spikes,600,350);
        

        
        Spikes spikes3 = new Spikes(80, 90);
        addObject(spikes3,119,205);
        
        Platform platform3 = new Platform(50, 20);
        addObject(platform3,130,243);
        Platform platform4 = new Platform(20, 100);
        addObject(platform4,109,203);
        Platform platform5 = new Platform(20, 20);
        addObject(platform5, 0,120);
        
        Platform platform6= new Platform(20, 200);
        addObject(platform6,704,103);
        
        Platform platform7= new Platform(20, 100);
        addObject(platform7,0,0);
        Spikes spikes5 = new Spikes(200, 270);
        addObject(spikes5,694,102);
        if(levelComplete[4]){
            moveablePlatform.setLocation(566, 324);
        }
    }
    
    
    private void setLevel5(){
        
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        
        Door door = new Door(6, 50, 329);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(4, 770, 329);
        addObject(door2,0,getHeight()-100);
        
        RotatingSpikes rotatingSpikes = new RotatingSpikes(500,40, 1);
        addObject(rotatingSpikes,378,173);
        rotatingSpikes.setLocation(433,182);
        Platform platform = new Platform();
        addObject(platform,335,283);
        Platform platform2 = new Platform();
        addObject(platform2,476,160);
        
        Spikes spikes = new Spikes(50, 0);
        addObject(spikes,361,270);
        Spikes spikes2 = new Spikes(10, 20, 270);
        addObject(spikes2,420,159);
    }
    
    
    
    
    private void setLevel6(){
        
        Floor floor = new Floor(350);
        addObject(floor,175,getHeight());
        Floor floor2 = new Floor(500);
        addObject(floor2,670,getHeight());
        
        
        Door door = new Door(7, 50, 120);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(5, 750, 125);
        addObject(door2,0,getHeight()-100);
        
        Platform platform4 = new Platform(50, 300);
        addObject(platform4,355,283);
        
        MovingPlatform movingPlatform = new MovingPlatform(30, 10, 100, -2,false);
        addObject(movingPlatform,280,162);
        MovingPlatform movingPlatform2 = new MovingPlatform(30 , 10, 100, -2,false);
        addObject(movingPlatform2,280,262);
        
        Spikes spikes = new Spikes(200, 270);
        addObject(spikes,330,234);
        
        Platform platform = new Platform(200, 200);
        addObject(platform,573,100);

        Platform platform2 = new Platform(200, 100);
        addObject(platform2,641,350);

        MoveablePlatform moveablePlatform = new MoveablePlatform();
        addObject(moveablePlatform,617,275);

        Platform platform3 = new Platform(100, 200);
        addObject(platform3,780,250);
        
        Platform platform5 = new Platform(20, 20);
        addObject(platform5,469,190);
        
        Spikes spikes2 = new Spikes(200, 270);
        addObject(spikes2,735,258);
        Shooter shooter = new Shooter(-1, false, 5);
        addObject(shooter,560,329);
        
        SecretDoor secretDoor = new SecretDoor(12, 400, 50);
        addObject(secretDoor,400,396);
        if(levelComplete[12]){
            floor2.setLocation(627,getHeight());
        }
    }
    
    
    
    
    private void setLevel7(){
        Platform platform = new Platform(100, 200);
        addObject(platform,0,250);
        
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        
        Door door = new Door(8, 100, 329);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(6, 750, 125);
        addObject(door2,0,getHeight()-100);
        
        ImageHelper bossHealth = new ImageHelper(new GreenfootImage("Boss Turns:" + 10, 24,Color.BLACK, null));
        addObject(bossHealth, 700, 380);
        
        Boss boss = new Boss(player, bossHealth);
        addObject(boss,700,230);
        spawnX = 33;
        spawnY = 131;
    }
    
    
    
    
    private void setLevel8(){
        
        Floor floor = new Floor();
        addObject(floor,getWidth()/2,getHeight());
        Floor floor2 = new Floor();
        addObject(floor2,getWidth()/2,0);
        Wall wall = new Wall();
        addObject(wall,0,174);
        Wall wall2 = new Wall();
        addObject(wall2,800,174);
        Door door = new Door(9, 50, 329);
        addObject(door,getWidth(),getHeight()-100);
        //right
        Door door2 = new Door(7, 750, 329);
        addObject(door2,0,getHeight()-100);
        
        HighscoreBoard hs = new HighscoreBoard(player.deaths, timer.millisElapsed());
        addObject(hs, 452, 323);
        
        GreenfootSound win = new GreenfootSound("win.wav");
        win.play();
    }
    
    
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        
    }
    
    
    private void setSecretLevel1(){
        Wall wall = new Wall();
        addObject(wall,0,174);
        
        Wall wall2 = new Wall();
        addObject(wall2,800,174);
        
        Floor floor = new Floor("tile.png",800);
        addObject(floor,300,getHeight());
        Floor floor2 = new Floor("tile.png",800);
        addObject(floor2,getWidth()/2,0);
        
        SecretDoor secretDoor = new SecretDoor(3, 752, 78);
        addObject(secretDoor,730,396);
        
        RotatingSpikes rotatingSpikes = new RotatingSpikes(100,20, -2);
        addObject(rotatingSpikes,250,188);
        RotatingSpikes rotatingSpikes2 = new RotatingSpikes(80,20,2);
        addObject(rotatingSpikes2,360,200);
        
        Platform platform3 = new Platform(20, 20);
        addObject(platform3,554,194);
        RotatingSpikes rotatingSpikes3 = new RotatingSpikes(80, -30);
        addObject(rotatingSpikes3,617,198);
        
        RotatingSpikes rotatingSpikes4 = new RotatingSpikes(80, -30);
        addObject(rotatingSpikes4,500,220);
        
        
        Spikes spikes = new Spikes(200, 0);
        addObject(spikes,560,245);
        
        
        Platform platform = new Platform(600, 20);
        addObject(platform,460,255);
        
        Shooter shooter = new Shooter(1, false, 60);
        addObject(shooter,40,330);
        
        Validation validation = new Validation(3, 752, 78);
        addObject(validation,718,222);
    }
    
    
    private void setSecretLevel2(){
        Wall wall = new Wall();
        addObject(wall,0,174);
        
        Wall wall2 = new Wall();
        addObject(wall2,800,174);
        Floor floor = new Floor("tile.png",800);
        addObject(floor,getWidth()/2,getHeight());
        Floor floor2 = new Floor("tile.png",400);
        addObject(floor2,175,0);
        Floor floor3 = new Floor("tile.png",350);
        addObject(floor3,592,0);
        SecretDoor secretDoor = new SecretDoor(6, 569, 271);
        addObject(secretDoor,403,2);
        //Gate gate = new Gate(button);
        //addObject(gate, 780, 330);

        Platform platform = new Platform(300, 20);
        addObject(platform,510,175);
        Platform platform2 = new Platform(200, 20);
        addObject(platform2,652,300);
        Platform platform3 = new Platform(20, 300);
        addObject(platform3,366,140);
        Saw saw = new Saw(15, 10);
        addObject(saw,500,175);
        Saw saw2 = new Saw(15, -10);
        addObject(saw2,520,350);

        //Platform platform4 = new Platform(200, 10);
        //addObject(platform4,150,229);
        MovingPlatform movingPlatform = new MovingPlatform(120, -2, true);
        addObject(movingPlatform,217,340);
        
        
        FadingPlatform fadingPlatform = new FadingPlatform();
        addObject(fadingPlatform,56,231);

        RotatingSpikes rotatingSpikes = new RotatingSpikes(100, 30);
        addObject(rotatingSpikes,208,194);

        Validation validation = new Validation(6, 50, 329);
        addObject(validation,327,113);
        RotatingSpikes rotatingSpikes2 = new RotatingSpikes(100, 30);
        addObject(rotatingSpikes2,318,206);
        Spikes spikes = new Spikes(50, 0);
        addObject(spikes,78,352);
        
        
    }
    
}
