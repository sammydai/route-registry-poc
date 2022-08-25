package cn.pattern;

import cn.registry.DirectoryComponent;

import java.util.List;

/**
 * @Package: cn.pattern
 * @Description: Full Match Strategy 全匹配模式
 * @Author: Sammy
 * @Date: 2022/8/24 09:46
 */

public class FullMatchStrategy implements PatternStrategy {
	@Override
	public boolean pattern(String patternName, String routeRule, List<DirectoryComponent> patternObjects) {
		return patternObjects.contains(patternName);
	}

	@Override
	public List<DirectoryComponent> matcher(String patternName, String routeRule, List<DirectoryComponent> patternObjects) {
		return null;
	}
}
