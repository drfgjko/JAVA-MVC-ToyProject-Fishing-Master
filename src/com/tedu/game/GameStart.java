package com.tedu.game;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;
import com.tedu.show.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 程序的唯一入口
 */
public class GameStart {
	public static void main(String[] args) {
		GameJFrame gameJf = new GameJFrame();//实例化初始面板
		GameMenuJpanel gameMenuJp= new GameMenuJpanel();//菜单面板
		GameModeJPanel gameModeJp = new GameModeJPanel(); //选择模式面板
		BackgroundMusicPlayer musicPlayer = new BackgroundMusicPlayer("resources/music.wav");
		musicPlayer.play();
		//注入
		gameJf.setGameMenuJp(gameMenuJp);//初始为菜单面板
		gameJf.start();//显示窗体

//		设置开始游戏按钮监听事件
		GameMenuJpanel.startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameMainJPanel gameMainJp=new GameMainJPanel(0);//游戏面板
				gameMenuJp.setVisible(false);
				gameMainJp.setVisible(true);
				GameListener listener = new GameListener();//监听
				GameThread th=new GameThread(gameMainJp,gameJf);//游戏主线程
				//注入
				gameJf.setContentPane(gameMainJp);
				gameJf.addMouseListener(listener);
				gameJf.addMouseMotionListener(listener);
				gameJf.setThread(th);
				gameMainJp.gameStart();//不调用这条的话gameMainJp不会repaint
				th.start(); // 启动游戏主线程
			}
		});

//		设置选择模式按钮监听事件
		GameMenuJpanel.modeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameMenuJp.setVisible(false);
				gameModeJp.setVisible(true);
				gameJf.add(gameModeJp);
			}
		});

		//选择模式面板监听事件
		int BgId=0;
		for(JButton button : GameModeJPanel.modeButtons){
			GameMainJPanel gameMainJp=new GameMainJPanel(BgId++);//游戏面板
//			System.out.println(BgId);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gameModeJp.setVisible(false);
					gameMainJp.setVisible(true);
					GameListener listener = new GameListener();//监听
					GameThread th=new GameThread(gameMainJp,gameJf);//游戏主线程
					//注入
					gameJf.setContentPane(gameMainJp);
					gameJf.addMouseListener(listener);
					gameJf.addMouseMotionListener(listener);
					gameJf.setThread(th);
					gameMainJp.gameStart();//不调用这条的话gameMainJp不会repaint
					th.start(); // 启动游戏主线程
				}
			});
		}
	}


}
