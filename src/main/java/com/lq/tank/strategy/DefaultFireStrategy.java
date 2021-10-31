package com.lq.tank.strategy;

import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.audio.Audio;
import com.lq.tank.enums.Group;
import com.lq.tank.gameobject.Bullet;
import com.lq.tank.gameobject.Tank;

/**
 * @author babei
 * @date 2021/10/26
 */
public class DefaultFireStrategy implements FireStrategy {


    @Override
    public void fire(BaseTank tank) {
        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        tank.getGameModel().gameFactory.createBullet(bx, by, tank.getDirection(), tank.getGroup(), tank.getGameModel());

        // 播放开火音效
        if (tank.getGroup() == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
