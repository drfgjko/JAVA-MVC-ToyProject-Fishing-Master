package com.tedu.element;

import com.tedu.manager.ImageUtil;
import com.tedu.show.GameMainJPanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 鱼类，负责鱼类的绘制
 */
public class Fish extends Thread{
    //鱼的属性
    // 图片（绘制）
    public Image img;

    // 坐标（x，y）,体型（w，h）
    public int x;
    public int y;

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int w;
    public int h;
    //随机数对象
    Random random = new Random();
    //定义一条鱼游动的图片集合
    List<Image> animation = new ArrayList<Image>();
    //定义一个面板
    GameMainJPanel panel;

    public Fish(GameMainJPanel panel){
        this.panel = panel;
        //随机鱼的种类
        int index = random.nextInt(9)+1;
        //拼接鱼种类的名字
        String fishName = "resources/img/fish0" + index+ "_";
        //加载鱼的游动
        for(int i = 1;i<11;i++){
            String prefix = (i==10?"":"0") + i;
            //完整路径
            String path = fishName + prefix + ".png";
            //读取图片
            Image img = ImageUtil.getImg(path);
            //装入集合
            animation.add(img);
        }
        //显示每条鱼的01图片(初始状态)
        img = animation.get(0);
        //获取鱼的宽度w和高度h
        w = getW();
        h = getH();
        //鱼的坐标
        x = random.nextInt(800-w);
        y = random.nextInt(400-h)+40;
//        System.out.println(y);
    }

    //鱼移动的距离
    int moveNum = 0;
    //鱼的移动
    public void move(){
        //鱼的移动距离增加
        moveNum++;
        //切换图片
        img = animation.get(moveNum%10);
        //重新获取切换后图片的宽高（防止图片衔接变形）
        w = getW();
        h = getH();
        //鱼向左移动(随机速度)
        x -= random.nextInt(5)+1;
        //边界判断（鱼循环出现）
        if(x <= (-w-80)){//鱼游到左边界
            //重新出现在鱼塘右边
            x=800;
            y=random.nextInt(400-h)+40;
        }
    }

    /**
     * 线程启动执行
     */
    @Override
    public void run() {
        //让鱼一直动（死循环）
        for (;;){
            move();
            try {
                Thread.sleep(80);
                panel.repaint();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
