package cn.dutychain;

import cn.contract.Contract;
import cn.registry.DirectoryComponent;

import java.util.List;

/**
 * @Package: cn.dutychain
 * @Description: Processor 路由处理
 * @Author: Sammy
 * @Date: 2022/8/22 11:28
 */

interface Processor {

	/**
	 * 根据路由规则，匹配每一层级Dir
	 * @param routeRule
	 * @param contract
	 * @param processorChain
	 * @return
	 */
	List<DirectoryComponent> process(String routeRule, Contract contract, ProcessorChain processorChain);
}
