package com.lq.tank.gameobject;

import com.lq.tank.facade.GameModel;
import lombok.Data;

import java.awt.*;

/**
 * @author babei
 * @date 2021/11/1
 */
@Data
public class Wall extends GameObject{

    private int width;

    private int height;

    public Rectangle rect;

    public Wall(int x,int y,int width,int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rectangle(x,y,width,height);
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x,y,width,height);
        g.setColor(color);
    }
}
