package com.zhan.mail.config.database;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.apache.bcel.util.ClassLoaderRepository;
import org.aspectj.apache.bcel.util.ClassLoaderRepository.SoftHashMap;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureAfter(DatasourceConfiguration.class)
public class MybatisConfiguration extends MybatisAutoConfiguration {
	
	@Resource(name="masterDataSource")
	private DataSource masterDataSource;
	
	@Resource(name="slaveDataSource")
	private DataSource slaveDataSource;
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		return super.sqlSessionFactory(getReadWriteSplitRoutingDataSource());
	}
	
	
	public ReadWriteSplitRoutingDataSource getReadWriteSplitRoutingDataSource() {
		
		SoftHashMap dataSourceMap = new ClassLoaderRepository.SoftHashMap();
		dataSourceMap.put(DatabaseContextHolder.DatabaseType.MASTER, masterDataSource);
		dataSourceMap.put(DatabaseContextHolder.DatabaseType.SLAVE, slaveDataSource);
		
		ReadWriteSplitRoutingDataSource proxy = new ReadWriteSplitRoutingDataSource();
		proxy.setDefaultTargetDataSource(masterDataSource);
		proxy.setTargetDataSources(dataSourceMap);
		return proxy;
		
	}
	

}
