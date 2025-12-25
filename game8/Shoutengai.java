import greenfoot.*;

public class Shoutengai extends World
{
    private static final int TILE_SIZE = 50;

    // ===== ゲーム管理 =====
    private boolean gameEnd = false;
    private int initialFoodCount;
    private int maxScore = 0;

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
        initialFoodCount = getFoodCount();
    }

    public void act()
    {
        if (gameEnd) return;

        timer--;
        if (timer <= 0) {
            showGameOver();
            gameEnd = true;
            return;
        }

        int eaten = initialFoodCount - getFoodCount();
        int timeBonus = (timer / 60) * 2;
        int score = eaten * (100 + timeBonus);

        if (score > maxScore) {
            maxScore = score;
        }

        showStatus(maxScore);

        if (getFoodCount() == 0) {
            showGameClear(maxScore);
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
        addObject(new foodman(), 75, 500); 
        addObject(new tenshu1(), 75, 100);
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

    // ===== ★ 敵用：通路判定 =====
    public boolean isRoad(int gx, int gy)
    {
        if (gy < 0 || gy >= map.length) return false;
        if (gx < 0 || gx >= map[0].length()) return false;
        return map[gy].charAt(gx) == '0';
    }

    // ===== ★ プレイヤー取得 =====
    public foodman getPlayer()
    {
        if (getObjects(foodman.class).isEmpty()) return null;
        return getObjects(foodman.class).get(0);
    }

    // ===== 食べ物数 =====
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
        showText("TIME : " + (timer / 60), 80, 30);
        showText("SCORE : " + score, 700, 30);
    }

    private void showGameClear(int score)
    {
        showText("GAME CLEAR!", 400, 300);
        showText("SCORE : " + score, 400, 350);
        Greenfoot.stop();
    }

    private void showGameOver()
    {
        showText("GAME OVER", 400, 300);
        Greenfoot.stop();
    }
}
