package com.zhan.mail.config.database;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class ReadOnlyConnectionInterceptor implements Ordered {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReadOnlyConnectionInterceptor.class);
	
	@Around("@annotation(readOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection) throws Throwable {
		try {
			
			LOGGER.info("======================SLAVE DATASOURCE==============================");
			DatabaseContextHolder.setDatabaseType(DatabaseContextHolder.DatabaseType.SLAVE);
			Object result = proceedingJoinPoint.proceed();
			return result;
		} finally {
			LOGGER.info("======================REMOVE DATASOURCE==============================");
			DatabaseContextHolder.removeDatabaseType();
		}
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
