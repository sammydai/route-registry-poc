package cn.registry;

import java.util.Map;

/**
 * @Package: cn.registry
 * @Description: Directory Component 注册对象结构
 * @Author: Sammy
 * @Date: 2022/8/13 09:43
 */

public abstract class DirectoryComponent {

	public abstract DirectoryComponent addComponent(DirectoryComponent c);

	public abstract void remove(DirectoryComponent c);

	public abstract Map<String,DirectoryComponent> getChildren();

	public abstract void operation();

	public void printList() {
		printList("");
	}

	protected abstract void printList(String prefix);

	public abstract String getName();

	public abstract String getType();

	public String toString() {
		return getName();
	}
}
