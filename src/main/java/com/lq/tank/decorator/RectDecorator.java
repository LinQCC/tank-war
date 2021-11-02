package com.lq.tank.decorator;

import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.facade.GameModel;

import java.awt.*;

/**
 *
 * 边框装饰类
 *
 * @author babei
 * @date 2021/11/2
 */
public class RectDecorator extends TankDecorator {

    public RectDecorator(BaseTank tank) {
        super(tank);
    }

    @Override
    public void paint(Graphics g) {
        this.x = tank.getX();
        this.y = tank.getY();
        tank.paint(g);
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.drawRect(tank.getX(),tank.getY(),50,50);
        g.setColor(color);
    }

    @Override
    public void die() {
        tank.die();
        GameModel.getInstance().remove(this);
    }
}
