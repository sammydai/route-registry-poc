package cn.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: cn.registry
 * @Description: File 叶子节点
 * @Author: Sammy
 * @Date: 2022/8/12 14:13
 */

public class File extends DirectoryComponent{

	private String name;

	private String type;

	/**
	 * 注册节点元数据信息
	 * 存放节点分数score、可用信任分级grade
	 */
	private Map<String, String> metaData = new ConcurrentHashMap<>();

	@Override
	public DirectoryComponent addComponent(DirectoryComponent c) {
		return c;
	}

	@Override
	public void remove(DirectoryComponent c) {
	}

	@Override
	public Map<String,DirectoryComponent> getChildren() {
		return null;
	}

	@Override
	public void operation() {
	}

	@Override
	public void printList() {
		super.printList();
	}

	@Override
	protected void printList(String prefix) {
		// System.out.println(prefix + "/" + this);
		if (getMetaDataValue("score") != null) {
			System.out.println(prefix + "/" + this + " score:" + getMetaDataValue("score"));
		} else {
			System.out.println(prefix + "/" + this);
		}
	}

	public File(String name, String type) {
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

	public Map<String, String> getMetaData() {
		return metaData;
	}

	public String getMetaDataValue(String metaDataKey) {
		return metaData.get(metaDataKey);
	}

	public void setMetaData(String metaDataKey,String metaDataValue) {
		metaData.put(metaDataKey, metaDataValue);
	}

	public static File mergeFileMetaData(File originalFile, File c) {
		File mergeResult = new File(originalFile.name, originalFile.type);
		originalFile.getMetaData().forEach((k,v)->{
			mergeResult.setMetaData(k,v);
		});
		c.getMetaData().forEach((k,v)->{
			mergeResult.setMetaData(k,v);
		});
		return mergeResult;
	}
}
