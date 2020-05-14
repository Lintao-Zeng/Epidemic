package com.lintao.epidemic;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 对myBatis的配置，等同于mybatis.xml+spring-bean.xml
 */
@Configuration
@MapperScan(basePackages = "com.lintao.epidemic.mapper")
public class MyBatisConfig {
    /**
     * 配置数据源
     * @return
     */
    @Bean(name="dataSource",destroyMethod = "close")
    public BasicDataSource basicDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");//驱动
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/epidemic");//数据库地址
        dataSource.setUsername("root");//数据库用户名
        dataSource.setPassword("root");//数据库密码

        dataSource.setInitialSize(3);//连接池的大小
        dataSource.setMaxActive(50);//最大活跃数
        dataSource.setMaxIdle(1);//最大空闲数
        dataSource.setMaxWait(4000);//等待连接时间
        dataSource.setDefaultAutoCommit(false);//禁止自动提交事务
        return dataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);
        SqlSessionFactory factory = null;
        //设置xml文件中的类所在的包（不过本次项目，使用注解替换掉原本的xml配置）
        factoryBean.setTypeAliasesPackage("com.lintao.epidemic.bean");
        //为了让mybatis自动将下划线分割的列名转换为小驼峰表示的属性名
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//举例： user_id      userId
        factoryBean.setConfiguration(configuration);

        try {
            //设置映射xml文件的路径
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/lintao/epidemic/mapper/*Mapper.xml");
            factoryBean.setMapperLocations(resources);
            factory = factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }


}
