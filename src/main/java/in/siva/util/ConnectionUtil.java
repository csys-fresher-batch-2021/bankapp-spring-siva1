package in.siva.util;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionUtil {

	private ConnectionUtil() {
		// Default Constructor
	}

	private static final String DRIVERCLASS = System.getenv("spring.datasource.driver-class-name");
	private static final String URL = System.getenv("spring.datasource.url");
	private static final String USERNAME = System.getenv("spring.datasource.username");
	private static final String PASSWORD = System.getenv("spring.datasource.password");

	public static BasicDataSource getConnection() {

		BasicDataSource db = new BasicDataSource();
		db.setDriverClassName(DRIVERCLASS);
		db.setUrl(URL);
		db.setUsername(USERNAME);
		db.setPassword(PASSWORD);
		return db;

	}

}
