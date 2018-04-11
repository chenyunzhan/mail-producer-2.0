package com.zhan.mail.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zhan.mail.config.database.DatabaseContextHolder.DatabaseType;

public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource{

	@Override
	protected DatabaseType determineCurrentLookupKey() {
		return DatabaseContextHolder.getDatabaseType();
	}
	
}
