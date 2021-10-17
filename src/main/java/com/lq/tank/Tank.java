package com.lq.tank;

import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * @author babei
 * @date 2021/9/26
 */
@Data
public class Tank {

    private static final int SPEED = 5;

    public static final int WIDTH = ResourceManager.tankU.getWidth();

    public static final int HEIGHT = ResourceManager.tankU.getHeight();

    private int x;

    private int y;

    private DirectionEnum direction;

    private TankFrame tankFrame;

    private boolean moving = false;

    private boolean alive = true;

    private Random random = new Random();

    private Group group = Group.BAD;

    public Tank(int x, int y, DirectionEnum direction, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        this.tankFrame = tankFrame;
        if (this.group == Group.BAD) {
            moving = true;
        }
    }

    public void paint(Graphics g) {

        if (!alive) {
            if (this.group == Group.GOOD) {
                tankFrame.tank.setX(1000);
                tankFrame.tank.setY(1000);
            } else {
                tankFrame.enemies.remove(this);
            }
        }

        switch (direction) {
            case LEFT:
                g.drawImage(ResourceManager.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD, x, y, null);
                break;
            default:
        }
        move();
    }

    private void move() {
        int newX;
        int newY;
        if (moving) {
            switch (direction) {
                case LEFT:
                    newX = x - SPEED;
                    if (newX > 0) {
                        x = newX;
                    } else {
                        x = 0;
                    }
                    break;
                case UP:
                    newY = y - SPEED;
                    if (newY > 20) {
                        y = newY;
                    } else {
                        y = 20;
                    }
                    break;
                case RIGHT:
                    newX = x + SPEED;
                    if (newX < (TankFrame.GAME_WIDTH - WIDTH)) {
                        x = newX;
                    } else {
                        x = (TankFrame.GAME_WIDTH - WIDTH);
                    }
                    break;
                case DOWN:
                    newY = y + SPEED;
                    if (newY < (TankFrame.GAME_HEIGHT - HEIGHT)) {
                        y = newY;
                    } else {
                        y = (TankFrame.GAME_HEIGHT - HEIGHT);
                    }
                    break;
                default:
            }
        }

        randomDirAndFire();

    }

    private void randomDirAndFire() {
        //敌方坦克随机转向
        if (this.group == Group.BAD) {
            if (random.nextInt(40) > 38) {
                this.direction = DirectionEnum.values()[random.nextInt(DirectionEnum.values().length)];
            }
        }

        //敌方坦克随机开火
        if (this.group == Group.BAD) {
            if (random.nextInt(50) > 48) {
                fire();
            }
        }
    }


    public void fire() {
        int bx = x + WIDTH / 2 - Bullet.WIDTH / 2;
        int by = y + HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bulletList.add(new Bullet(bx, by, direction, this.group, tankFrame));
    }

    public void die() {
        this.alive = false;
        tankFrame.explodes.add(new Explode(this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2, this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2, tankFrame));
    }
}
