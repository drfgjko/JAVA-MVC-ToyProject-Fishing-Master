package com.tedu.show;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 游戏窗体
 * 需要嵌入面板，启动主线程等等
 1.面板绑定窗体
 * 2.监听绑定
 * 3.游戏主线程启动
 * 4.显示窗体
 */

public class GameJFrame extends JFrame{
//	public static int GameX = 800;
//	public static int GameY = 480;
	public static int GameX = UIConfig.WINDOW_WIDTH;
	public static int GameY = UIConfig.WINDOW_HEIGHT;
// 游戏初始页面面板
	private GameMenuJpanel GameMenuJp = null;
//	游戏主界面面板
	private GameMainJPanel GameMainJp = null;

	private KeyListener keyListener = null;
	private MouseMotionListener mouseMotionListener=null;
	private MouseListener mouseListener=null;
	private Thread thread=null; //游戏主线程
	
	public GameJFrame() {
		init();
	}
	public void init() {
		this.setSize(GameX, GameY);
		this.setTitle("捕鱼达人");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭并且退出
		this.setLocationRelativeTo(null); //屏幕居中显示
	}

	
//	启动方法
	public void start() {
		if(GameMenuJp!=null) {
			this.add(GameMenuJp);
		}

		if(mouseMotionListener!=null) {
			this.addMouseMotionListener(mouseMotionListener);
		}
		if(mouseListener!=null) {
			this.addMouseListener(mouseListener);
		}
		this.setVisible(true);


	}
	
//	set注入
	public void setGameMainJp(GameMainJPanel GameMainJp) {
		this.GameMainJp = GameMainJp;
	}
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public void setGameMenuJp(GameMenuJpanel gameMenuJp) {
		GameMenuJp = gameMenuJp;
//		System.out.println("set:"+this.GameMenuJp);
	}

}
