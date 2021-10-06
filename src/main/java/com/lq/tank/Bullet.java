package com.lq.tank;

import java.awt.*;

/**
 * @author babei
 * @date 2021/9/26
 */
public class Bullet {

    private static final int SPEED = 10;

    public static final int WIDTH = ResourceManager.bulletU.getWidth();

    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    private int x;

    private int y;

    private DirectionEnum dir;

    private boolean alive = true;

    private TankFrame tankFrame;

    public Bullet(int x, int y, DirectionEnum dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionEnum getDir() {
        return dir;
    }

    public void setDir(DirectionEnum dir) {
        this.dir = dir;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
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

        Rectangle rect = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if(rect.intersects(rect2)){
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.alive = false;
    }
}
