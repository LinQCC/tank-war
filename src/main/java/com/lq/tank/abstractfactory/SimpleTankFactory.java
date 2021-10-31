package com.lq.tank.abstractfactory;

import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.facade.GameModel;
import com.lq.tank.gameobject.SimpleTank;
import com.lq.tank.gameobject.SimpleBullet;
import com.lq.tank.gameobject.SimpleExplode;

/**
 * @author babei
 * @date 2021/10/29
 */
public class SimpleTankFactory extends TankFactory {

    @Override
    public BaseTank createTank(int x, int y, DirectionEnum dir, Group group, GameModel gameModel) {
        return new SimpleTank(x,y,dir,group, gameModel);
    }

    @Override
    public BaseBullet createBullet(int x, int y, DirectionEnum dir, Group group, GameModel gameModel) {
        return new SimpleBullet(x,y,dir,group, gameModel);
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameModel gameModel) {
        return new SimpleExplode(x,y);
    }
}
