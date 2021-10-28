package com.lq.tank.abstractfactory;

import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.TankFrame;

/**
 * 生产坦克相关类的抽象工厂类
 *
 * @author babei
 * @date 2021/10/28
 */
public abstract class TankFactory {

    public abstract BaseTank createTank(int x, int y, DirectionEnum dir, Group group, TankFrame tankFrame);

    public abstract BaseBullet createBullet(int x, int y, DirectionEnum dir,Group group, TankFrame tankFrame);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
}
