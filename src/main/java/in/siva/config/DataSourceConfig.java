package in.siva.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import in.siva.util.ConnectionUtil;

@Configuration
public class DataSourceConfig {

	@Bean
	public JdbcTemplate jdbcTemplate() {
		// JdbcTemplate jdbcTemplate =
		DataSource jdbcTemplate = ConnectionUtil.getConnection();
		return new JdbcTemplate(jdbcTemplate);

	}

}
