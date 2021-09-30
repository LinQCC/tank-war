package com.lq.tank;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TankGame {

    public static void main(String[] args) throws InterruptedException {

        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
