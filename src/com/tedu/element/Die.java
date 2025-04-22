package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

public class Die extends ElementObj{
    private int value;
    private int count=0;//计数器
    public Die(){}
    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), null);
    }

    @Override
    protected void updateImage() {
        count++;
        this.setIcon(GameLoad.imgMap.get("score"+this.value));
        if (count % 15 == 0) {
            this.setLive(false);
        }
    }

    @Override
    public ElementObj createElement(String str) {
        String[] split = str.split(",");
        this.value = Integer.parseInt(split[0]);
        this.setX(Integer.parseInt(split[1]));
        this.setY(Integer.parseInt(split[2]));
        ImageIcon icon = GameLoad.imgMap.get("score"+this.value);
        this.setIcon(icon);
        return this;
    }
}
