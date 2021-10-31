package com.lq.tank.collide;

import com.lq.tank.gameobject.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author babei
 * @date 2021/10/31
 */
public class ColliderChain implements Collider{

    List<Collider> colliderList = new LinkedList<>();

    public void add(Collider collider){
        colliderList.add(collider);
    }

    @Override
    public Boolean collide(GameObject o1, GameObject o2) {

        for(int i=0;i<colliderList.size();i++){
            Boolean collide = colliderList.get(i).collide(o1, o2);
            if(!collide){
                return false;
            }
        }
        return true;
    }
}
