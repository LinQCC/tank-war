package com.lq.tank.abstractfactory;

import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.facade.GameModel;
import com.lq.tank.gameobject.Bullet;
import com.lq.tank.gameobject.GameObject;
import lombok.Data;

import java.awt.*;

/**
 * 子弹抽象类
 *
 * @author babei
 * @date 2021/10/29
 */
@Data
public abstract class BaseBullet extends GameObject {


    protected DirectionEnum dir;

    protected Group group;

    protected boolean alive = true;

    protected Rectangle rect;

    protected GameModel gameModel;

    public abstract void paint(Graphics g);

    public void die() {
        this.alive = false;
    }
}
