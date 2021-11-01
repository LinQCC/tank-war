package com.lq.tank.gameobject;

import com.lq.tank.TankFrame;
import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.facade.GameModel;
import com.lq.tank.manager.PropertyManager;
import com.lq.tank.manager.ResourceManager;
import com.lq.tank.strategy.DefaultFireStrategy;
import com.lq.tank.strategy.FireStrategy;
import com.lq.tank.strategy.FourDirFireStrategy;
import lombok.Data;

import java.awt.*;
import java.util.Random;

/**
 * @author babei
 * @date 2021/9/26
 */
@Data
public class Tank extends BaseTank {

    public static final int WIDTH = ResourceManager.goodTankU.getWidth();

    public static final int HEIGHT = ResourceManager.goodTankU.getHeight();

    private static final int SPEED = PropertyManager.getInt("tankSpeed");

    private boolean moving = false;

    private Random random = new Random();

    private FireStrategy fireStrategy = new DefaultFireStrategy();

    public Tank(int x, int y, DirectionEnum direction, Group group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.group = group;
        if (this.group == Group.BAD) {
            moving = true;
        }
        rect = new Rectangle(x,y,WIDTH,HEIGHT);

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {

        if (!alive) {
            if (this.group == Group.GOOD) {
                GameModel.getInstance().getMainTank().setX(1000);
                GameModel.getInstance().getMainTank().setY(1000);
            } else {
                GameModel.getInstance().remove(this);
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
        preX = x;
        preY = y;
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

        // 更新碰撞矩形的位置
        rect.x = this.x;
        rect.y = this.y;
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

        if (alive) {
            fireStrategy.fire(this);
        }
    }

    @Override
    public void die() {
        this.alive = false;
        GameModel.getInstance().add(GameModel.getInstance().gameFactory.createExplode(this.x + Tank.WIDTH / 2 - Explode.WIDTH / 2, this.y + Tank.HEIGHT / 2 - Explode.HEIGHT / 2));
    }
}
