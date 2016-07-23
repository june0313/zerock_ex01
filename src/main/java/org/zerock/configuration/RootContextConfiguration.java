package org.zerock.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by wayne on 2016. 6. 30..
 *
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy // <aop:aspectj-autoproxy> 에 대응되는 annotation
@EnableTransactionManagement // <tx:annotation-driven>
@ComponentScan(basePackages = {"org.zerock.persistence", "org.zerock.service", "org.zerock.aop"})
public class RootContextConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
		dataSource.setUrl("jdbc:mysql://127.0.0.1/book_ex");
		dataSource.setUsername("zerock");
		dataSource.setPassword("zerock");
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		// 하나의 datasource를 사용하는 경우 스프링에서 제공하는 DataSourceTransactionManager를 사용한다.
		return new DataSourceTransactionManager(dataSource());
	}
}
