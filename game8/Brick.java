import greenfoot.*;

public class Brick extends Actor
{
    public Brick()
    {
        // 50x50の画像を生成
        GreenfootImage image = new GreenfootImage(50, 50);
        
        // レンガの色（茶色っぽい色）
        image.setColor(new Color(150, 75, 0)); 
        image.fill(); // 全体を塗りつぶす
        
        // 枠線を少し明るくしてレンガっぽく見せる（装飾）
        image.setColor(new Color(180, 100, 20));
        image.drawRect(0, 0, 49, 49);
        
        setImage(image);
    }
    
    public void act()
    {
        // 壁なので動かない
    }
}