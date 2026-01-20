import greenfoot.*;

public class TitleScreen extends World
{
    public TitleScreen()
    {    
        // ゲーム画面と同じサイズ (800x645) でワールドを作成
        super(800, 645, 1); 
        
        // 背景画像をセット (imagesフォルダに入れたファイル名)
        setBackground("表紙.png");
    }

    public void act()
    {
        // スペースキーが押されたらゲーム(Shoutengai)を開始する
        if (Greenfoot.isKeyDown("space")) {
            GreenfootSound startSound = new GreenfootSound("start.mp3");
            startSound.setVolume(50);  // 0〜100で指定（50 = 半分の音量）
            startSound.play();
            Greenfoot.setWorld(new Shoutengai());
        }
    }
}