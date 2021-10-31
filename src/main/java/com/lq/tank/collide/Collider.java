package com.lq.tank.collide;

import com.lq.tank.gameobject.GameObject;

/**
 * 碰撞器接口
 *
 * @author babei
 * @date 2021/10/31
 */
public interface Collider {

    Boolean collide(GameObject go1,GameObject go2);
}
