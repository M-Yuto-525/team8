import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class gyuudon extends Actor {

    public gyuudon() {
        GreenfootImage img = new GreenfootImage("牛丼.png");

        img.scale(50, 50);


        // 35 × 35 に縮小
        img.scale(45, 45);

        setImage(img);
    }

    public void act() {
        // foodman に触れていたら回収
        if (isTouching(foodman.class)) {
            getWorld().removeObject(this);
        }
    }
}
