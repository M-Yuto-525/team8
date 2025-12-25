import greenfoot.*;

public class Shoutengai extends World
{
    private static final int TILE_SIZE = 50;

    // ===== ゲーム管理 =====
    private boolean gameEnd = false;
    private int initialFoodCount;

    // 制限時間（2分）
    private int timer = 120 * 60;

    // ===== マップ =====
    private String[] map = {
        "1111111111111111",
        "1000000110000001", 
        "1011110110111101", 
        "1010000000000101", 
        "1010111001110101", 
        "1000100000010001", 
        "1110101111010111",
        "1000100000010001", 
        "1010111001110101", 
        "1010000000000101", 
        "1011110110111101", 
        "1000000110000001", 
        "1111111111111111"
    };

    public Shoutengai()
    {    
        super(800, 645, 1);

        makeWalls();
        placeActors();

        // 初期の食べ物数を記録
        initialFoodCount = getFoodCount();
    }

    public void act()
    {
        if (gameEnd) return;

        // ===== タイマー =====
        timer--;

        if (timer <= 0) {
            showGameOver();
            gameEnd = true;
            return;
        }

        // ===== スコア計算 =====
        int eaten = initialFoodCount - getFoodCount();

        // 残り時間ボーナス（秒 × 2）
        int timeBonus = (timer / 60) * 2;

        // 1個100点＋時間ボーナス
        int score = eaten * (100 + timeBonus);

        showStatus(score);

        // ===== クリア判定 =====
        if (getFoodCount() == 0) {
            showGameClear(score);
            gameEnd = true;
        }
    }

    // ===== 壁生成 =====
    private void makeWalls()
    {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length(); x++) {
                if (map[y].charAt(x) == '1') {
                    addObject(
                        new Brick(),
                        x * TILE_SIZE + TILE_SIZE / 2,
                        y * TILE_SIZE + TILE_SIZE / 2
                    );
                }
            }
        }
    }

    // ===== キャラ・食べ物配置 =====
    private void placeActors()
    {

        // --- 固定キャラの配置 ---
        addObject(new foodman(), 71, 500); 
        addObject(new tenshu1(), 75, 100);

        addObject(new foodman(), 71, 500);
        addObject(new tenshu1(), 78, 100);
        addObject(new tenshu2(), 500, 300);

        Class[] foods = {
            gyuudon.class,
            manganiku.class,
            syouyuramen.class,
            tyaahan.class
        };

        for (int i = 0; i < 5; i++) {
            int gx, gy;
            while (true) {
                gx = Greenfoot.getRandomNumber(map[0].length());
                gy = Greenfoot.getRandomNumber(map.length);
                if (map[gy].charAt(gx) == '0') break;
            }

            try {
                Actor food = (Actor) foods[
                    Greenfoot.getRandomNumber(foods.length)
                ].newInstance();

                addObject(
                    food,
                    gx * TILE_SIZE + TILE_SIZE / 2,
                    gy * TILE_SIZE + TILE_SIZE / 2
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // ===== 食べ物数取得 =====
    private int getFoodCount()
    {
        return  getObjects(gyuudon.class).size()
              + getObjects(manganiku.class).size()
              + getObjects(syouyuramen.class).size()
              + getObjects(tyaahan.class).size();
    }

    // ===== 表示 =====
    private void showStatus(int score)
    {
        // 左上：TIME
        showText("TIME : " + (timer / 60), 80, 30);

        // 右上：SCORE
        showText("SCORE : " + score, 700, 30);
    }

    // ===== クリア =====
    private void showGameClear(int score)
    {
        showText("GAME CLEAR!", 400, 300);
        showText("SCORE : " + score, 400, 350);
        Greenfoot.stop();
    }

    // ===== ゲームオーバー =====
    private void showGameOver()
    {
        showText("GAME OVER", 400, 300);
        Greenfoot.stop();
    }
}
