package com.task.java.week07.sqltraining.config;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:24
 * @Version 1.0
 **/
public class DataSourceContextHolder {
	private static final ThreadLocal<String> DYNAMIC_DATASOURCE_CONTEXT = new ThreadLocal<>();

	public static void set(String datasourceType) {
		DYNAMIC_DATASOURCE_CONTEXT.set(datasourceType);
	}

	public static String get() {
		return DYNAMIC_DATASOURCE_CONTEXT.get();
	}

	public static void clear() {
		DYNAMIC_DATASOURCE_CONTEXT.remove();
	}
}
