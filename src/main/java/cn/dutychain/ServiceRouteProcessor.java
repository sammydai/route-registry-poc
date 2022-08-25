package cn.dutychain;

import cn.contract.Contract;
import cn.pattern.PatternContext;
import cn.registry.DirectoryComponent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Package: cn.dutychain
 * @Description: Service Route Processor 路由处理器
 * @Author: Sammy
 * @Date: 2022/8/22 11:57
 */

public class ServiceRouteProcessor implements Processor{

	/**
	 * @param routeRule      路由对象：路由类型、路由规则
	 * @param contract       本地缓存的注册表（契约）
	 * @param processorChain 责任链
	 * @return
	 */
	@Override
	public List<DirectoryComponent> process(String routeRule, Contract contract, ProcessorChain processorChain) {
		String[] routeRuleArrays = routeRule.split("\\.");
		String serviceName = routeRuleArrays[0];
		List<DirectoryComponent> patternResult = processorChain.getPatternResult();
		List<DirectoryComponent> newPatternResult = new ArrayList<>();
		PatternContext fullMatchStrategy = new PatternContext(processorChain.getPatternStrategy("fullMatchStrategy"));
		if (patternResult.size() == 0) {
			System.out.println("上一层级没有匹配到,service结束责任链传递");
		} else {
			Iterator<DirectoryComponent> iterator = patternResult.iterator();
			while (iterator.hasNext()) {
				DirectoryComponent directoryComponent = iterator.next();
				Map<String, DirectoryComponent> childrenMap = directoryComponent.getChildren();
				if (fullMatchStrategy.matches(serviceName, routeRule, childrenMap)) {
					newPatternResult = fullMatchStrategy.matcher(serviceName, routeRule, childrenMap);
					iterator.remove();
				} else {
					iterator.remove();
				}
			}
			processorChain.setPatternResult(newPatternResult);
		}
		return processorChain.process(routeRule,contract,processorChain);
	}
}
