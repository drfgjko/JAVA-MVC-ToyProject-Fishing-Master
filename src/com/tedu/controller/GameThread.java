package com.tedu.controller;

import com.tedu.element.BulletCount;
import com.tedu.element.ElementObj;
import com.tedu.element.Score;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;
import com.tedu.show.GameMainJPanel;
import com.tedu.show.GameMenuJpanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *&#064;说明  游戏的主线程，用于控制游戏加载、游戏关卡，游戏运行时自动化
 */
public class GameThread extends Thread{
	private final ElementManager em;
	private boolean gameRunning;
	private JFrame jFrame;
	private GameMainJPanel gamePanel; // 引用 GameMainJPanel 对象

	private GameMenuJpanel gameMenuJpanel;
	
	public GameThread(GameMainJPanel gamePanel, JFrame jFrame){
		this.gamePanel = gamePanel;
		this.jFrame=jFrame;
		em=ElementManager.getManager();
		gameRunning = false;
	}
	
	@Override
	public void run() {
		gameLoad();//游戏开始前:读进度条，加载游戏资源
		gameRunning = true;
        while (gameRunning) {
            gameRun(); // 游戏进行中
			gameRunning = false;//跳出后设置游戏状态
        }
		gameOver();//游戏场景结束 游戏资源回收
	}
	
	/**
	 * @说明 游戏的加载
	 */
	private void gameLoad() {
		GameLoad.loadObj();//实现类反射
		GameLoad.loadImg(); //加载图片
		GameLoad.loadScore();//加载分数
		GameLoad.loadBulletCount();//加载子弹数
		GameLoad.loadFirePower();//加载火力值
		GameLoad.loadInfo();//加载提示句
		GameLoad.loadPlay();//加载主角 -> 炮台
		GameLoad.loadBar();//加载底部栏
		GameLoad.loadNet();//加载渔网
		GameLoad.loadFish(50);//加载鱼类
		System.out.println("资源加载完毕");
	}

	/**
	 * 游戏进行时
	 */
	private long gameTime=0L;
	private void gameRun() {
		while(true) {
			Map<GameElement,List<ElementObj>> all =em.getGameElements();
			Score score= (Score) em.getElementsByKey(GameElement.SCORE).get(0);
			BulletCount bulletCount= (BulletCount) em.getElementsByKey(GameElement.BULLET_COUNT).get(0);
			List<ElementObj> fishes = em.getElementsByKey(GameElement.FISH);
			List<ElementObj> playfiles = em.getElementsByKey(GameElement.PLAYFILE);

			moveAndUpdate(all);
			catchFish(playfiles,fishes);

			gameTime++;
			try {
				sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//结束条件：子弹数用完
			if(bulletCount.getValue()==-1) break;
		}
	}

	private void gameOver() {
//		System.out.println("游戏结束");
		gamePanel.showStatistics(gameTime); // 显示统计结果
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Map<GameElement, List<ElementObj>> all = em.getGameElements();
		for (GameElement ge : GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			if (list != null) {
				list.clear();
			}
		}
		jFrame.dispose();
		System.exit(0);
	}

	//返回菜单

    
	//游戏元素自动化方法
	private void moveAndUpdate(Map<GameElement,List<ElementObj>> all) {
		ElementObj score = all.get(GameElement.SCORE).get(0);
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			List<ElementObj> toBeRemoved = new ArrayList<>(); // 用于存储需要被删除的元素

			for(ElementObj obj : list) {
				if(!obj.isLive()) {
					obj.die();//启动一个死亡方法
					obj.toScore(score);//获取分数
					toBeRemoved.add(obj);
					continue;
				}
				obj.model(gameTime);
			}
			list.removeAll(toBeRemoved);
		}
	}


	private void catchFish(List<ElementObj>  playfiles,List<ElementObj> fishes) {
		for (ElementObj playfile : playfiles) {
			for (ElementObj fish : fishes) {
				if (playfile.hasIntersection(fish)) {
					playfile.setLive(false);
					fish.setLive(false);
				}
				playfile.setLive(false);
			}
		}
	}

	private boolean isCatch(ElementObj catched,ElementObj beCatched){
		boolean is= beCatched.getX()<= catched.getX()+catched.getW() &&
				beCatched.getY()>=catched.getX()-beCatched.getW()
				&& beCatched.getY()<=catched.getY()+catched.getH()
				&& beCatched.getY()>=catched.getY()-beCatched.getH();
		return is;
	}
}
