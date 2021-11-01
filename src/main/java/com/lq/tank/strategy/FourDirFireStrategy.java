package com.lq.tank.strategy;

import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.audio.Audio;
import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.facade.GameModel;
import com.lq.tank.gameobject.Bullet;
import com.lq.tank.gameobject.Tank;
import com.lq.tank.strategy.FireStrategy;

/**
 * @author babei
 * @date 2021/10/26
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(BaseTank tank) {

        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        DirectionEnum[] dirs = DirectionEnum.values();
        for(DirectionEnum dir : dirs){
            GameModel.getInstance().gameFactory.createBullet(bx, by, dir, tank.getGroup());
        }

        // 播放开火音效
        if (tank.getGroup() == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
