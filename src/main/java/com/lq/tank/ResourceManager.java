package com.lq.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author babei
 * @date 2021/10/1
 */
public class ResourceManager {

    public static BufferedImage tankL, tankU, tankR, tankD;

    public static BufferedImage bulletL, bulletU, bulletR, bulletD;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/tankL.gif"));
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/tankU.gif"));
            tankR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/tankR.gif"));
            tankD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/tankD.gif"));

            bulletL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletL.gif"));
            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletU.gif"));
            bulletR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletR.gif"));
            bulletD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/bulletD.gif"));

            //加载爆炸图片
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("image/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
