package com.tedu.element;

import java.awt.*;

/**
 * 提示类
 */
public class Info extends ElementObj{
    private static String text = "(点击鼠标右键提升渔网等级)";
    public static void setText(String text) {
        Info.text = text;
    }

    private static final int X = 580;
    private static final int Y = 50;
    public Info(){}
    @Override
    public void showElement(Graphics g) {
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        g.drawString(text,this.getX(),this.getY());
    }

    @Override
    public ElementObj createElement(String str){//解析字符串后创建
        this.setX(X);
        this.setY(Y);
        return this;
    }
}
