package com.lq.tank;

/**
 * @author babei
 * @date 2021/10/26
 */
public class FourDirFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank tank) {

        int bx = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        DirectionEnum[] dirs = DirectionEnum.values();
        for(DirectionEnum dir : dirs){
            new Bullet(bx, by, dir, tank.getGroup(), tank.getTankFrame());
        }

        // 播放开火音效
        if (tank.getGroup() == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
