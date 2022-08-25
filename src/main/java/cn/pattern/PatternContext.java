package cn.pattern;

import cn.registry.DirectoryComponent;

import java.util.List;
import java.util.Map;

/**
 * @Package: cn.matches
 * @Description: Pattern Context
 * @Author: Sammy
 * @Date: 2022/8/25 12:45
 */

public class PatternContext {

	private PatternStrategy patternStrategy;

	public PatternContext(PatternStrategy patternStrategy) {
		this.patternStrategy = patternStrategy;
	}

	public boolean matches(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects) {
		return patternStrategy.matches(patternName, routeRule, patternObjects);
	}

	public List<DirectoryComponent> matcher(String patternName, String routeRule, Map<String, DirectoryComponent> patternObjects) {
		return patternStrategy.matcher(patternName, routeRule, patternObjects);
	}


}
