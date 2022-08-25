package cn;

import cn.contract.Contract;
import cn.dutychain.AppGroupRouteProcessor;
import cn.dutychain.ProcessorChain;
import cn.dutychain.ProtocolRouteProcessor;
import cn.dutychain.ServiceRouteProcessor;
import cn.registry.DirectoryComponent;
import cn.route.RouteObject;

import java.io.IOException;
import java.util.List;

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
		RouteObject routeObject = new RouteObject("route.properties");
		System.out.println(routeObject);
		for (String routeRule : routeObject.getRouteRules()) {
			ProcessorChain processorChain = new ProcessorChain();
			processorChain.addPatternResult(contract.getContractInfo().getRoot());
			processorChain.addProcessor(new ServiceRouteProcessor());
			processorChain.addProcessor(new ProtocolRouteProcessor());
			processorChain.addProcessor(new AppGroupRouteProcessor());
			System.out.println("路由规则: "+routeRule+" 进行路由处理……");
			List<DirectoryComponent> result = processorChain.process(routeRule, contract, processorChain);
		}
	}
}
