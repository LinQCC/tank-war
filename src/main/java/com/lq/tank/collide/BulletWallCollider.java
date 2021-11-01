package com.lq.tank.collide;

import com.lq.tank.abstractfactory.BaseBullet;
import com.lq.tank.gameobject.GameObject;
import com.lq.tank.gameobject.Wall;

/**
 * @author babei
 * @date 2021/11/1
 */
public class BulletWallCollider implements Collider {


    @Override
    public Boolean collide(GameObject go1, GameObject go2) {
        if(go1 instanceof Wall && go2 instanceof BaseBullet){
            Wall wall = (Wall) go1;
            BaseBullet bullet = (BaseBullet) go2;

            if (wall.getRect().intersects(bullet.getRect())) {
                bullet.die();
            }
        }else if(go1 instanceof BaseBullet && go2 instanceof Wall){
            collide(go2,go1);
        }
        return true;
    }
}
