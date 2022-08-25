package cn.observer;

import cn.registry.RegistryObject;

import java.util.Vector;

/**
 * @Package: cn.observer
 * @Description: Subject
 * @Author: Sammy
 * @Date: 2022/8/25 14:54
 */

public class Subject {

	//观察者数组
	private Vector<Observer> oVector = new Vector<>();

	//增加一个观察者，相当于观察者注册
	public void addObserver(Observer observer) {
		this.oVector.add(observer);
	}

	//删除一个观察者
	public void deleteObserver(Observer observer) {
		this.oVector.remove(observer);
	}

	//通知所有观察者，主题有变化时通知观察者
	public void notifyObserver(RegistryObject refreshRegistryObject) {
		for (Observer observer : this.oVector) {
			observer.updateContract(refreshRegistryObject);
		}
	}

}


