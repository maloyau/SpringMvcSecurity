package com.serhii.springmvc.configs;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.serhii.springmvc.repositories")
@EnableTransactionManagement
public class DataConfig {
    @Bean
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
    }
}
