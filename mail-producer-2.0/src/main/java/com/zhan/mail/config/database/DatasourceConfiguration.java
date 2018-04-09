package com.zhan.mail.config.database;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

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
		LOGGER.info("========MASTER: {}=========",masterDataSource);
		return masterDataSource;
	}
	
	
	@Bean(name="slaveDataSource")
	public DataSource slaveDataSource() {
		DataSource slaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
		LOGGER.info("========SLAVE: {}=========",slaveDataSource);
		return slaveDataSource;
	}


	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addInitParameter("allow", "127.0.0.1");
		reg.addInitParameter("deny", "192.168.16.72");
		reg.addUrlMappings("/druid/*");
		LOGGER.info("========druidServlet: {}========",reg);
		return reg;
	}
	
	@Bean
	public FilterRegistrationBean druidFilter() {
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setFilter(new WebStatFilter());
		filter.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico");
		filter.addUrlPatterns("/*");
		LOGGER.info("========druidFilter: {}========",filter);
		return filter;
	}
}
