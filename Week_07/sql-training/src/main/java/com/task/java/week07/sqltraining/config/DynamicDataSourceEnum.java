package com.task.java.week07.sqltraining.config;

import lombok.Getter;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:22
 * @Version 1.0
 **/
@Getter
public enum DynamicDataSourceEnum {

	MASTER("master"),
	SLAVE("slave");

	private String dataSourceName;

	DynamicDataSourceEnum(String dataSourceName) {

		this.dataSourceName = dataSourceName;
	}
}
