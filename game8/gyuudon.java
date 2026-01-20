import greenfoot.*;
public class gyuudon extends Actor {
    public gyuudon() {
        GreenfootImage img = new GreenfootImage("牛丼.png");
        img.scale(50, 50);
        img.scale(45, 45);
        setImage(img);
    }
    public void act() {
        if (isTouching(foodman.class)) {
            // ★音量調整付きで再生
            GreenfootSound eatSound = new GreenfootSound("eat.mp3");
            eatSound.setVolume(50);  // 0〜100で指定（50 = 半分の音量）
            eatSound.play();
            getWorld().removeObject(this);
        }
    }
}