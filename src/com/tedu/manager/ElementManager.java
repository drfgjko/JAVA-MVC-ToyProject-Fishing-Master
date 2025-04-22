package com.tedu.manager;

import com.tedu.element.ElementObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类是元素管理器，专门存储所有的元素，同时，提供方法
 * 给视图和控制获取数据
 * 问题一：怎么存放所有数据？list map set三大集合
 * 问题二：管理器必须只有一个，单例模式（内存中有且只有一个实例）
 */


public class ElementManager {

	//	匹配所有元素
	private Map<GameElement, List<ElementObj>> gameElements;	
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
//	添加元素
	public void addElement(ElementObj obj,GameElement ge) {
		gameElements.get(ge).add(obj);
	}
//	依据key取出List
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}
	
	private static ElementManager EM; 
//	synchronized线程锁，保证本方法执行中只有一个线程
	public static synchronized ElementManager getManager() {
		if(EM ==null) {
			EM=new ElementManager();
		}
		return EM;
	}
//	私有化构造方法
	private ElementManager() {
		init();
	}
	
//	实例化在这里完成；为将来重写init方法准备
	public void init() {
		gameElements=new HashMap<GameElement,List<ElementObj>>();
		for(GameElement ge:GameElement.values()) {
			gameElements.put(ge,new ArrayList<ElementObj>());
		}
	}
	
}
