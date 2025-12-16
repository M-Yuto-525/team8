import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Shoutengai extends World
{
    private static final int TILE_SIZE = 50;

    // 【変更点1】map配列をここで宣言して、placeActorsでも中身を見れるようにする
    private String[] map = {
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

    public Shoutengai()
    {    
        // 画面サイズ: 800x665
        super(800, 645, 1); 
        
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
        // ここにあった String[] map は上に移動しました
        createMap(map);
    }

    /**
     * 設計図を読み込んで壁を配置する処理
     */
    private void createMap(String[] map) {
        for (int y = 0; y < map.length; y++) {
            String row = map[y];
            for (int x = 0; x < row.length(); x++) {
                
                if (x >= row.length()) break; 
                
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
        addObject(new foodman(), 71, 500); 
        addObject(new tenshu1(), 100, 100);
        addObject(new tenshu2(), 500, 300);

        // --- ランダムアイテムの配置 ---

        // ご飯系クラスのリスト
        Class[] foods = {
            gyuudon.class,
            manganiku.class,
            syouyuramen.class,
            tyaahan.class
        };

        // 【変更点2】壁と重ならないように配置するロジック
        for (int i = 0; i < 5; i++) {
            Class foodClass = foods[(int)(Math.random() * foods.length)];

            int gridX = 0;
            int gridY = 0;
            
            // "通路('0')"が見つかるまでランダムに場所を選び直す
            while (true) {
                // マップの配列サイズに合わせてランダムなインデックス(0〜15など)を決める
                gridX = Greenfoot.getRandomNumber(map[0].length());
                gridY = Greenfoot.getRandomNumber(map.length);
                
                // 選んだ場所が '0' (通路) ならOKとしてループを抜ける
                // ※ charAtで文字を取得して判定
                if (map[gridY].charAt(gridX) == '0') {
                    break;
                }
            }

            // マス目のインデックスをピクセル座標に変換する
            // (マスの中心に置くために + TILE_SIZE / 2 をする)
            int x = gridX * TILE_SIZE + TILE_SIZE / 2;
            int y = gridY * TILE_SIZE + TILE_SIZE / 2;

            try {
                // インスタンス生成
                Actor food = (Actor) foodClass.newInstance();
                addObject(food, x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}