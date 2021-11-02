package com.lq.tank.decorator;

import com.lq.tank.abstractfactory.BaseTank;

import java.awt.*;

/**
 * 装饰器的基类
 *
 * @author babei
 * @date 2021/11/2
 */
public abstract class TankDecorator extends BaseTank {

    BaseTank tank;

    public TankDecorator(BaseTank tank) {
        this.tank = tank;
    }

    public abstract void paint(Graphics g);
}
