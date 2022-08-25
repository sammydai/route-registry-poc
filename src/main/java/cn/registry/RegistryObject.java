package cn.registry;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @Package: cn.registry
 * @Description: RegistryObject
 * @Author: Sammy
 * @Date: 2022/8/12 11:04
 */

public class RegistryObject {

	private Directory root;

	public Directory getRoot() {
		return root;
	}

	public RegistryObject() {
	}

	public RegistryObject(Directory root) {
		this.root = root;
	}

	public RegistryObject(String configFileName) {
		root = new Directory("root", "root");
		InputStream inputStream = RegistryObject.class.getClassLoader().getResourceAsStream(configFileName);
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		generateRegistryObject(properties);
	}

	/**
	 * 构建RegistryObject对象
	 * Directory：/root/service/protocol/app.group/
	 * File：ip
	 * @param properties
	 */
	public void generateRegistryObject(Properties properties){
		Set<Object> keys = properties.keySet();
		for (Object key : keys) {
			String[] keysArray = key.toString().split("\\.");
			String serviceName = keysArray[0];
			String protocolName = keysArray[1];
			String appgroupName = keysArray[2] + "." + keysArray[3];
			String ipName = (String)properties.get(key);

			String[] ipsArray = ipName.split(",");
			//初始化注册表信息
			Directory serviceDir = new Directory(serviceName, "service");
			Directory protocolDir = new Directory(protocolName, "protocol");
			Directory appgroupDir = new Directory(appgroupName, "appgroup");
			List<File> ipFiles = new ArrayList<>();
			for (String ipStr : ipsArray) {
				String[] ipAttr = ipStr.split("#");
				File ipFile = new File(ipAttr[0], "ip");
				if (ipAttr.length >=2) {
					ipFile.setMetaData("score",ipAttr[1]);
				}
				ipFiles.add(ipFile);
			}
			//构造注册表结构
			DirectoryComponent tempComponent = root.addComponent(serviceDir);
			if (tempComponent!=null) {
				serviceDir = (Directory) tempComponent;
			}
			tempComponent = serviceDir.addComponent(protocolDir);
			if (tempComponent!=null) {
				protocolDir = (Directory) tempComponent;
			}
			tempComponent = protocolDir.addComponent(appgroupDir);
			if (tempComponent!=null) {
				appgroupDir = (Directory) tempComponent;
			}
			for (File ipFile : ipFiles) {
				appgroupDir.addComponent(ipFile);
			}
		}
	}
}
