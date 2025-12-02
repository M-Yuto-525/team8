import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class tenshu2 extends Actor
{
    public void act() 
    {
        // foodman（フードマン）にぶつかったか確認
        Actor actor = getOneIntersectingObject(foodman.class); 
        
        if (actor != null) {
            // ★ぶつかった場合の処理
            getWorld().showText("GAME OVER", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
            return;  // ぶつかったら行動を止める
        }

        // ★ランダムに回転（‐10度〜10度くらい）
        int randomTurn = Greenfoot.getRandomNumber(21) - 10; 
        turn(randomTurn);

        // ★前に進む
        move(4);
    }
}
