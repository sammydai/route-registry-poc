package cn.pattern;

import cn.registry.DirectoryComponent;

import java.util.List;
import java.util.Map;

/**
 * @Package: cn.dutychain
 * @Description: Pattern Strategy 匹配策略
 * @Author: Sammy
 * @Date: 2022/8/24 09:03
 */

public interface PatternStrategy {
	/**
	 * 判断能否路由到对应目录
	 *
	 * @param patternName
	 * @param routeRule
	 * @param patternObjects
	 * @return
	 */
	boolean matches(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects);

	/**
	 * 匹配目录
	 *
	 * @param patternName
	 * @param routeRule
	 * @param patternObjects
	 * @return
	 */
	List<DirectoryComponent> matcher(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects);
}
