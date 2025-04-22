package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

/**
 * 发射的渔网类
 */
public class PlayFile extends ElementObj{
    private int value;//火力值
    private boolean live=true;
    protected void updateImage() {}

    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(),
                this.getX(),this.getY(),
                this.getW(),this.getH(), null);
//        System.out.println("showElement method called:");
//        System.out.println("  Icon: " + this.getIcon());
//        System.out.println("  X: " + this.getX());
//        System.out.println("  Y: " + this.getY());
//        System.out.println("  Width: " + this.getW());
//        System.out.println("  Height: " + this.getH());
    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(this.getX(),this.getY(),this.getW(),this.getH());
    }

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        this.value = Integer.parseInt(split[0]);
        this.setY(Integer.parseInt(split[1]));
        this.setX(Integer.parseInt(split[2]));
        this.setW(Integer.parseInt(split[3]));
        this.setH(Integer.parseInt(split[4]));
        ImageIcon icon = GameLoad.imgMap.get(split[5]);
        this.setIcon(icon);
//        System.out.println("createElement method called:");
//        System.out.println("  Value: " + this.value);
//        System.out.println("  Y: " + this.getY());
//        System.out.println("  X: " + this.getX());
//        System.out.println("  Width: " + this.getW());
//        System.out.println("  Height: " + this.getH());
//        System.out.println("  Icon: " + split[5]);
        return this;
    }
}
