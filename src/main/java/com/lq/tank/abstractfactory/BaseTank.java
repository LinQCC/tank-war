package com.lq.tank.abstractfactory;

import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.facade.GameModel;
import com.lq.tank.gameobject.GameObject;
import lombok.Data;

import java.awt.*;

/**
 *  坦克抽象类
 *
 * @author babei
 * @date 2021/10/28
 */
@Data
public abstract class BaseTank extends GameObject {

    protected int preX;

    protected int preY;

    protected DirectionEnum direction;

    protected boolean alive = true;

    protected Group group;

    protected Rectangle rect;


    /**
     * 坦克绘制
     *
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * 坦克死亡
     */
    public abstract void die();


}

