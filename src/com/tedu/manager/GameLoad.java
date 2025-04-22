package com.tedu.manager;

import com.tedu.element.ElementObj;
import com.tedu.show.UIConfig;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GameLoad {
	private static final ElementManager em =ElementManager.getManager();
//	图片集合 使用map来进行存储
	public static Map<String,List<ImageIcon>> imgMaps;
	public static Map<String, ImageIcon> imgMap=new HashMap<>();
	private static Map<String, Class<?>> objMap = new HashMap<>();
	private static Properties pro = new Properties();

	/**
	 * &#064;说明  imgMap集合里存放了所有图片的url及对应键
	 */
	public static void loadImg() {
		String textUrl = "com/tedu/text/GameData.properties";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(textUrl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();
			for (Object o : set) {
				String key = o.toString();
				String url = pro.getProperty(key);
				imgMap.put(key,ImageUtil.getImgIcon(url));
//				System.out.println("Loaded image: key=" + key+ ", value=" + imgMap.get(key));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadPlay() {
		String Str= UIConfig.WINDOW_WIDTH/2+","+(UIConfig.WINDOW_HEIGHT-100);
		em.addElement(getObj("play").createElement(Str), GameElement.PLAY);
	}
	public static void loadNet() {
		String Str="net1,100,200";
		ElementObj net = getObj("net").createElement(Str);
		em.addElement(net, GameElement.NET);
	}
	public static void loadScore() {
		em.addElement(getObj("score").createElement(""), GameElement.SCORE);
	}
	public static void loadBulletCount() {
		em.addElement(getObj("bullet_count").createElement(""), GameElement.BULLET_COUNT);
	}
	public static void loadFirePower() {
		em.addElement(getObj("firepower").createElement(""), GameElement.FIREPOWER);
	}
	public static void loadInfo() {
		em.addElement(getObj("info").createElement(""), GameElement.INFO);
	}

	public static void loadBar() {
		em.addElement(getObj("bar").createElement(""), GameElement.BAR);
	}
	public static void loadFish(int x) {
		for(int i=0;i<x;i++) {
			em.addElement(getObj("fish").createElement(""), GameElement.FISH);
		}
	}
	public static ElementObj getObj(String str) {
		Class<?> class1 = objMap.get(str);
		ElementObj obj =null;
		Object newInstance;
		try {
			newInstance = class1.newInstance();
			if(newInstance instanceof ElementObj) {
			obj=(ElementObj)newInstance;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 实现类反射
	 */
	public static void loadObj() {
		String textUrl = "com/tedu/text/obj.properties";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(textUrl);
//		System.out.println("---"+texts);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set= pro.keySet();
//			System.out.println("---"+texts);
			for(Object o:set) {
				String url = pro.getProperty(o.toString());
				Class<?> forName = Class.forName(url);//使用反射的方法获取类
				objMap.put(o.toString(),forName);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
