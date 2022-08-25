package cn.route;


import cn.registry.RegistryObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @Package: cn.dutychain
 * @Description: PatternRule
 * @Author: Sammy
 * @Date: 2022/8/22 11:52
 */

public class RouteObject {

	private RouteType routeType;

	private List<String> routeRules;

	public RouteObject() {
	}

	public RouteObject(String configFileName) {
		InputStream inputStream = RegistryObject.class.getClassLoader().getResourceAsStream(configFileName);
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.routeType = RouteType.findEnumByCode(Integer.valueOf(properties.getProperty("route.type")));
		this.routeRules = Arrays.asList(properties.getProperty("route.rule").split(","));
	}

	public RouteType getRouteType() {
		return routeType;
	}

	public void setRouteType(RouteType routeType) {
		this.routeType = routeType;
	}

	public List<String> getRouteRules() {
		return routeRules;
	}

	public void setRouteRules(List<String> routeRules) {
		this.routeRules = routeRules;
	}

	@Override
	public String toString() {
		return "路由对象:{" +
				"路由类型=" + routeType +
				", 路由规则=" + routeRules +
				'}';
	}
}

