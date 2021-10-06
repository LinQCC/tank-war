package com.lq.tank;

import lombok.Data;

import java.awt.*;

/**
 * @author babei
 * @date 2021/9/26
 */
@Data
public class Bullet {

    private static final int SPEED = 10;

    public static final int WIDTH = ResourceManager.bulletU.getWidth();

    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    private int x;

    private int y;

    private DirectionEnum dir;

    private boolean alive = true;

    private TankFrame tankFrame;

    private Group group = Group.BAD;

    public Bullet(int x, int y, DirectionEnum dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!alive) {
            tankFrame.bulletList.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.bulletD, x, y, null);
                break;
            default:
        }
        move();
    }

    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            alive = false;
        }
    }

    public void collideWith(Tank tank) {

        if(this.group == tank.getGroup()){
            return;
        }
        // TODO: 2021/10/6 用一个Rectangle来记录子弹的位置，这样可以减少创建对象的数量
        Rectangle rect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect.intersects(rect2)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.alive = false;
    }
}
