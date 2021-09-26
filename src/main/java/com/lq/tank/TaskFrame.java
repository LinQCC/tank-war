package com.lq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TaskFrame extends Frame {

    Tank tank = new Tank(200,200,DirectionEnum.DOWN);
    Bullet bullet = new Bullet(300,300,DirectionEnum.DOWN);

    public TaskFrame() throws HeadlessException {
        setSize(1000, 1000);
        setResizable(false);
        setTitle("坦克大战");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new MyKeyListener());
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {

       tank.paint(g);
       bullet.paint(g);

    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            if(!bL && !bU && !bR && !bD){
                tank.setMoving(false);
            }

            if(bL){
                tank.setDirection(DirectionEnum.LEFT);
                tank.setMoving(true);
            }
            if(bU){
                tank.setDirection(DirectionEnum.UP);
                tank.setMoving(true);
            }
            if(bR){
                tank.setDirection(DirectionEnum.RIGHT);
                tank.setMoving(true);
            }
            if(bD){
                tank.setDirection(DirectionEnum.DOWN);
                tank.setMoving(true);
            }
        }

    }
}
