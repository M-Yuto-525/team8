import greenfoot.*;

public class tenshu2 extends Actor
{
    private int speed = 1;

    // 0:上 1:右 2:下 3:左
    private int direction = 1;

    public tenshu2()
    {
        GreenfootImage img = new GreenfootImage("店主.png");
        img.scale(64, 88);
        setImage(img);
    }

    public void act()
    {
        foodman protagonist =
            (foodman)getWorld().getObjects(foodman.class).get(0);

        if (protagonist == null) return;

        // 進めないなら方向転換
        if (!canMove(direction))
        {
            chooseNewDirection(protagonist);
        }

        // 移動
        moveInDirection(direction);

        // 見た目の向き
        turnToDirection(direction);

        // 接触でゲームオーバー
        if (isTouching(foodman.class))
        {
            getWorld().showText(
                "GAME OVER",
                getWorld().getWidth()/2,
                getWorld().getHeight()/2
            );
            Greenfoot.stop();
        }
    }

    // 指定方向に進めるか（1歩先を仮チェック）
    private boolean canMove(int dir)
    {
        int oldX = getX();
        int oldY = getY();

        switch (dir)
        {
            case 0: setLocation(oldX, oldY - speed); break;
            case 1: setLocation(oldX + speed, oldY); break;
            case 2: setLocation(oldX, oldY + speed); break;
            case 3: setLocation(oldX - speed, oldY); break;
        }

        boolean hitWall = isTouching(Brick.class);

        // ★ 必ず元に戻す
        setLocation(oldX, oldY);

        return !hitWall;
    }

    // プレイヤーに近づく方向を選ぶ
    private void chooseNewDirection(foodman protagonist)
    {
        int bestDir = direction;
        int bestDist = Integer.MAX_VALUE;

        for (int dir = 0; dir < 4; dir++)
        {
            if (!canMove(dir)) continue;

            int nx = getX();
            int ny = getY();

            switch (dir)
            {
                case 0: ny -= speed; break;
                case 1: nx += speed; break;
                case 2: ny += speed; break;
                case 3: nx -= speed; break;
            }

            int dx = protagonist.getX() - nx;
            int dy = protagonist.getY() - ny;
            int dist = dx*dx + dy*dy;

            if (dist < bestDist)
            {
                bestDist = dist;
                bestDir = dir;
            }
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
        switch (dir)
        {
            case 0: setRotation(270); break;
            case 1: setRotation(0);   break;
            case 2: setRotation(90);  break;
            case 3: setRotation(180); break;
        }
    }
}
