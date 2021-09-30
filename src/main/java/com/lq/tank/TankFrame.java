package com.lq.tank;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author babei
 * @date 2021/9/25
 */
public class TankFrame extends Frame {

    Tank tank = new Tank(200,200,DirectionEnum.DOWN,this);
    List<Bullet> bulletList = new ArrayList<>();
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

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
        if(offScreemImage == null){
            offScreemImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen  = offScreemImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreemImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:"+bulletList.size(),100,100);
        g.setColor(color);
       tank.paint(g);

       for(int i= 0;i<bulletList.size();i++){
           bulletList.get(i).paint(g);
       }

       //另一种方式删除子弹，使用迭代器来删除，该方式不会报错
/*        for( Iterator<Bullet> iterator = bulletList.iterator();iterator.hasNext();){
            Bullet bullet = iterator.next();
            if(!bullet.isLive()){
                iterator.remove();
            }
        }*/

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
                case KeyEvent.VK_SPACE:
                    tank.fire();
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