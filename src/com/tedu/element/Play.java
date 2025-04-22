package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

/**
 *  玩家类->炮台
 *  动作  左键调整图片角度
 */
public class Play extends ElementObj{
	private int rotationAngle = 0; // 旋转角度
	private Point center; // 图片中心点位置
	public Play() {}

	@Override
	public void mouseClick(boolean bl, int x, int y) {
		if(bl){//左键->调整角度
			updateRotationAngle(x, y);
		}
	}

	public void updateRotationAngle(int mouseX, int mouseY) {
		// 计算鼠标当前位置与 Play 中心的角度
		double dx = mouseX - (this.getX() + this.getW() / 2);
		double dy = mouseY - (this.getY() + this.getH() / 2);
		rotationAngle = (int) Math.toDegrees(Math.atan2(dy, dx))+90;
	}


	protected void updateImage() {
		this.setIcon(GameLoad.imgMap.get("play"));
	}

	@Override
	public void showElement(Graphics g) {
//		g.drawImage(this.getIcon().getImage(),
//				this.getX(),
//				this.getY(),
//				this.getW(),
//				this.getH(),
//				null);
		Graphics2D g2d = (Graphics2D) g.create();

		// 平移和旋转绘图上下文
		center = new Point(this.getX() + this.getW() / 2, this.getY() + this.getH() / 2);
		g2d.translate(center.x, center.y);
		g2d.rotate(Math.toRadians(rotationAngle));

		// 绘制图片
		g2d.drawImage(this.getIcon().getImage(),
				-this.getW() / 2,
				-this.getH() / 2,
				this.getW(),
				this.getH(),
				null);

		g2d.dispose();
	}

	@Override
	public void move() {}

	@Override
	public ElementObj createElement(String str) {
		String[] split= str.split(",");
		this.setX(Integer.parseInt(split[0]));
		this.setY(Integer.parseInt(split[1]));
		ImageIcon icon = GameLoad.imgMap.get("play");
		this.setW(icon.getIconWidth());
		this.setH(icon.getIconHeight());
		this.setIcon(icon);
		return this;
	}

	@Override
	public String toString() {
		int x=this.getX();
		int y=this.getY();
		return "x:"+x+",y:"+y;
	}
}
