package cn.pattern;

import cn.registry.DirectoryComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Package: cn.matches
 * @Description: Full Match Strategy 全匹配模式
 * @Author: Sammy
 * @Date: 2022/8/24 09:46
 */

public class FullMatchStrategy implements PatternStrategy {
	@Override
	public boolean matches(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects) {
		return patternObjects.containsKey(patternName);
	}

	@Override
	public List<DirectoryComponent> matcher(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects) {
		DirectoryComponent directoryComponent = patternObjects.get(patternName);
		List<DirectoryComponent> result = new ArrayList<>();
		result.add(directoryComponent);
		return result;
	}
}
