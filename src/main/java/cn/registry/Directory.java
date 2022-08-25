package cn.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: cn.registry
 * @Description: Directory 目录节点
 * @Author: Sammy
 * @Date: 2022/8/12 12:13
 */

public class Directory extends DirectoryComponent{

	private String name;

	private String type;

	private Map<String, DirectoryComponent> directoryComponentMap = new ConcurrentHashMap<>();

	@Override
	public DirectoryComponent addComponent(DirectoryComponent c) {
		if (c.getType().equals("ip")) {
			File originalFile = (File) directoryComponentMap.get(c.getName());
			if (originalFile != null) {
				File mergedFile = File.mergeFileMetaData(originalFile, (File) c);
				return directoryComponentMap.put(c.getName(), mergedFile);
			} else {
				return directoryComponentMap.put(c.getName(), c);
			}
		}
		return directoryComponentMap.putIfAbsent(c.getName(), c);
	}

	@Override
	public void remove(DirectoryComponent c) {
	}

	@Override
	public Map<String,DirectoryComponent> getChildren() {
		return directoryComponentMap;
	}

	@Override
	public void operation() {
	}

	@Override
	protected void printList(String prefix) {
		System.out.println(prefix + "/" + this);
		directoryComponentMap.forEach((k,v)-> v.printList(prefix + "/" + name));
	}

	public Directory(String name, String type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setDirectoryComponentMap(Map<String, DirectoryComponent> directoryComponentMap) {
		this.directoryComponentMap = directoryComponentMap;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Directory directory = null;
		try {
			directory = (Directory) this.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		return directory;
	}
}
