package com.zhan.mail.config.database;

public class DatabaseContextHolder {
	
	public enum DatabaseType {
		MASTER,SLAVE
	}
	
	
	private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();
	
	public static void setDatabaseType(DatabaseType databaseType) {
		if(databaseType == null) throw new NullPointerException();
		contextHolder.set(databaseType);
	}
	
	public static DatabaseType getDatabaseType() {
		return contextHolder.get() == null ? DatabaseType.MASTER : contextHolder.get();
	}
	
	public static void removeDatabaseType() {
		contextHolder.remove();
	}

}
