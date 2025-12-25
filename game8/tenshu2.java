import greenfoot.*;

public class tenshu2 extends Actor
{
    private int speed = 1;           // 移動速度
    private int direction = -1;      // 0=上,1=右,2=下,3=左, -1=初期未決定
    private boolean initialized = false; // 初期フレーム判定
    private static final int TILE_SIZE = 50;

    public tenshu2()
    {
        GreenfootImage img = new GreenfootImage("店主.png");
        img.scale(TILE_SIZE, TILE_SIZE);
        setImage(img);
    }

    public void act()
    {
        foodman protagonist = (foodman)getWorld().getObjects(foodman.class).get(0);
        if (protagonist == null) return;

        if (!initialized) {
            initialized = true;
            direction = -1; 
            snapToGrid();   
            return;
        }

        // 修正点：マスの中心（交差点）に来たとき、または動けないときに方向を決める
        if (isAtIntersection() || direction == -1 || !canMove(direction)) {
            chooseBestDirection(protagonist);
        }

        moveInDirection(direction);
        turnToDirection(direction);

        if (isTouching(foodman.class)) {
            getWorld().showText("GAME OVER", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
    }
    // 新しいメソッドを追加：マスの中心にいるか判定
    private boolean isAtIntersection()
    {
        // 座標を50で割った余りが25（中心）ならtrue
        return (getX() % TILE_SIZE == TILE_SIZE / 2) && (getY() % TILE_SIZE == TILE_SIZE / 2);
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
        
        // 進行方向の「逆（後ろ）」を計算 (例: 上(0)なら下(2))
        int back = (direction + 2) % 4;
        
        // 「行き止まり」じゃない限り、後ろには戻らないように選択肢から消す
        if (dirs.contains(back) && dirs.size() > 1) {
            dirs.remove((Integer)back);
        }

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
    }

    private void turnToDirection(int dir)
    {
        switch (dir) { case 0: setRotation(270); break; case 1: setRotation(0); break; case 2: setRotation(90); break; case 3: setRotation(180); break; }
    }

    private void snapToGrid()
    {
        int x = (getX() / TILE_SIZE) * TILE_SIZE + TILE_SIZE / 2;
        int y = (getY() / TILE_SIZE) * TILE_SIZE + TILE_SIZE / 2;
        setLocation(x, y);
    }
}