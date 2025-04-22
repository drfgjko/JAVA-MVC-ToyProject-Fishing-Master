package com.tedu.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * 所有元素的基类
 */
public abstract class ElementObj {
	private int x;
	private int y;
	private int w;
	private int h;
	protected ImageIcon icon;
	private boolean live=true; //生存状态 true代表存在
	public ElementObj() {}
	
	/**带参数的构造方法；可以由子类传输数据到父类
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param icon
	 */
	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	
	/**
	 * 抽象方法：显示元素
	 * @param g 画笔用于绘画
	 */
	public abstract void showElement(Graphics g);
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public void mouseClick(boolean bl,int x,int y) {}
	public void mouseMove(int x,int y) {}
	public void mouseEnter() {}
	public void moueExit() {}
	/**
	 * 模板模式；在模板模式中定义对象执行方法的先后顺序,由子类选择性重写方法
	 * @param gameTime 
	 */
	public final void model(long gameTime) {
		updateImage();
		move();
	}

	public void die() {}
	
	/**
	 * 移动方法
	 */
	protected void move() {}
	protected void add(int x,int y) {}

	protected void updateImage() {}
	
	public ElementObj createElement(String str) {
		return null;
	}
	public void toScore(ElementObj score){}
	public Rectangle getRectangle(){return new Rectangle(x,y,h,w);}
	public boolean hasIntersection(ElementObj obj) {
//	    交集部分
		Rectangle intersection = this.getRectangle().intersection(obj.getRectangle());
		return !intersection.isEmpty();
	}
}
