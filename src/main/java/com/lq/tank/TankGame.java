package com.lq.tank;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TankGame {

    public static void main(String[] args) throws InterruptedException {

        TaskFrame taskFrame = new TaskFrame();

        while (true){
            Thread.sleep(50);
            taskFrame.repaint();
        }
    }
}
