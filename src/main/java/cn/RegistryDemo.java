package cn;

import cn.contract.Contract;
import cn.dutychain.RouteProcessor;

import java.io.IOException;

/**
 * @Package: cn
 * @Description: Registry Demo
 * @Author: Sammy
 * @Date: 2022/8/22 10:22
 */

public class RegistryDemo {
	public static void main(String[] args) throws IOException {
		System.out.println("初始化本地契约");
		Contract contract = new Contract().initContract();
		contract.getContractInfo().getRoot().printList();
		RouteProcessor.route(contract);

		//更新注册信息场景
		System.out.println();
		System.out.println("==========更新注册信息场景==========");
		contract.getRegistryObjectsMap().get("visibleRegistryObject").updateRegistryObject();
		RouteProcessor.route(contract);
	}


}
