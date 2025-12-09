import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class manganiku here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class manganiku extends Actor
{
    /**
     * Act - do whatever the manganiku wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public manganiku() {
        GreenfootImage img = new GreenfootImage("漫画肉.png");

        // 好きな大きさに変更（例：100 × 100）
        img.scale(100, 100);

        // Actor に適用
        setImage(img);
    }   
}
