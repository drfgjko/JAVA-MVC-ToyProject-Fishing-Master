package com.tedu.controller;

import com.tedu.element.ElementObj;
import com.tedu.element.Play;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;

import java.awt.event.*;
import java.util.List;

/**
 *监听类，用于监听用户的操作MouseListener, MouseMotionListener
 */
public class GameListener implements MouseListener, MouseMotionListener {
//	关联元素管理器
	private final ElementManager em = ElementManager.getManager();


	@Override
	public void mouseClicked(MouseEvent e) {
		switch (e.getButton()) {
			case MouseEvent.BUTTON1 -> {//左键
				List<ElementObj> play = em.getElementsByKey(GameElement.PLAY);
				List<ElementObj> net = em.getElementsByKey(GameElement.NET);
				List<ElementObj> bulletCount = em.getElementsByKey(GameElement.BULLET_COUNT);
				play.get(0).mouseClick(true,e.getX(), e.getY());
				net.get(0).mouseClick(true,e.getX(), e.getY());
				bulletCount.get(0).mouseClick(true,e.getX(), e.getY());
			}
			case MouseEvent.BUTTON3 -> {//右键
				List<ElementObj> firePower = em.getElementsByKey(GameElement.FIREPOWER);
				List<ElementObj> net = em.getElementsByKey(GameElement.NET);
				firePower.get(0).mouseClick(false,e.getX(),e.getY());
				for (ElementObj obj : net) {
					obj.mouseClick(false,e.getX(), e.getY());
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		List<ElementObj> net = em.getElementsByKey(GameElement.NET);
		for (ElementObj obj : net) {
			obj.mouseMove(e.getX(),e.getY());
		}
		Play play = (Play) em.getElementsByKey(GameElement.PLAY).get(0);
		play.updateRotationAngle(e.getX(), e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		List<ElementObj> net = em.getElementsByKey(GameElement.NET);
		for (ElementObj obj : net) {
			obj.mouseEnter();
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		List<ElementObj> net = em.getElementsByKey(GameElement.NET);
		for (ElementObj obj : net) {
			obj.moueExit();
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {}
}
