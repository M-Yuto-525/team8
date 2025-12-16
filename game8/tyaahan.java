import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class tyaahan extends Actor
{
    public tyaahan() {
        GreenfootImage img = new GreenfootImage("チャーハン.png");
        img.scale(50, 50);
        setImage(img);
    }

    public void act() {
        // foodman に当たったら回収して消える
        if (isTouching(foodman.class)) {
            getWorld().removeObject(this);
        }
    }
}
