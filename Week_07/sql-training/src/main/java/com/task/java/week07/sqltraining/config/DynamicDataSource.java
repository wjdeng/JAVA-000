package com.task.java.week07.sqltraining.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:24
 * @Version 1.0
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.get();
	}
}
