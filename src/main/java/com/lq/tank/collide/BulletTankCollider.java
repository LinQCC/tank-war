package com.lq.tank.collide;

import com.lq.tank.abstractfactory.BaseBullet;
import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.gameobject.GameObject;

/**
 * 子弹和坦克的碰撞器
 *
 * @author babei
 * @date 2021/10/31
 */
public class BulletTankCollider implements Collider{

    @Override
    public Boolean collide(GameObject go1, GameObject go2) {
        if(go1 instanceof BaseBullet && go2 instanceof BaseTank){
            BaseBullet bullet = (BaseBullet) go1;
            BaseTank tank = (BaseTank) go2;
            if(bullet.getGroup() == tank.getGroup()){
                return true;
            }

            if (bullet.getRect().intersects(tank.getRect())) {
                tank.die();
                bullet.die();
                return false;
            }
        }else if(go1 instanceof BaseTank && go2 instanceof BaseBullet){
            collide(go2,go1);
        }
        return true;
    }
}
