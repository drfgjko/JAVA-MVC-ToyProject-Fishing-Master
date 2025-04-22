package com.tedu.element;

import com.tedu.manager.GameLoad;
import com.tedu.show.UIConfig;

import javax.swing.*;
import java.awt.*;

/**
 * 底部栏
 */
public class Bar extends ElementObj{
    public Bar(){}
    @Override
    public void showElement(Graphics g) {
//       g.drawImage(bottomBar,0,bottomBarY,bottomBarWidth,bottomBarHeight,null);
        g.drawImage(this.getIcon().getImage(),
                this.getX(),
                this.getY(),
                this.getW(),
                this.getH(),
                null);
    }

    @Override
    public ElementObj createElement(String str) {
        ImageIcon icon = GameLoad.imgMap.get("bar");
        int bottomBarY = UIConfig.WINDOW_HEIGHT-icon.getIconHeight()-35;
        this.setX(0);
        this.setY(bottomBarY);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        this.setIcon(icon);
        this.setLive(true);
        return this;
    }
}
