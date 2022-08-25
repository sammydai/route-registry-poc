package cn.dutychain;

import cn.contract.Contract;
import cn.registry.DirectoryComponent;
import cn.route.RouteObject;

import java.util.List;

/**
 * @Package: cn.route
 * @Description: Route Processor
 * @Author: Sammy
 * @Date: 2022/8/25 15:42
 */

public class RouteProcessor {
	public static void route(Contract contract) {
		RouteObject routeObject = new RouteObject("route.properties");
		System.out.println(routeObject);
		for (String routeRule : routeObject.getRouteRules()) {
			ProcessorChain processorChain = new ProcessorChain();
			processorChain.addPatternResult(contract.getContractInfo().getRoot());
			processorChain.addProcessor(new ServiceRouteProcessor());
			processorChain.addProcessor(new ProtocolRouteProcessor());
			processorChain.addProcessor(new AppGroupRouteProcessor());
			System.out.println("路由规则: " + routeRule + " 进行路由处理……");
			List<DirectoryComponent> result = processorChain.process(routeRule, contract, processorChain);
		}
	}
}
