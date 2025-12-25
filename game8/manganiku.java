import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class manganiku extends Actor
{
    public manganiku() {
        GreenfootImage img = new GreenfootImage("漫画肉.png");

        img.scale(50, 50);

        img.scale(45, 45);

        setImage(img);
    }

    public void act() {
        // foodman に当たったら回収して消える
        if (isTouching(foodman.class)) {
            getWorld().removeObject(this);
        }
    }
}
