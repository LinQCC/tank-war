package com.lq.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author babei
 * @date 2021/10/17
 */
public class ImageUtil {

    public static BufferedImage rotateImage(final BufferedImage bufferedImage,final int degree){

        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();
        BufferedImage newImg = new BufferedImage(width,height,type);
        Graphics2D graphics2D = (newImg.createGraphics());
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(degree),width/2,height/2);
        graphics2D.drawImage( bufferedImage,0,0,null);
        graphics2D.dispose();
        return newImg;
    }
}
