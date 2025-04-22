package com.tedu.manager;

import javax.swing.*;
import java.awt.*;

/** 获取图片的工具类
 *
 */
public class ImageUtil {
    /**
     * @Description: 获取指定路径下图片的方法
     * @Param: path 图片的路径
     * @return: 图片
     * @Author: ManolinCoder
     */
    public static Image getImg(String path){
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        return img;
    }

    public static ImageIcon getImgIcon(String path){
        ImageIcon icon = new ImageIcon(path);
        return icon;
    }
}
