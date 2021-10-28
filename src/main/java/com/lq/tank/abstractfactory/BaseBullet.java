package com.lq.tank.abstractfactory;

import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.gameobject.Bullet;
import lombok.Data;

import java.awt.*;

/**
 * 子弹抽象类
 *
 * @author babei
 * @date 2021/10/29
 */
@Data
public abstract class BaseBullet {


    protected int x;

    protected int y;

    protected DirectionEnum dir;

    protected Group group;

    protected boolean alive = true;

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);

    protected void die() {
        this.alive = false;
    }
}
