package com.lq.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TaskFrame extends Frame {

    int x = 200, y = 200;

    public TaskFrame() throws HeadlessException {
        setSize(500, 500);
        setResizable(false);
        setTitle("坦克大战");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println(x+" "+y);
        g.fillRect(x, y, 50, 50);
        x += 20;
        y += 20;
    }
}
