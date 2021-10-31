package com.lq.tank.facade;

import com.lq.tank.abstractfactory.*;
import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.enums.Group;
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

    public List<BaseBullet> bulletList = new ArrayList<>();

    public List<BaseTank> enemies = new ArrayList<>();

    public List<BaseExplode> explodes = new ArrayList<>();

    public TankFactory gameFactory = new DefaultFactory();

    public GameModel() {

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            enemies.add(gameFactory.createTank(50+i*50,200, DirectionEnum.DOWN,Group.BAD,this));
        }
    }

    public void paint(Graphics g){
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:" + bulletList.size(), 50, 100);
        g.drawString("敌人数量:" + enemies.size(), 50, 130 );
        g.drawString("爆炸数量:" + explodes.size(), 50, 160 );
        g.setColor(color);

        if(tank != null){
            tank.paint(g);
        }

        //绘制子弹
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        //另一种方式删除子弹，使用迭代器来删除，该方式不会报错
/*        for( Iterator<Bullet> iterator = bulletList.iterator();iterator.hasNext();){
            Bullet bullet = iterator.next();
            if(!bullet.isLive()){
                iterator.remove();
            }
        }*/

        //绘制敌方坦克
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        //子弹坦克碰撞检测
        for(BaseBullet bullet : bulletList){
            for(BaseTank tank : enemies){
                bullet.collideWith(tank);
            }
        }

        //绘制爆炸
        for(int i=0;i<explodes.size();i++){
            explodes.get(i).paint(g);
        }
    }

    public Tank getMainTank() {

        return tank;
    }
}
