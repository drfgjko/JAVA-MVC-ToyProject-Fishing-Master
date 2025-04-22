package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;

import static com.tedu.manager.GameLoad.getObj;

/**
 *  渔网类-> 实际作用为一个准头表示
 */
public class Net extends ElementObj {
    private int value = 1;//火力值
    private int x;
    private int y;
    private int w;
    private int h;
    protected ImageIcon icon;
    private boolean live=true;
    private boolean visible=true;
    private boolean atk=false;//攻击状态
    public boolean isAtk() {return atk;}
    public void setAtk(boolean atk) {this.atk = atk;}

    private String fx;//网的状态
    public Net(){}
    protected void updateImage() {}//图片更新写在监听方法里

    @Override
    public void showElement(Graphics g) {
        if(visible){
            g.drawImage(this.getIcon().getImage(),
                    this.getX(),this.getY(),
                    this.getW(),this.getH(), null);
        }
    }

    @Override
    public void mouseClick(boolean bl,int x, int y) {
        if(bl){//左键
            add(x,y);
        }else{//右键
            this.value++;
            this.value=this.value>7?1:this.value;
            this.fx="net"+value;
            ImageIcon icon = GameLoad.imgMap.get(fx);
            this.setW(icon.getIconWidth()+this.value);
            this.setH(icon.getIconHeight()+this.value);
            this.setIcon(icon);
            this.setX(x - this.getW() / 2-2);
            this.setY(y - this.getH() / 2-18);
        }
    }

    @Override
    protected void add(int x, int y) {
        String str=this.value+","
                +this.getY()+","
                +this.getX()+","
                +this.getW()+","
                +this.getH()+","
                +this.fx;
        ElementManager.getManager().addElement(getObj("playfile").createElement(str), GameElement.PLAYFILE);
    }

    @Override
    public void mouseMove(int x, int y) {
        if(!this.visible){
            this.visible=true;
        }
        this.setX(x - this.getW() / 2-2);
        this.setY(y - this.getH() / 2-18);
    }

    @Override
    public void mouseEnter() {this.visible=true;}
    @Override
    public void moueExit() {this.visible=false;}

    /**
     * &#064;说明  监听鼠标创建网
     * @param str 传入fx:,x:,y:
     */
    @Override
    public ElementObj createElement(String str) {
        String []arr = str.split(",");
        switch (arr[0]) {
            case "net1":
                this.fx="net1";
                this.value=1;
                break;
            case "net2":
                this.fx="net2";
                this.value=2;
                break;
            case "net3":
                this.fx="net3";
                this.value=3;
                break;
            case "net4":
                this.fx="net4";
                this.value=4;
                break;
            case "net5":
                this.fx="net5";
                this.value=5;
                break;
            case "net6":
                this.fx="net6";
                this.value=6;
                break;
            case "net7":
                this.fx="net7";
                this.value=7;
                break;
        }
        ImageIcon icon = GameLoad.imgMap.get(fx);
        this.setX(Integer.parseInt(arr[1]));
        this.setY(Integer.parseInt(arr[2]));
        this.setW(icon.getIconWidth()+this.value);
        this.setH(icon.getIconHeight()+this.value);
        this.visible=false;
        this.setIcon(icon);
        return this;
    }

}
