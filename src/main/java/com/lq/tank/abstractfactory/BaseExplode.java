package com.lq.tank.abstractfactory;

import java.awt.*;

/**
 * 爆炸抽象类
 *
 * @author babei
 * @date 2021/10/29
 */
public abstract class BaseExplode {

    protected int x;

    protected int y;


    public abstract void paint(Graphics g);
}
