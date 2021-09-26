package com.lq.tank;

import java.awt.*;

/**
 * @author babei
 * @date 2021/9/26
 */
public class Bullet {

    private static final int SPEED = 10;

    private static final int WIDTH = 5;

    private static final int HEIGHT = 5;

    private int x;

    private int y;

    private DirectionEnum dir;

    private boolean live = true;

    private TaskFrame taskFrame;

    public Bullet(int x, int y, DirectionEnum dir, TaskFrame taskFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.taskFrame = taskFrame;
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        if (!live) {
            taskFrame.bulletList.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
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
        if (x < 0 || y < 0 || x > TaskFrame.GAME_WIDTH || y > TaskFrame.GAME_HEIGHT) {
            live = false;
        }
    }
}
