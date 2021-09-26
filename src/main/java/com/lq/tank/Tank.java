package com.lq.tank;

import java.awt.*;

/**
 * @author babei
 * @date 2021/9/26
 */
public class Tank {

    private static final int SPEED = 5;

    private int x;

    private int y;

    private DirectionEnum direction;


    private boolean moving = false;

    public Tank(int x, int y, DirectionEnum direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {

        g.fillRect(x, y, 50, 50);
        move();
    }

    private void move() {
        if(moving){
            switch (direction){
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
                default:
            }
        }

    }
}
