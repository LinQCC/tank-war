package com.lq.tank.gameobject;

import com.lq.tank.manager.ResourceManager;
import com.lq.tank.TankFrame;
import com.lq.tank.abstractfactory.BaseExplode;
import com.lq.tank.audio.Audio;
import lombok.Data;

import java.awt.*;

/**
 * @author babei
 * @date 2021/10/6
 */
@Data
public class Explode extends BaseExplode {

    public static final int WIDTH = ResourceManager.explodes[0].getWidth();

    public static final int HEIGHT = ResourceManager.explodes[0].getHeight();

    private TankFrame tankFrame;

    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        // 播放爆炸音效
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], this.x, this.y, null);
        if (step >= ResourceManager.explodes.length) {
            tankFrame.explodes.remove(this);
        }
    }
}
