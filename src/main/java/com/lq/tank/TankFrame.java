package com.lq.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * @author babei
 * @date 2021/9/25
 */
public class TankFrame extends Frame {

    public Tank tank = new Tank(500, 500, DirectionEnum.DOWN, Group.GOOD,this);

    List<Bullet> bulletList = new ArrayList<>();

    List<Tank> enemies = new ArrayList<>();

    List<Explode> explodes = new ArrayList<>();

    public static final int GAME_WIDTH = 1080;
    public static final int GAME_HEIGHT = 960;

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

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:" + bulletList.size(), 50, 100);
        g.drawString("敌人数量:" + enemies.size(), 50, 130 );
        g.drawString("爆炸数量:" + explodes.size(), 50, 160 );
        g.setColor(color);

        if(tank != null){
            tank.paint(g);
        }

        //绘制子弹
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        //另一种方式删除子弹，使用迭代器来删除，该方式不会报错
/*        for( Iterator<Bullet> iterator = bulletList.iterator();iterator.hasNext();){
            Bullet bullet = iterator.next();
            if(!bullet.isLive()){
                iterator.remove();
            }
        }*/

        //绘制敌方坦克
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        //子弹坦克碰撞检测
        for(Bullet bullet : bulletList){
            for(Tank tank : enemies){
                bullet.collideWith(tank);
            }
            bullet.collideWith(tank);
        }

        //绘制爆炸
        for(int i=0;i<explodes.size();i++){
            explodes.get(i).paint(g);
        }

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
                    tank.fire();
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            if (!bL && !bU && !bR && !bD) {
                tank.setMoving(false);
            }

            if (bL) {
                tank.setDirection(DirectionEnum.LEFT);
                tank.setMoving(true);
            }
            if (bU) {
                tank.setDirection(DirectionEnum.UP);
                tank.setMoving(true);
            }
            if (bR) {
                tank.setDirection(DirectionEnum.RIGHT);
                tank.setMoving(true);
            }
            if (bD) {
                tank.setDirection(DirectionEnum.DOWN);
                tank.setMoving(true);
            }
        }

    }
}
