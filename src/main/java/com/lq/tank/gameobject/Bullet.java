package com.lq.tank.gameobject;

import com.lq.tank.TankFrame;
import com.lq.tank.abstractfactory.BaseBullet;
import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.facade.GameModel;
import com.lq.tank.manager.PropertyManager;
import com.lq.tank.manager.ResourceManager;
import lombok.Data;

import java.awt.*;

/**
 * @author babei
 * @date 2021/9/26
 */
@Data
public class Bullet extends BaseBullet {

    private static final int SPEED = PropertyManager.getInt("bulletSpeed");;

    public static final int WIDTH = ResourceManager.bulletU.getWidth();

    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    public Bullet(int x, int y, DirectionEnum dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        rect = new Rectangle(x,y,WIDTH,HEIGHT);

        gameModel.gameObjectList.add(this);
    }

    @Override
    public void paint(Graphics g) {
        if (!alive) {
            gameModel.remove(this);
            return;
        }

        drawBullet(g);

        move();
    }

    private void drawBullet(Graphics g) {
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

}
