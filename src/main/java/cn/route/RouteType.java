package cn.route;

/**
 * @Package: cn.route
 * @Description: Route Type 路由方式
 * @Author: Sammy
 * @Date: 2022/8/22 14:59
 */
public enum RouteType {
	ability_address("ability_address",1),node_address("node_address",2);
	private String name;
	private int index;

	private RouteType(String name,int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static RouteType findEnumByCode(int index) {

		for (RouteType statusEnum : RouteType.values()) {
			if (statusEnum.getIndex() == index) {
				return statusEnum;
			}
		}
		throw new IllegalArgumentException("code is not support");

	}
}
