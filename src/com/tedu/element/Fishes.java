package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.ImageUtil;
import com.tedu.show.UIConfig;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.tedu.manager.GameLoad.getObj;

/**
 * 鱼群:使用框架加载鱼群
 */
public class Fishes extends ElementObj{
    private int value;//分值,同index
    private static final int MAX_MOVE_DISTANCE = 3;//随机移动速度
    public void setValue(int value) {this.value = value;}
    private Random random = new Random();
    public Fishes(){}
    List<ImageIcon> animation = new ArrayList<>();//定义一条鱼游动的图片集合
    private int moveNum = 0;//精灵图的index
    private int moveCount = 0;//计数器
    @Override
    protected void updateImage() {
        moveCount++;
        if (moveCount % 5 == 0) {
            this.setIcon(animation.get((moveNum++) % 10));
        }
    }
    @Override
    protected void move() {
        int deltaX = random.nextInt(MAX_MOVE_DISTANCE);
        this.setX(this.getX() - deltaX);
        if(this.getX() <= (-this.getW()-80)){//鱼游到左边界
            this.setX(UIConfig.WINDOW_WIDTH); //重新出现在鱼塘右边
            this.setY(Math.max(50,random.nextInt(UIConfig.WINDOW_HEIGHT-getH()-80)));
        }
    }
    @Override
    public void showElement(Graphics g) {
        g.drawImage(this.getIcon().getImage(), this.getX(), this.getY(), null);
    }

    @Override
    public void die() {
        String str=this.value+","
                +this.getX()+","
                +this.getY()+",";
        ElementManager.getManager().addElement(getObj("die").createElement(str), GameElement.DIE);
    }

    public void toScore(ElementObj obj){
        Score score = (Score)obj;
        score.setValue(score.getValue()+this.value);
    }

    @Override
    public Rectangle getRectangle(){
        return new Rectangle(this.getX(),this.getY(),this.getW(),this.getH());
    }

    @Override
    public ElementObj createElement(String str) {
        int index = random.nextInt(9)+1;
        //拼接鱼种类的名字
        String fishName = "resources/img/fish0" + index+ "_";
        //加载鱼的游动
        for(int i = 1;i<11;i++){
            String prefix = (i==10?"":"0") + i;
            //完整路径
            String path = fishName + prefix + ".png";
            ImageIcon imgIcon = ImageUtil.getImgIcon(path);
            animation.add(imgIcon);
        }
        ImageIcon icon =animation.get(0);
        this.setX(random.nextInt(UIConfig.WINDOW_WIDTH -this.getW())) ;
        this.setY(Math.max(50,random.nextInt(UIConfig.WINDOW_HEIGHT-getH()-80)));
//        System.out.println(getY());
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
        this.setIcon(icon);
        this.setValue(index);
        this.setLive(true);
        return this;
    }
}
