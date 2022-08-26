package cn.contract;

import cn.observer.Observer;
import cn.registry.Directory;
import cn.registry.DirectoryComponent;
import cn.registry.RegistryObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: cn.contract
 * @Description: 本地缓存的契约
 * #mode1能力级别寻址:/<service>/<protocol>/<app.group>
 * #mode2实例寻址: /<app>/<group>/<node>
 * @Author: Sammy
 * @Date: 2022/8/15 11:42
 */

public class Contract implements Observer {

	private Map<String, String> registryMap = new HashMap<>();

	private Map<String, RegistryObject> registryObjectsMap = new ConcurrentHashMap<>();

	private RegistryObject contractInfo;

	public void addRegistryObject(String name, RegistryObject registryObject) {
		registryObjectsMap.put(name, registryObject);
	}

	public Map<String, String> getRegistryMap() {
		return registryMap;
	}

	public void setRegistryMap(Map<String, String> registryMap) {
		this.registryMap = registryMap;
	}

	public RegistryObject getRegistryObject(String name) {
		return registryObjectsMap.get(name);
	}

	public Map<String, RegistryObject> getRegistryObjectsMap() {
		return registryObjectsMap;
	}

	public RegistryObject getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(RegistryObject contractInfo) {
		this.contractInfo = contractInfo;
	}

	public Contract() {
	}

	/**
	 * 初始化注册表和契约对象
	 * 1、解析配置文件成为RegistryObject对象
	 * 2、初始化Contract契约对象
	 *
	 * @return
	 * @throws IOException
	 */
	public Contract initContract() throws IOException {
		RegistryObject visibleRegistryObject = new RegistryObject("registry.properties");
		RegistryObject usableRegistryObject = new RegistryObject("registry2.properties");
		System.out.println("初始化可见性注册对象……");
		System.out.println("初始化可用性注册对象……");
		Contract contract = new Contract();
		contract.addRegistryObject("visibleRegistryObject", visibleRegistryObject);
		contract.addRegistryObject("usableRegistryObject", usableRegistryObject);
		contract.handleRegistryObjectsMap(contract.getRegistryObjectsMap());
		System.out.println("编排注册对象,生成本地契约文件");
		//如果注册表信息有更新，更新契约文件
		Observer contractObservable = contract;
		contract.getRegistryObjectsMap().forEach((k, v) -> {
			v.addObserver(contractObservable);
		});
		return contract;
	}

	/**
	 * 处理注册表信息，生成本地缓存注册表（本地契约）
	 * 1、编排registryObjectsMap
	 * 2、生成本地契约文件
	 * @param registryObjectsMap
	 * @return
	 */
	public void handleRegistryObjectsMap(Map<String, RegistryObject> registryObjectsMap) {
		RegistryObject visibleRegistryObject = registryObjectsMap.get("visibleRegistryObject");
		RegistryObject usableRegistryObject = registryObjectsMap.get("usableRegistryObject");
		Directory visibleRoot = visibleRegistryObject.getRoot();
		Directory usableRoot = usableRegistryObject.getRoot();
		Directory result = new Directory("root", "root");
		try {
			mergeDirectory(visibleRoot, usableRoot, result);
			RegistryObject mergedRegistryObject = new RegistryObject((Directory) result.getChildren().get("root"));
			contractInfo = mergedRegistryObject;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 编排注册对象-可见性表、注册对象-可用性表
	 * merge/select registry object
	 * @param visibleRoot 可见性表
	 * @param usableRoot  可用性表
	 * @param result
	 * @return
	 */
	private DirectoryComponent mergeDirectory(DirectoryComponent visibleRoot, DirectoryComponent usableRoot, DirectoryComponent result) {
		if (visibleRoot.getType().equals("ip") || usableRoot.getType().equals("ip")) {
			result.addComponent(visibleRoot);
			result.addComponent(usableRoot);
			return result;
		}
		if (visibleRoot.getName().equals(usableRoot.getName())) {
			Map<String, DirectoryComponent> visibleRootChildren = visibleRoot.getChildren();
			Map<String, DirectoryComponent> usableRootChildren = usableRoot.getChildren();
			result.addComponent(visibleRoot);
			visibleRootChildren.forEach((k, v) -> {
				usableRootChildren.forEach((k1, v1) -> {
					mergeDirectory(v, v1, visibleRoot);
				});
			});
		} else {
			result.addComponent(visibleRoot);
			result.addComponent(usableRoot);
		}
		return result;
	}

	@Override
	public void updateContract(RegistryObject refreshRegistryObject) {
		System.out.println("发现注册表信息有更新……");
		System.out.println("更新本地契约文件");
		Directory result = new Directory("root", "root");
		DirectoryComponent directoryComponent = this.mergeDirectory(refreshRegistryObject.getRoot(), this.contractInfo.getRoot(), result);
		RegistryObject refreshResult = new RegistryObject((Directory) directoryComponent.getChildren().get("root"));
		contractInfo = refreshResult;
		System.out.println("更新后的契约文件");
		this.getContractInfo().getRoot().printList();
	}
}
