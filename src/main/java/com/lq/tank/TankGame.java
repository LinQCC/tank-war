package com.lq.tank;

import com.lq.tank.audio.Audio;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TankGame {

    public static void main(String[] args) {

        TankFrame tankFrame = new TankFrame();

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
