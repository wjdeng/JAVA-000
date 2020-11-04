package io.github.kimmking.gateway.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author Wayne.Deng
 * @Date 2020/11/4 下午3:18
 * @Version 1.0
 **/
public class HttpEndpointRouterImpl implements HttpEndpointRouter {


	private static List<String> serverList;

	static {
		serverList = new ArrayList<>();
		serverList.add("http://localhost:8081");
		serverList.add("http://localhost:8082");
		serverList.add("http://localhost:8083");
	}

	@Override
	public String route() {
		return serverList.get(new Random().nextInt(3));
	}
}
