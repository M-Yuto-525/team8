import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class gyuudon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class gyuudon extends Actor {

    public gyuudon() {
        GreenfootImage img = new GreenfootImage("牛丼.png");

        // 好きな大きさに変更（例：100 × 100）
        img.scale(100, 100);

        // Actor に適用
        setImage(img);
    }
}
