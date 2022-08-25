package cn.dutychain;

import cn.contract.Contract;
import cn.pattern.FullMatchStrategy;
import cn.pattern.PatternStrategy;
import cn.pattern.WildCardMatchStrategy;
import cn.registry.DirectoryComponent;
import cn.registry.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Package: cn.dutychain
 * @Description: Processor Chain
 * @Author: Sammy
 * @Date: 2022/8/22 11:20
 */

public class ProcessorChain {

	private List<Processor> processorList = new ArrayList<>();

	private List<DirectoryComponent> patternResult = new ArrayList<>();

	private Map<String, PatternStrategy> patternContext = new ConcurrentHashMap<>();

	private int index = 0;

	private PatternStrategy fullMatchStrategy = new FullMatchStrategy();

	private PatternStrategy wildCardMatchStrategy = new WildCardMatchStrategy();

	public ProcessorChain addProcessor(Processor processor) {
		processorList.add(processor);
		return this;
	}

	public ProcessorChain() {
		patternContext.put("fullMatchStrategy", fullMatchStrategy);
		patternContext.put("wildCardMatchStrategy", wildCardMatchStrategy);
	}

	public List<DirectoryComponent> getPatternResult() {
		return patternResult;
	}

	public PatternStrategy getPatternStrategy(String name) {
		return patternContext.get(name);
	}

	public void addPatternResult(DirectoryComponent patternDir) {
		patternResult.add(patternDir);
	}

	public void setPatternResult(List<DirectoryComponent> patternResult) {
		this.patternResult = patternResult;
	}

	public List<DirectoryComponent> process(String routeRule, Contract contract, ProcessorChain chain) {
		if (patternResult.size() == 0) {
			System.out.println("未匹配到符合规则的节点");
			return patternResult;
		}
		if (index == processorList.size()) {
			System.out.println("路由匹配到的节点:");
			if (patternResult != null) {
				for (DirectoryComponent directoryComponent : patternResult) {
					directoryComponent.getChildren().forEach((k, v) -> {
						if (v instanceof File) {
							File newFile = (File) v;
							String score = newFile.getMetaDataValue("score");
							if (score!= null) {
								System.out.println(newFile +" score:"+ score);
							} else {
								System.out.println(newFile);
							}
						}
					});
				}
			} else {
				System.out.println("未匹配到符合规则的节点");
			}
			return patternResult;
		}
		Processor processor = processorList.get(index);
		index++;
		return processor.process(routeRule, contract, chain);
	}

}
