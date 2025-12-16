import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class foodman extends Actor
{
    private int speed = 3;
    private String lastKeyPressed = "";

    // コンストラクタ（最初に1回だけ呼ばれる）
    public foodman() {
        GreenfootImage img = getImage();
        img.scale(30,30);
        setImage(img);
    }

    public void act() 
    {
        if (Greenfoot.isKeyDown("up"))    setLocation(getX(), getY() - speed);lastKeyPressed="up";
        if (Greenfoot.isKeyDown("down"))  setLocation(getX(), getY() + speed);lastKeyPressed="down";
        if (Greenfoot.isKeyDown("left"))  setLocation(getX() - speed, getY());lastKeyPressed="left";
        if (Greenfoot.isKeyDown("right")) setLocation(getX() + speed, getY());lastKeyPressed="right";
        if(isTouching(Brick.class)){
            if (Greenfoot.isKeyDown("up"))    setLocation(getX(), getY() + speed);
            if (Greenfoot.isKeyDown("down"))  setLocation(getX(), getY() - speed);
            if (Greenfoot.isKeyDown("left"))  setLocation(getX() + speed, getY());
            if (Greenfoot.isKeyDown("right")) setLocation(getX() - speed, getY());
        }
    }
    
}
