package com.lintao.epidemic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring相关配置(替换掉了spring-bean.xml和spring-tx.xml)
 */
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Controller.class))
@EnableTransactionManagement //启用事务管理器
public class SpringConfig {
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        //创建数据源事务管理器
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

}
