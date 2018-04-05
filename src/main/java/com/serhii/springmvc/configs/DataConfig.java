package com.serhii.springmvc.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@PropertySource(value = "classpath:application.properties")
@Configuration
@EnableJpaRepositories(basePackages = "com.serhii.springmvc.repositories")
@EnableTransactionManagement
@EnableJpaAuditing
public class DataConfig {
    @Value("${spring.datasource.DriverClassName}")
    private String DATASOURCE_DRIVER_CLASS_NAME;
    @Value("${spring.datasource.Url}")
    private String DATASOURCE_URL;
    @Value("${spring.datasource.Username}")
    private String DATASOURCE_USERNAME;
    @Value("${spring.datasource.Password}")
    private String DATASOURCE_PASSWORD;
    @Value("${hibernate.DatabasePlatform}")
    private String HIBERNATE_DATABASE_PLATFORM;
    @Value("${hibernate.GenerateDdl}")
    private boolean HIBERNATE_GENERATE_DDL;
    @Value("${hibernate.ShowSql}")
    private boolean HIBERNATE_SHOW_SQL;
    @Value("${hibernate.PackagesToScan}")
    private String HIBERNATE_PACKAGES_TO_SCAN;
    @Value("${hikari.MaximumPoolSize}")
    private int HIKARI_MAX_POOL_SIZE;
    @Value("${hikari.CachePrepStmts}")
    private String HIKARI_CACHE_PREP_STMTS;
    @Value("${hikari.PrepStmtCacheSize}")
    private String HIKARI_PREP_STMTS_CACHE_SIZE;
    @Value("${hikari.PrepStmtCacheSqlLimit}")
    private String HIKARI_PREP_STMTS_CACHE_SQL_LIMIT;

    @Bean
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(DATASOURCE_DRIVER_CLASS_NAME);
        hikariConfig.setJdbcUrl(DATASOURCE_URL);
        hikariConfig.setUsername(DATASOURCE_USERNAME);
        hikariConfig.setPassword(DATASOURCE_PASSWORD);
        hikariConfig.setMaximumPoolSize(HIKARI_MAX_POOL_SIZE);
        hikariConfig.addDataSourceProperty("cachePrepStmts", HIKARI_CACHE_PREP_STMTS);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize" , HIKARI_PREP_STMTS_CACHE_SIZE);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", HIKARI_PREP_STMTS_CACHE_SQL_LIMIT);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public HibernateJpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(HIBERNATE_DATABASE_PLATFORM);
        vendorAdapter.setGenerateDdl(HIBERNATE_GENERATE_DDL);
        vendorAdapter.setShowSql(HIBERNATE_SHOW_SQL);
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter());
        factory.setDataSource(dataSource());
        factory.setPackagesToScan(HIBERNATE_PACKAGES_TO_SCAN);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    /*@Bean
    public DataSource dataSource() {
        EmbeddedDatabaseFactoryBean databaseFactoryBean = new EmbeddedDatabaseFactoryBean();
        databaseFactoryBean.setDatabaseType(EmbeddedDatabaseType.H2);
        databaseFactoryBean.afterPropertiesSet();
        return databaseFactoryBean.getObject();
    }

    @Bean
    public AbstractEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.serhii.springmvc.entities");
        entityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());
        entityManagerFactory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create-drop");
        entityManagerFactory.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        entityManagerFactory.getJpaPropertyMap().put("hibernate.show_sql", "true");
        entityManagerFactory.afterPropertiesSet();

        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }*/
}
