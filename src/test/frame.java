package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @description:
 * @author: ManolinCoder
 */
public class frame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game Frame");
        frame.setSize(800,620);
        GamePanel gamePanel = new GamePanel();
        OtherPanel otherPanel = new OtherPanel();

        frame.add(gamePanel);
//

        // 设置初始显示的面板
        gamePanel.setVisible(true);
//        otherPanel.setVisible(false);

        JButton switchButton = new JButton("Switch to Other Panel");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setVisible(false);
                frame.add(otherPanel);
                otherPanel.setVisible(true);
            }
        });

        gamePanel.add(switchButton);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
