package com.lq.tank.gameobject;


import lombok.Data;

import java.awt.*;

/**
 * 游戏物体的抽象类
 *
 * @author babei
 * @date 2021/10/31
 */
@Data
public abstract class GameObject {

    protected int x;

    protected int y;

    public abstract void paint(Graphics g);
}