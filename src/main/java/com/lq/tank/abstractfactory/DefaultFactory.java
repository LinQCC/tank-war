package com.lq.tank.abstractfactory;

import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.gameobject.Bullet;
import com.lq.tank.gameobject.Explode;
import com.lq.tank.facade.GameModel;
import com.lq.tank.gameobject.Tank;

/**
 * 默认坦克工厂类
 *
 * @author babei
 * @date 2021/10/29
 */
public class DefaultFactory extends TankFactory {

    @Override
    public BaseTank createTank(int x, int y, DirectionEnum dir, Group group, GameModel gameModel) {

        return new Tank(x,y,dir,group, gameModel);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnum dir, Group group, GameModel gameModel) {

        return new Bullet(x, y, dir, group, gameModel);
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameModel gameModel) {

        return new Explode(x,y, gameModel);
    }
}
