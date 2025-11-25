
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shoutengai here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shoutengai extends World
{

    /**
     * Constructor for objects of class Shoutengai.
     * 
     */
    public Shoutengai()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 655, 1); 
        addObject(new foodman(), 300, 200);
        addObject(new tenshu1(), 100, 100);
        addObject(new tenshu2(), 500, 300);
    }
}
