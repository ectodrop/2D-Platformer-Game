import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Platform obeys gravity and moves if player pushes against it
 * will also block projectiles
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveablePlatform extends Platform
{
    /**
     * Act - do whatever the MoveablePlatform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int speed = 0;
    int offset = 5;
    private int myVelocity = 0;
    public MoveablePlatform(){
        setImage("pushable.png");
    }
    public void act() 
    {
        // Add your action code here.
        fall();
        if(hittingSide(Platform.class)){
            speed = 0;
        }
        setLocation(getX() + speed, getY() + myVelocity);
        
        speed = 0;
    }    
    public int getVelocity(){
        return myVelocity;
    }
    public Actor returnTouchingBelow(Class c){
        int xGround = getImage().getWidth();
        int yGround = getImage().getHeight();
        Actor a = null;
        if(getOneObjectAtOffset(0, yGround/2,c)!= null){  //checking left and bottom right of image
            a = (Actor)getOneObjectAtOffset(0, yGround/2,c);
        }
        if(getOneObjectAtOffset(xGround/2, yGround/2,c)!= null){  //checking left and bottom right of image
            a = (Actor)getOneObjectAtOffset(xGround/2, yGround/2,c);
        }
        if(getOneObjectAtOffset(xGround/-2, yGround/2,c)!= null){  //checking left and bottom right of image
            a = (Actor)getOneObjectAtOffset(xGround/-2, yGround/2,c);
        }
        return a;
    }
    public boolean touchingBelow(Class c){
        boolean onGround = false;
        int xGround = getImage().getWidth();
        int yGround = getImage().getHeight();
        
        if(getOneObjectAtOffset(xGround/-2+1, yGround/2,c)!= null ||
           getOneObjectAtOffset(xGround/2-1, yGround/2,c) != null){  //checking left and bottom right of image
            onGround = true;
        }
        return onGround;
    }
    public boolean hittingRight(Class c){
        boolean hitRight = false;
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        
        for(int i = y/2-1; i > -y/2; i--){
            if(getOneObjectAtOffset(x/2 + offset, i, c) != null){
                hitRight = true;
                break;
            }
        }   
        /*if(getOneObjectAtOffset(x/2 + offset,y/2 - offset-10, c) != null || 
        getOneObjectAtOffset(x/2 + offset,y/-2 + offset+10, c) != null ||
        getOneObjectAtOffset(x/2 + offset,0 , c) != null){
            hitRight = true;//hitting right side
        }*/
        return hitRight; //hitting nothing
    }
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
        /*if(getOneObjectAtOffset(x/-2 -offset, y/2 -offset-10, c) != null || 
        getOneObjectAtOffset(x/-2 -offset, y/-2 +offset+10, c) != null ||
        getOneObjectAtOffset(x/-2 -offset, 0, c) != null){
            
            hitLeft = true;//hitting left side
        }*/
        return hitLeft; //hitting nothing
    }
    public boolean hittingSide(Class c){
        return hittingRight(c) || hittingLeft(c);    
    }
    public void fall(){
        
        if(touchingBelow(Platform.class)){
            myVelocity = 0;
            while(touchingBelow(Platform.class)){
                setLocation(getX(), getY() -1);//push player out of ground if in ground
            }
            setLocation(getX(), getY() +1);
        }
        else if(myVelocity <= 20){
            myVelocity ++;//acceleration
        }
        
        
    }
}
