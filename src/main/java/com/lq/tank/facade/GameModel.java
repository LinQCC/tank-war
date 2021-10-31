package com.lq.tank.facade;

import com.lq.tank.abstractfactory.*;
import com.lq.tank.collide.BulletTankCollider;
import com.lq.tank.collide.Collider;
import com.lq.tank.collide.ColliderChain;
import com.lq.tank.collide.TankTankCollider;
import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
import com.lq.tank.gameobject.GameObject;
import com.lq.tank.gameobject.Tank;
import com.lq.tank.manager.PropertyManager;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏模型的一个门面，负责模型的绘制和碰撞处理
 *
 * @author babei
 * @date 2021/10/31
 */
@Data
public class GameModel {

    int initTankCount = PropertyManager.getInt("initTankCount");

    public Tank tank = new Tank(500, 500, DirectionEnum.DOWN, Group.GOOD,this);

    public TankFactory gameFactory = new DefaultFactory();

    public List<GameObject> gameObjectList = new ArrayList<>();

    Collider bulletTankCollider = new BulletTankCollider();

    Collider tankTankCollider = new TankTankCollider();

    ColliderChain colliderChain = new ColliderChain();

    public GameModel() {

        gameObjectList.add(tank);

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            gameObjectList.add(gameFactory.createTank(50+i*100,200, DirectionEnum.DOWN,Group.BAD,this));
        }
        colliderChain.add(new BulletTankCollider());
        colliderChain.add(new TankTankCollider());
    }

    /**
     * 绘制模型和碰撞检测
     *
     * @param g 画笔
     */
    public void paint(Graphics g){

        // 绘制状态栏
/*        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:" + gameObjectList.size(), 50, 100);
        g.drawString("敌人数量:" + gameObjectList.size(), 50, 130 );
        g.drawString("爆炸数量:" + gameObjectList.size(), 50, 160 );
        g.setColor(color);*/

        // 绘制游戏物体
        for (int i = 0; i < gameObjectList.size(); i++) {
            gameObjectList.get(i).paint(g);
        }

        // 碰撞检测
        for(int i=0;i<gameObjectList.size();i++){
            for(int j=i+1;j<gameObjectList.size();j++){
                GameObject o1 =gameObjectList.get(i);
                GameObject o2 = gameObjectList.get(j);

                colliderChain.collide(o1,o2);
/*                // 检测子弹和坦克的碰撞
                bulletTankCollider.collide(o1,o2);

                // 检测坦克和坦克之间的碰撞
                tankTankCollider.collide(o1,o2);*/
            }
        }

    }

    /**
     * 获取玩家坦克
     *
     * @return  玩家坦克
     */
    public Tank getMainTank() {

        return tank;
    }

    /**
     * 添加游戏物体
     *
     * @param gameObject    游戏物体
     */
    public void add(GameObject gameObject){
        gameObjectList.add(gameObject);
    }


    /**
     * 移除游戏物体
     *
     * @param gameObject    游戏物体
     */
    public void remove(GameObject gameObject){
        gameObjectList.remove(gameObject);
    }
}
