package com.lq.tank.abstractfactory;

import com.lq.tank.*;
import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.gameobject.Bullet;
import com.lq.tank.gameobject.Explode;
import com.lq.tank.gameobject.Tank;

/**
 * 默认坦克工厂类
 *
 * @author babei
 * @date 2021/10/29
 */
public class DefaultFactory extends TankFactory {

    @Override
    public BaseTank createTank(int x, int y, DirectionEnum dir, Group group, TankFrame tankFrame) {

        return new Tank(x,y,dir,group,tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnum dir, Group group, TankFrame tankFrame) {

        return new Bullet(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {

        return new Explode(x,y,tankFrame);
    }
}
