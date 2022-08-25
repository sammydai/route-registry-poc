package cn.pattern;

import cn.registry.DirectoryComponent;

import java.util.List;
import java.util.Map;

/**
 * @Package: cn.matches
 * @Description: Wild Card Match Strategy 通配符匹配模式
 * @Author: Sammy
 * @Date: 2022/8/25 12:50
 */

public class WildCardMatchStrategy implements PatternStrategy {
	@Override
	public boolean matches(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects) {
		return false;
	}

	@Override
	public List<DirectoryComponent> matcher(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects) {
		return null;
	}
}
