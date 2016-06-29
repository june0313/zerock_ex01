package org.zerock.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by wayne on 2016. 6. 30..
 *
 */
@Configuration
public class RootContextConfiguration {
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		dataSource.setUrl("jdbc:mysql://127.0.0.1/book_ex");
		dataSource.setUsername("zerock");
		dataSource.setPassword("zerock");
		return dataSource;
	}
}
