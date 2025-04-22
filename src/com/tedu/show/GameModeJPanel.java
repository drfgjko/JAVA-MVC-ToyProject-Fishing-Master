package com.tedu.show;

import com.tedu.manager.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @description:选择场景面板
 * @author: ManolinCoder
 */
public class GameModeJPanel extends JPanel {
    private Image modeBg;
    public static ArrayList<JButton> modeButtons = new ArrayList<>();
    public GameModeJPanel(){
        // 设置背景图片
        modeBg= ImageUtil.getImg("resources/modeBg.png");

//        this.button=button;
//        this.add(modeButton1);

        // 循环创建按钮并添加到 JPanel 中
        setLayout(new GridBagLayout()); // 使用GridBagLayout布局管理器

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // 设置组件间的间距

        // 创建按钮，并设置大小和位置
        for (int i = 1; i <= 6; i++) {
            JButton button = new JButton("Button " + i);
            button.setPreferredSize(new Dimension(240, 150)); // 设置按钮大小为50x50像素
            button.setBounds(300, 210, 300, 80); // 设置按钮位置和大小
            button.setOpaque(false); // 设置按钮为透明
            button.setContentAreaFilled(false); // 设置内容区域不填充
            button.setBorderPainted(false); // 设置不绘制边框

            // 设置按钮在网格中的位置，靠下
            gbc.gridx = (i - 1) % 3; // 列
            gbc.gridy = (i - 1) / 3; // 行

            modeButtons.add(button);
            add(button, gbc);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(modeBg,0,0, UIConfig.WINDOW_WIDTH-15,UIConfig.WINDOW_HEIGHT-35,null);

    }
}
