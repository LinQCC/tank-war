package com.lq.tank;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TankGame {

    public static void main(String[] args) {

        TankFrame tankFrame = new TankFrame();

        int initTankCount = PropertyManager.getInt("initTankCount");

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.enemies.add(new Tank(50+i*50,200,DirectionEnum.DOWN,Group.BAD,tankFrame));
        }

        // 循环播放背景音乐
        new Thread(()->{
            new Audio("audio/war1.wav").loop();
        }).start();

        // 每隔一段时间重绘
        new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tankFrame.repaint();
            }
        }).start();


    }
}
