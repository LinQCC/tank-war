package com.lq.tank.strategy;

import com.lq.tank.abstractfactory.BaseTank;

/**
 *  坦克开火策略
 *
 * @author babei
 * @date 2021/10/26
 */
public interface FireStrategy {

    void fire(BaseTank tank);
}
