package com.github.study.transaction.jpa.config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class JPAConfiguration {

    /*
     *配置数据源
     */
    @Bean
    public DataSource createDataSource(){
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://127.0.0.1:3306/security");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    /*
     * 配置EntityManager
     */
    @Bean("entityManagerFactory")
    public EntityManagerFactory createEntityManager(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource); //配置数据源

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);

        em.setPackagesToScan("com.githup.**.domain");//设置实体类的扫描路径
        em.setJpaVendorAdapter(jpaVendorAdapter); //配置jpa的适配器
        em.afterPropertiesSet();
        return em.getObject();
    }

    /*
     * 定义一个事务
     */
    @Bean("transactionManager")
    public PlatformTransactionManager createPlatformTransactionManager(EntityManagerFactory emf){
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        return tm;
    }
}
