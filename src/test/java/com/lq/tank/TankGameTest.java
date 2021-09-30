package com.lq.tank;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author babei
 * @date 2021/9/30
 */
class TankGameTest {


    @Test
    void test(){

        try {
            BufferedImage bufferedImage = ImageIO.read(TankGameTest.class.getClassLoader().getResourceAsStream("com/lq/tank/bulletD.gif"));
            assertNotNull(bufferedImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}