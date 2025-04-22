package com.tedu.show;

import com.tedu.manager.ImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @description:游戏初始页面
 * @author: ManolinCoder
 */
public class GameMenuJpanel extends JPanel {
    private Image menuBg;
    public static JButton startButton;
    public static JButton modeButton;

    public GameMenuJpanel(){
        setLayout(null); // 设置布局管理器为 null
        //		开始游戏按钮
        JButton startButton = new JButton("");
        startButton.setBounds(300, 210, 210, 50); // 设置按钮位置和大小
        startButton.setOpaque(false); // 设置按钮为透明
        startButton.setContentAreaFilled(false); // 设置内容区域不填充
        startButton.setBorderPainted(false); // 设置不绘制边框
        this.startButton=startButton;
        this.add(startButton);

//        选择模式按钮
        JButton modeButton = new JButton("");
        modeButton.setBounds(300, 270, 210, 50); // 设置按钮位置和大小
        modeButton.setOpaque(false); // 设置按钮为透明
        modeButton.setContentAreaFilled(false); // 设置内容区域不填充
        modeButton.setBorderPainted(false); // 设置不绘制边框
        this.modeButton=modeButton;
        this.add(modeButton);

//       设置背景图片
        menuBg= ImageUtil.getImg("resources/main.png");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(menuBg,0,0, UIConfig.WINDOW_WIDTH,UIConfig.WINDOW_HEIGHT-35,null);

    }


}
