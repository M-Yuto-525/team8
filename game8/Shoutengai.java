import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Shoutengai extends World
{
    private static final int TILE_SIZE = 50;

    public Shoutengai()
    {    
        // 画面サイズ: 800x665
        super(800, 665, 1); 
        
  

        // 1. 壁（迷路）を作る
        makeWalls();
        
        // 2. 人やアイテムを配置する
        placeActors();
    }

    /**
     * 迷路（壁）を生成するメソッド
     */
    private void makeWalls()
    {
        // 商店街のマップ設計図 (1=レンガ, 0=通路)
        String[] map = {
            "1111111111111111", // 外壁
            "1000000110000001", 
            "1011110110111101", 
            "1010000000000101", 
            "1010111001110101", 
            "1000100000010001", 
            "1110101111010111", // 真ん中あたり
            "1000100000010001", 
            "1010111001110101", 
            "1010000000000101", 
            "1011110110111101", 
            "1000000110000001", 
            "1111111111111111"  // 外壁
        };

        createMap(map);
    }

    /**
     * 設計図を読み込んで壁を配置する処理
     */
    private void createMap(String[] map) {
        for (int y = 0; y < map.length; y++) {
            String row = map[y];
            for (int x = 0; x < row.length(); x++) {
                
                if (x >= row.length()) break; // エラー防止
                
                char tile = row.charAt(x);
                
                // '1' ならレンガ(Brick)を置く
                if (tile == '1') {
                    Brick brick = new Brick();
                    
                    int xPos = x * TILE_SIZE + TILE_SIZE / 2;
                    int yPos = y * TILE_SIZE + TILE_SIZE / 2;
                    
                    addObject(brick, xPos, yPos);
                }
            }
        }
    }

    /**
     * 人や食べ物を配置するメソッド
     */
    private void placeActors()
    {
        // --- 固定キャラの配置 ---
        addObject(new foodman(), 71, 500); // ※座標調整: 壁と被らないように少しずらしました
        addObject(new tenshu1(), 100, 100);
        addObject(new tenshu2(), 500, 300);

        // --- ランダムアイテムの配置 ---
        
        // ランダム座標用
        int A = 50;  // 壁の中に埋まらないよう、端っこ(0)ではなく50から
        int B = 750; // 端っこ(800)ではなく750まで

        // ご飯系クラスのリスト
        // ※注意: これらのクラスファイル(gyuudon.javaなど)がプロジェクト内に存在する必要があります
        Class[] foods = {
            gyuudon.class,
            manganiku.class,
            syouyuramen.class,
            tyaahan.class
        };

        // ご飯系をランダムに5つ置く
        for (int i = 0; i < 5; i++) {
            // ランダムにクラスを選ぶ
            Class foodClass = foods[(int)(Math.random() * foods.length)];

            // ランダムな座標を決める
            int x = A + (int)(Math.random() * (B - A + 1));
            int y = A + (int)(Math.random() * (600 - A + 1)); // 縦は少し狭めに調整

            try {
                // インスタンス生成
                Actor food = (Actor) foodClass.newInstance();
                addObject(food, x, y);
            } catch (Exception e) {
                // newInstanceで失敗時のエラー表示
                e.printStackTrace();
            }
        }
    }
}