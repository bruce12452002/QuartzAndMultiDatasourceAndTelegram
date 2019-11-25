package com.omg.synccloud.datasource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
//@MapperScan(basePackages = "com.og.ogsynccloud.dao.mysql", sqlSessionFactoryRef = "mySqlSqlSessionFactory")
public class MySqlDatasource {

    @Bean("mySqlDS")
    @ConfigurationProperties("spring.datasource.mysql-datasource")
    public DataSource getMysqlDatasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("mySqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mySqlDS") DataSource datasource) throws Exception {
        var resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/mysql/**/*.xml");

        var bean = new MybatisSqlSessionFactoryBean(); // myBatis-plus 的 factoryBean
        // var bean = new SqlSessionFactoryBean(); // myBatis 的 factoryBean
        bean.setDataSource(datasource);
        bean.setMapperLocations(resources);
        // bean.setTypeAliasesPackage("com.og.ogsynccloud.entity");
        return bean.getObject();
    }

    @Bean("mySqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate(
            @Qualifier("mySqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("mySqlTransactionManager")
    public DataSourceTransactionManager getMySqlTransactionManager(@Qualifier("mySqlDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("mySqlMapperScan")
    public MapperScannerConfigurer getMySqlMapperScan() {
        var mapper = new MapperScannerConfigurer();
        mapper.setBasePackage("com.og.ogsynccloud.dao.mysql");
        mapper.setSqlSessionFactoryBeanName("mySqlSqlSessionFactory");
        return mapper;
    }
}
