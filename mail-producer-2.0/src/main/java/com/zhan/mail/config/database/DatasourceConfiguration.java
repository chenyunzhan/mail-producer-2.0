package com.zhan.mail.config.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfiguration {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DatasourceConfiguration.class);
	
	@Value("${druid.type}")
	private Class<? extends DataSource> dataSourceType;
	
	@Bean(name="masterDataSource")
	@Primary
	@ConfigurationProperties(prefix="druid.master")
	public DataSource masterDataSource() {
		DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
		LOGGER.info("========MASTER: {}=========", masterDataSource);
		return masterDataSource;
	}

}
