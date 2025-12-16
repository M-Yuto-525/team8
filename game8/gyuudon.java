import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class gyuudon extends Actor {

    public gyuudon() {
        GreenfootImage img = new GreenfootImage("牛丼.png");
        img.scale(100, 100);
        setImage(img);
    }

    public void act() {
        // foodman に触れていたら回収
        if (isTouching(foodman.class)) {
            getWorld().removeObject(this); // 自分（gyuudon）を消す
        }
    }
}
