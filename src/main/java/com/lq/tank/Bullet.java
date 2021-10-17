package com.lq.tank;

import lombok.Data;

import java.awt.*;

/**
 * @author babei
 * @date 2021/9/26
 */
@Data
public class Bullet {

    private static final int SPEED = 30;

    public static final int WIDTH = ResourceManager.bulletU.getWidth();

    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    private Rectangle rect = new Rectangle();

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
        rect.x = x;
        rect.y = y;
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

        // 更新碰撞矩形的位置
        rect.x = x;
        rect.y = y;

    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()){
            return;
        }

        if (this.rect.intersects(tank.getRect())) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.alive = false;
    }
}
