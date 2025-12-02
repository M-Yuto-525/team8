import greenfoot.*; 

public class tenshu2 extends Actor
{
    public void act() 
    {
        Actor actor = getOneIntersectingObject(foodman.class); 
        
        if (actor != null) {
            getWorld().showText("GAME OVER", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
            return;
        }

        int randomTurn = Greenfoot.getRandomNumber(21) - 10; 
        turn(randomTurn);
        
        move(4);
    }
}
