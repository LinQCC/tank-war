package com.lq.tank.gameobject;

import com.lq.tank.TankFrame;
import com.lq.tank.abstractfactory.BaseBullet;
import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.manager.PropertyManager;
import com.lq.tank.manager.ResourceManager;
import lombok.Data;

import java.awt.*;

/**
 * @author babei
 * @date 2021/9/26
 */
@Data
public class SimpleBullet extends BaseBullet {

    private static final int SPEED = PropertyManager.getInt("bulletSpeed");;

    public static final int WIDTH = 10;

    public static final int HEIGHT = 10;

    protected Rectangle rect = new Rectangle(WIDTH,HEIGHT);

    private TankFrame tankFrame;

    public SimpleBullet(int x, int y, DirectionEnum dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        rect.x = x;
        rect.y = y;

        tankFrame.bulletList.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!alive) {
            tankFrame.bulletList.remove(this);
        }

        drawBullet(g);

        move();
    }

    private void drawBullet(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,10,10);
        g.setColor(color);
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

        boundCheck();

        // 更新碰撞矩形的位置
        rect.x = this.x;
        rect.y = this.y;

    }

    private void boundCheck() {
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            alive = false;
        }
    }

    /**
     * 子弹与坦克的碰撞检测
     *
     * @param tank  坦克
     */
    @Override
    public void collideWith(BaseTank tank){

        if (this.group == tank.getGroup()) {
            return;
        }

        if (this.rect.intersects(tank.getRect())) {
            tank.die();
            this.die();
        }
    }
}
