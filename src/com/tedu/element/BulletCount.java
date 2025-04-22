package com.tedu.element;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 子弹数
 */
public class BulletCount extends ElementObj{
    private static final Font CUSTOM_FONT;
    private static final int X = 300;
    private static final int Y = 30;
    private int value;
    private String str="渔网数: " + value;
    public int getValue() {return value;}
    public void setValue(int value) {this.value = value;}

    static {
        try {
            File fontFile = new File("resources/texttype/font1.ttf");
            CUSTOM_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 20);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BulletCount(){}

    /**
     * 这里用来更新字符串
     */
    @Override
    protected void updateImage() {
        this.str =  "渔网数: " + value;
    }

    @Override
    public void showElement(Graphics g) {
//        System.out.println("BulletCount:显示");
        g.setColor(Color.WHITE);
        g.setFont(CUSTOM_FONT);
        g.drawString(str,this.getX(), this.getY());
    }

    @Override
    public void mouseClick(boolean bl,int x, int y) {
        if(bl){//左键
            this.value--;
        }
    }

    @Override
    public ElementObj createElement(String str){//解析字符串后创建
//        System.out.println("BulletCount:实例化");
        this.setX(X);
        this.setY(Y);
        this.setValue(10);
        return this;
    }
}
