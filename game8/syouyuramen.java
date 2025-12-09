import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class syouyuramen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class syouyuramen extends Actor
{
    /**
     * Act - do whatever the syouyuramen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public syouyuramen() {
        GreenfootImage img = new GreenfootImage("醤油ラーメン.png");

        // 好きな大きさに変更（例：100 × 100）
        img.scale(100, 100);

        // Actor に適用
        setImage(img);
    }   
}
