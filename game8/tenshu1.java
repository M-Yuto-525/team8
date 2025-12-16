import greenfoot.*;

public class tenshu1 extends Actor
{
    private int speed = 1;           // 移動速度
    private int direction = -1;      // 0=上,1=右,2=下,3=左, -1=初期未決定
    private boolean initialized = false; // 初期フレーム判定
    private static final int TILE_SIZE = 50;

    public tenshu1()
    {
        GreenfootImage img = new GreenfootImage("店主.png");
        img.scale(TILE_SIZE, TILE_SIZE);
        setImage(img);
    }

    public void act()
    {
        foodman protagonist = (foodman)getWorld().getObjects(foodman.class).get(0);
        if (protagonist == null) return;

        // 初回フレームは動かさず方向決定だけ
        if (!initialized) {
            initialized = true;
            direction = -1; // 初期方向未決定
            snapToGrid();   // 座標をグリッドに合わせて壁に埋まらないように
            return;
        }

        // 移動方向を選ぶ
        if (direction == -1 || !canMove(direction)) {
            chooseBestDirection(protagonist);
        }

        // 移動・向き更新
        moveInDirection(direction);
        turnToDirection(direction);

        // 接触でゲームオーバー
        if (isTouching(foodman.class)) {
            getWorld().showText("GAME OVER", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
    }

    private boolean canMove(int dir)
    {
        int nx = getX();
        int ny = getY();
        int margin = 2; // 壁判定の余裕

        switch (dir)
        {
            case 0: ny -= speed + margin; break; // 上
            case 1: nx += speed + margin; break; // 右
            case 2: ny += speed + margin; break; // 下
            case 3: nx -= speed + margin; break; // 左
        }
        return !isTouchingAt(nx, ny, Brick.class);
    }

    private boolean isTouchingAt(int x, int y, Class cls)
    {
        int oldX = getX();
        int oldY = getY();
        setLocation(x, y);
        boolean hit = isTouching(cls);
        setLocation(oldX, oldY);
        return hit;
    }

    private void chooseBestDirection(foodman protagonist)
    {
        java.util.List<Integer> dirs = new java.util.ArrayList<>();
        for (int d = 0; d < 4; d++) if (canMove(d)) dirs.add(d);
        if (dirs.isEmpty()) return;

        int bestDir = dirs.get(0);
        int bestDist = Integer.MAX_VALUE;
        for (int d : dirs)
        {
            int nx = getX();
            int ny = getY();
            switch (d) { case 0: ny-=speed; break; case 1: nx+=speed; break; case 2: ny+=speed; break; case 3: nx-=speed; break; }
            int dx = protagonist.getX() - nx;
            int dy = protagonist.getY() - ny;
            int dist = dx*dx + dy*dy;
            if (dist < bestDist) { bestDist = dist; bestDir = d; }
        }
        direction = bestDir;
    }

    private void moveInDirection(int dir)
    {
        switch (dir)
        {
            case 0: setLocation(getX(), getY() - speed); break;
            case 1: setLocation(getX() + speed, getY()); break;
            case 2: setLocation(getX(), getY() + speed); break;
            case 3: setLocation(getX() - speed, getY()); break;
        }
        snapToGrid();
    }

    private void turnToDirection(int dir)
    {
        switch (dir) { case 0: setRotation(270); break; case 1: setRotation(0); break; case 2: setRotation(90); break; case 3: setRotation(180); break; }
    }

    private void snapToGrid()
    {
        int x = ((getX() + TILE_SIZE/4)/TILE_SIZE)*TILE_SIZE;
        int y = ((getY() + TILE_SIZE/4)/TILE_SIZE)*TILE_SIZE;
        setLocation(x, y);
    }
}
