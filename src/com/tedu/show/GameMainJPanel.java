package com.tedu.show;

import com.tedu.element.ElementObj;
import com.tedu.element.Fish;
import com.tedu.element.Score;
import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 游戏的主要面板
 * 主要进行元素的显示，同时进行界面的刷新（多线程）
 */
public class GameMainJPanel extends JPanel implements Runnable{
//	联动管理器
	private ElementManager em=null;
	private int mainBgId=0;//	背景图片序号
	private Image mainBg;	//背景图片
	int fishNum = 1;

	private Long gameTime; //游戏时间

	private boolean showStatistics=false;
	//  鱼的集合
	List<Fish> fishes = new ArrayList<Fish>();

	public GameMainJPanel(int mainBgId) {
		this.mainBgId=mainBgId;
		init();
	}
	
	
	public void init() {

		mainBg= ImageUtil.getImg("resources/bg/fishlightbg_"+mainBgId+".jpg");
		em=ElementManager.getManager();
//		将鱼装入集合
		for(int i = 0;i<fishNum;i++){
			fishes.add(new Fish(this));
		}
	}

	//游戏开始
	public void gameStart(){
		for (int i = 0; i<fishNum; i++){
			//获取每一条鱼
			Fish fish = fishes.get(i);
			fish.start();
		}
	}

	public void setMainBgId(int mainBgId) {
		this.mainBgId = mainBgId;
	}

	/**
	 *paint方法是进行绘画。
	 *本方法只实现一次，需要进行多线程刷新。
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//背景
		g.drawImage(mainBg,0,0,UIConfig.WINDOW_WIDTH,UIConfig.WINDOW_HEIGHT-35,null);
		//绘制所有的鱼

		Map<GameElement,List<ElementObj>> all =em.getGameElements();
		for(GameElement ge:GameElement.values()) {
			List<ElementObj> list = all.get(ge);
			for (ElementObj obj : list) {
				obj.showElement(g);
			}
		}
		if (showStatistics) {drawStatisticsBox(g); }
	}

	@Override
	public void run() {
		while(true) {
			this.repaint();
			try {
				Thread.sleep(50); //休眠50毫秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void showStatistics(long gameTime) {
		showStatistics = true;
		this.gameTime=gameTime;
	}
	// 绘制统计信息框
	private void drawStatisticsBox(Graphics g) {

		Image bg=ImageUtil.getImg("resources/bg/setting.jpg");

		g.drawImage(bg,0,0,UIConfig.WINDOW_WIDTH,UIConfig.WINDOW_HEIGHT-35,null);
		Font CUSTOM_FONT;
		try {
			File fontFile = new File("resources/texttype/font1.ttf");
			CUSTOM_FONT = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(Font.PLAIN, 60);
		} catch (FontFormatException | IOException e) {
			throw new RuntimeException(e);
		}
		g.setColor(Color.WHITE);
		g.setFont(CUSTOM_FONT);

		String line0 = "游 戏 结 束";
		int stringWidth0 = g.getFontMetrics().stringWidth(line0);
		g.drawString(line0, (UIConfig.WINDOW_WIDTH - stringWidth0) / 2,  100);

		// 第一行文字
		g.setColor(Color.ORANGE);
		g.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		Score score= (Score) em.getElementsByKey(GameElement.SCORE).get(0);
		String line1 = "本关得分： " + score.getValue() + " 分";
		int stringWidth1 = g.getFontMetrics().stringWidth(line1);
		g.drawString(line1, (UIConfig.WINDOW_WIDTH - stringWidth1) / 2,  200);

		// 第二行文字
		long gameTimeInSeconds = gameTime * 15 / 1000;
		String line = "游戏时间： " + gameTimeInSeconds +" 秒";
		int stringWidth = g.getFontMetrics().stringWidth(line1);
		g.drawString(line, (UIConfig.WINDOW_WIDTH - stringWidth) / 2, 250);

		// 第三行文字
//		g.setColor(Color.YELLOW);
		double rate = score.getValue()+100.0/gameTimeInSeconds;
		String line3 = "综合表现： ";
		if(rate>200) line3+="A";
		else if(rate>100) line3+="B";
		else if(rate>50) line3+="C";
		else line3+="D";
		int stringWidth3 = g.getFontMetrics().stringWidth(line1);
		g.drawString(line3, (UIConfig.WINDOW_WIDTH - stringWidth3) / 2, 300);

		// 第四行文字
		g.setColor(Color.WHITE);
		g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
		String line2;
		line2 = "即将自动关闭页面...";
		int stringWidth2 = g.getFontMetrics().stringWidth(line2);
		g.drawString(line2, (UIConfig.WINDOW_WIDTH - stringWidth2) / 2, 400);
	}


	public void hideStatistics() {
		showStatistics=false;
	}
}
