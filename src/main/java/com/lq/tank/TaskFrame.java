package com.lq.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author babei
 * @date 2021/9/25
 */
public class TaskFrame extends Frame {

    public TaskFrame() throws HeadlessException {
        setSize(500,500);
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
        g.fillRect(200,200,50,50);
    }
}
