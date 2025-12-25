import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class syouyuramen extends Actor
{
    public syouyuramen() {
        GreenfootImage img = new GreenfootImage("醤油ラーメン.png");

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
