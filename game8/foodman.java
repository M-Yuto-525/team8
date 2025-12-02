import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class A here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class foodman
 extends Actor
{
    /**
     * Act - do whatever the A wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int speed = 3;
    
    public void act() 
    {
        // Add your action code here.
        //test
        handleMovement();
    }    
    private void handleMovement()
    {
        int dx = 0;
        int dy = 0;
        
        if (Greenfoot.isKeyDown("left")) {
            dx -= speed;
        }
        if (Greenfoot.isKeyDown("right")) {
            dx += speed;
        }
        if (Greenfoot.isKeyDown("up")) {
            dy -= speed;
        }
        if (Greenfoot.isKeyDown("down")) {
            dy += speed;
        }
        
        // 画面端を越えないように移動
        setLocation(
            Math.max(0, Math.min(getX() + dx, getWorld().getWidth() - 1)),
            Math.max(0, Math.min(getY() + dy, getWorld().getHeight() - 1))
        );
    }
}
