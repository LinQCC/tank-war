package com.lq.tank;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TankGame {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = new TankFrame();

        //初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            tankFrame.enemies.add(new Tank(50+i*50,200,DirectionEnum.DOWN,Group.BAD,tankFrame));
        }

        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
