package com.lq.tank;

import lombok.Data;

import java.awt.*;

/**
 * @author babei
 * @date 2021/10/6
 */
@Data
public class Explode {

    public static final int WIDTH = ResourceManager.explodes[0].getWidth();

    public static final int HEIGHT = ResourceManager.explodes[0].getHeight();

    private TankFrame tankFrame;

    private int x;

    private int y;

    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        // 播放爆炸音效
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], this.x, this.y, null);
        if (step >= ResourceManager.explodes.length) {
            tankFrame.explodes.remove(this);
        }
    }
}
