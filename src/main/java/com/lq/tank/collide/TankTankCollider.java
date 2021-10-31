package com.lq.tank.collide;

import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.gameobject.GameObject;

/**
 * 坦克和坦克的碰撞器
 *
 * @author babei
 * @date 2021/10/31
 */
public class TankTankCollider implements Collider{


    @Override
    public Boolean collide(GameObject go1, GameObject go2) {
        if(go1 instanceof BaseTank && go2 instanceof BaseTank){
            BaseTank tank = (BaseTank) go1;
            BaseTank tank2 = (BaseTank) go2;

            if (tank.getRect().intersects(tank2.getRect())) {
                tank.setX(tank.getPreX());
                tank.setY(tank.getPreY());
                tank2.setX(tank2.getPreX());
                tank2.setY(tank2.getPreY());
            }
        }
        return true;
    }
}
