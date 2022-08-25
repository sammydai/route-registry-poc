package cn.pattern;

import cn.registry.DirectoryComponent;

import java.util.List;

/**
 * @Package: cn.dutychain
 * @Description: Pattern Route Rule
 * @Author: Sammy
 * @Date: 2022/8/24 09:03
 */

public interface PatternStrategy {
	public boolean pattern(String patternName, String routeRule , List<DirectoryComponent> patternObjects);

	public List<DirectoryComponent> matcher(String patternName, String routeRule, List<DirectoryComponent> patternObjects);
}
