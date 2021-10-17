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

    private static final int SPEED = 10;

    public static final int WIDTH = ResourceManager.goodTankU.getWidth();

    public static final int HEIGHT = ResourceManager.goodTankU.getHeight();

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

        drawTank(g);

        move();

        randomDirAndFire();
    }

    private void drawTank(Graphics g) {

        switch (direction) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankL : ResourceManager.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankU : ResourceManager.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankR : ResourceManager.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceManager.goodTankD : ResourceManager.badTankD, x, y, null);
                break;
            default:
        }
    }

    private void move() {
        if (moving) {
            switch (direction) {
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

        // 边界检测
        boundCheck();
    }

    private void boundCheck() {
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            this.x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (this.y < 30) {
            this.y = 30;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            this.y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }
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
