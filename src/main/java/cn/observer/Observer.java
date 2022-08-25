package cn.observer;

import cn.registry.RegistryObject;

/**
 * @Package: cn.observer
 * @Description: Observer
 * @Author: Sammy
 * @Date: 2022/8/25 14:54
 */

public interface Observer {
	public void updateContract(RegistryObject refreshRegistryObject);
}
