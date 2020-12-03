package com.task.java.week07.sqltraining.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:22
 * @Version 1.0
 **/
@Configuration
@EnableConfigurationProperties
@MapperScan(basePackages = {"com.task.java.week07.sqltraining.dao"}, sqlSessionTemplateRef = "sqlTemplate")
public class DataSourceConfig {

	//主库
	@Bean(name = "masterDb")
	@Qualifier("masterDb")
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource masterDb() {
		return DruidDataSourceBuilder.create().build();
	}

	//从库
	@Bean(name = "slaveDb")
	@Qualifier("slaveDb")
	@ConditionalOnProperty(prefix = "spring.datasource", name = "slave", matchIfMissing = true)
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public DataSource slaveDb() {
		return DruidDataSourceBuilder.create().build();
	}

	// 主从动态配置
	@Bean(name = "dynamicDb")
	@Qualifier("dynamicDb")
	public DynamicDataSource dynamicDb(@Qualifier("masterDb") DataSource masterDataSource,
	                                   @Autowired(required = false) @Qualifier("slaveDb") DataSource slaveDataSource) {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DynamicDataSourceEnum.MASTER.getDataSourceName(), masterDataSource);
		// 默认连接为主库，从库的DataSource 不是比传参数，如果slaveDataSource不等于null时则切换到从库
		if (slaveDataSource != null) {
			targetDataSources.put(DynamicDataSourceEnum.SLAVE.getDataSourceName(), slaveDataSource);
		}
		dynamicDataSource.setTargetDataSources(targetDataSources);
		dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
		return dynamicDataSource;
	}
	@Bean
	public SqlSessionFactory sessionFactory(@Qualifier("dynamicDb") DataSource dynamicDataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*.xml"));
		bean.setDataSource(dynamicDataSource);
		return bean.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	@Bean(name = "dataSourceTx")
	public DataSourceTransactionManager dataSourceTx(@Qualifier("dynamicDb") DataSource dynamicDataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dynamicDataSource);
		return dataSourceTransactionManager;
	}
}
