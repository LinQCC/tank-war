package com.lq.tank.collide;

import com.lq.tank.abstractfactory.BaseTank;
import com.lq.tank.gameobject.GameObject;
import com.lq.tank.gameobject.Wall;

/**
 * @author babei
 * @date 2021/11/1
 */
public class TankWallCollider implements Collider{

    @Override
    public Boolean collide(GameObject go1, GameObject go2) {

        if(go1 instanceof Wall && go2 instanceof BaseTank){
            Wall wall = (Wall) go1;
            BaseTank tank = (BaseTank) go2;

            if (wall.getRect().intersects(tank.getRect())) {
                tank.setX(tank.getPreX());
                tank.setY(tank.getPreY());
            }
        }else if(go1 instanceof BaseTank && go2 instanceof Wall){
            collide(go2,go1);
        }
        return true;
    }
}
