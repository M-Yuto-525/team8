
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shoutengai here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shoutengai extends World {

    public Shoutengai() {    
        super(800, 655, 1);

        // ランダム座標用
        int A = 0;
        int B = 800;

        // ご飯系クラスのリスト
        Class[] foods = {
            gyuudon.class,
            manganiku.class,
            syouyuramen.class,
            tyaahan.class
        };

        // 人や店主
        addObject(new foodman(), 0, 500);
        addObject(new tenshu1(), 100, 100);
        addObject(new tenshu2(), 500, 300);

        // ご飯系をランダムに5つ置く例
        for (int i = 0; i < 5; i++) {
            int x = A + (int)(Math.random() * (B - A + 1));
            int y = A + (int)(Math.random() * (B - A + 1));

            // ランダムにクラスを選ぶ
            Class foodClass = foods[(int)(Math.random() * foods.length)];

            try {
                // インスタンス生成
                Actor food = (Actor) foodClass.newInstance();
                addObject(food, x, y);
            } catch (Exception e) {
                // newInstanceで失敗時の保険
                e.printStackTrace();
            }
        }
    }
}

