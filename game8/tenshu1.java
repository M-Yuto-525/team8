import greenfoot.*;
import java.util.*;

public class tenshu1 extends Actor
{
    private static final int TILE_SIZE = 50;

    private int speed = 2;
    private int direction = -1; // 0=上,1=右,2=下,3=左
    private boolean initialized = false;

    public tenshu1()
    {
        GreenfootImage img = new GreenfootImage("店主.png");
        img.scale(TILE_SIZE, TILE_SIZE);
        setImage(img);
    }

    public void act()
    {
        Shoutengai world = (Shoutengai)getWorld();
        foodman player = world.getPlayer();
        if (player == null) return;

        if (!initialized) {
            snapToGrid();
            chooseDirection(world, player);
            initialized = true;
            return;
        }

        // タイル中央でのみ方向変更
        if (isAtCenter()) {
            chooseDirection(world, player);
        }

        moveInDirection(direction);
        turnToDirection(direction);

        if (isTouching(foodman.class)) {
            world.showText("GAME OVER",
                world.getWidth()/2,
                world.getHeight()/2);
            Greenfoot.stop();
        }
    }

    // ===== タイル中央判定 =====
    private boolean isAtCenter()
    {
        return getX() % TILE_SIZE == TILE_SIZE / 2
            && getY() % TILE_SIZE == TILE_SIZE / 2;
    }

    // ===== 方向選択 =====
    private void chooseDirection(Shoutengai world, foodman player)
    {
        List<Integer> dirs = new ArrayList<>();

        for (int d = 0; d < 4; d++) {
            if (canMove(world, d)) dirs.add(d);
        }

        if (dirs.isEmpty()) return;

        // 後退禁止（行き止まり以外）
        if (direction != -1 && dirs.size() > 1) {
            int back = (direction + 2) % 4;
            dirs.remove((Integer)back);
        }

        int bestDir = dirs.get(0);
        int bestDist = Integer.MAX_VALUE;

        for (int d : dirs) {
            int nx = getX();
            int ny = getY();

            switch (d) {
                case 0: ny -= TILE_SIZE; break;
                case 1: nx += TILE_SIZE; break;
                case 2: ny += TILE_SIZE; break;
                case 3: nx -= TILE_SIZE; break;
            }

            int dx = player.getX() - nx;
            int dy = player.getY() - ny;
            int dist = dx * dx + dy * dy;

            if (dist < bestDist) {
                bestDist = dist;
                bestDir = d;
            }
        }

        direction = bestDir;
    }

    // ===== map判定 =====
    private boolean canMove(Shoutengai world, int dir)
    {
        int gx = getX() / TILE_SIZE;
        int gy = getY() / TILE_SIZE;

        switch (dir) {
            case 0: gy--; break;
            case 1: gx++; break;
            case 2: gy++; break;
            case 3: gx--; break;
        }

        return world.isRoad(gx, gy);
    }

    // ===== 移動 =====
    private void moveInDirection(int dir)
    {
        switch (dir) {
            case 0: setLocation(getX(), getY()-speed); break;
            case 1: setLocation(getX()+speed, getY()); break;
            case 2: setLocation(getX(), getY()+speed); break;
            case 3: setLocation(getX()-speed, getY()); break;
        }
    }

    private void turnToDirection(int dir)
    {
        switch (dir) {
            case 0: setRotation(270); break;
            case 1: setRotation(0); break;
            case 2: setRotation(90); break;
            case 3: setRotation(180); break;
        }
    }

    private void snapToGrid()
    {
        int x = (getX()/TILE_SIZE)*TILE_SIZE + TILE_SIZE/2;
        int y = (getY()/TILE_SIZE)*TILE_SIZE + TILE_SIZE/2;
        setLocation(x, y);
    }
}
