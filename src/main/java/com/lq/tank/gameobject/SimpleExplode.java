package com.lq.tank.gameobject;

import com.lq.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * 简单的方形爆炸类
 *
 * @author babei
 * @date 2021/10/29
 */
public class SimpleExplode extends BaseExplode {

    public SimpleExplode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {

    }
}
