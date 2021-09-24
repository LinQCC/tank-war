package com.lq.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author babei
 * @date 2021/9/25
 */
public class Tank {

    public static void main(String[] args) {

        Frame frame = new Frame();
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("坦克大战");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}
