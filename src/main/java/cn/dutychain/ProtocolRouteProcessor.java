package cn.dutychain;

import cn.contract.Contract;
import cn.registry.DirectoryComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Package: cn.dutychain
 * @Description: Protocol Route Processor
 * @Author: Sammy
 * @Date: 2022/8/23 10:53
 */

public class ProtocolRouteProcessor implements Processor{

	@Override
	public List<DirectoryComponent> process(String routeRule, Contract contract, ProcessorChain processorChain) {
		String[] routeRuleArrays = routeRule.split("\\.");
		String protocolName = routeRuleArrays[1];
		List<DirectoryComponent> patternResult = processorChain.getPatternResult();
		List<DirectoryComponent> newPatternResult = new ArrayList<>();
		if (patternResult.size() == 0) {
			System.out.println("上一层级没有匹配到,protocol结束责任链传递");
		} else {
			Iterator<DirectoryComponent> iterator = patternResult.iterator();
			while (iterator.hasNext()) {
				DirectoryComponent directoryComponent = iterator.next();
				Map<String, DirectoryComponent> childrenMap = directoryComponent.getChildren();
				if (childrenMap.containsKey(protocolName)) {
					DirectoryComponent patternServiceDir = childrenMap.get(protocolName);
					iterator.remove();
					newPatternResult.add(patternServiceDir);
				} else {
					iterator.remove();
				}
			}
			processorChain.setPatternResult(newPatternResult);
		}
		return processorChain.process(routeRule,contract,processorChain);
	}
}
