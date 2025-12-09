import greenfoot.*;

/**
 * Write a description of class C here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class tenshu1 extends Actor
{
    private int speed = 2;

    // ★★ 画像サイズはコンストラクタで変更する ★★
    public tenshu1() {
        GreenfootImage img = new GreenfootImage("店主.png");
        img.scale(129, 175);
        setImage(img);
    }

    public void act() 
    {
        foodman protagonist = (foodman) getWorld().getObjects(foodman.class).get(0);
        
        if(protagonist != null)
        {
            int protagonistX = protagonist.getX();
            int protagonistY = protagonist.getY();
            
            turnTowards(protagonistX, protagonistY);
            move(speed);
            
            if (this.intersects(protagonist)) 
            {
                Greenfoot.stop(); 
                getWorld().showText("GAME OVER",
                    getWorld().getWidth() / 2,
                    getWorld().getHeight() / 2);
            }
        }
    }
}
