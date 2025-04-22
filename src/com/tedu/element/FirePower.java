package com.tedu.element;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 *  渔网等级
 */
public class FirePower extends ElementObj{
    private static final Font CUSTOM_FONT;
    private static final int X = 600;
    private static final int Y = 30;
    private int value;
    private String str="渔网等级: " + value;
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
    public FirePower(){}

    /**
     * &#064;说明  这里用来更新字符串
     */
    @Override
    protected void updateImage() {
        this.str =  "渔网等级: " + value;
    }

    @Override
    public void showElement(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(CUSTOM_FONT);
        g.drawString(str,this.getX(), this.getY());
    }

    @Override
    public void mouseClick(boolean bl,int x, int y) {
        if(!bl) {
            this.value++;
            this.value=this.value>7?1:this.value;
        }
    }

    @Override
    public ElementObj createElement(String str){//解析字符串后创建
//        System.out.println("FirePower:实例化");
        this.setX(X);
        this.setY(Y);
        this.setValue(1);
        return this;
    }
}
