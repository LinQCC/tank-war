package com.lq.tank;


import com.lq.tank.enums.DirectionEnum;
import com.lq.tank.facade.GameModel;
import com.lq.tank.gameobject.Tank;
import com.lq.tank.manager.PropertyManager;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * @author babei
 * @date 2021/9/25
 */
public class TankFrame extends Frame {




    public static final int GAME_WIDTH = PropertyManager.getInt("gameWidth");

    public static final int GAME_HEIGHT = PropertyManager.getInt("gameHeight");;

    private GameModel gameModel = new GameModel();


    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH, GAME_HEIGHT);
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

    Image offScreemImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreemImage == null) {
            offScreemImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreemImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreemImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {

        gameModel.paint(g);
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
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
            switch (e.getKeyCode()) {
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
                case KeyEvent.VK_SPACE:
                    gameModel.getMainTank().fire();
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            Tank mainTank = gameModel.getMainTank();

            if (!bL && !bU && !bR && !bD) {
                mainTank.setMoving(false);
            }

            if (bL) {
                mainTank.setDirection(DirectionEnum.LEFT);
                mainTank.setMoving(true);
            }
            if (bU) {
                mainTank.setDirection(DirectionEnum.UP);
                mainTank.setMoving(true);
            }
            if (bR) {
                mainTank.setDirection(DirectionEnum.RIGHT);
                mainTank.setMoving(true);
            }
            if (bD) {
                mainTank.setDirection(DirectionEnum.DOWN);
                mainTank.setMoving(true);
            }
        }

    }
}
