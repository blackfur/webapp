package com.fakeghost.bbs.conf;

import java.util.Properties;
 
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:hibernate.prop")
public class HibernateConf{
   /*
	@Value("${db.driver}")
	private String DRIVER;
 
	@Value("${db.password}")
	private String PASSWORD;
 
	@Value("${db.url}")
	private String URL;
 
	@Value("${db.username}")
	private String USERNAME;
 
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}
   */
	@Value("${PackagesToScan}")
	String PACKAGES_TO_SCAN;
	@Value("${hibernate.dialect}") String DIALECT;
 
	@Value("${hibernate.show_sql}")
	String SHOW_SQL;
 
	@Value("${hibernate.hbm2ddl.auto}")
	String HBM2DDL_AUTO;
 
 
   @Autowired
   DataSource dataSource;
 
	@Bean
	public LocalSessionFactoryBean sessionf() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", DIALECT);
		hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		sessionFactory.setHibernateProperties(hibernateProperties);
 
		return sessionFactory;
	}
 
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionf().getObject());
		return transactionManager;
	}
}
