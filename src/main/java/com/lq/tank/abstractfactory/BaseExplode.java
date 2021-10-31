package com.lq.tank.abstractfactory;

import com.lq.tank.gameobject.GameObject;

import java.awt.*;

/**
 * 爆炸抽象类
 *
 * @author babei
 * @date 2021/10/29
 */
public abstract class BaseExplode extends GameObject {

    public abstract void paint(Graphics g);
}
