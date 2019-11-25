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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@Primary
public class MsSqlAbcDatasource {

    @Bean("msSqlDSabc")
    @Primary
    @ConfigurationProperties("spring.datasource.mssql-dsabc")
    public DataSource getMsSqlabcDatasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("msSqlabcSqlSessionFactory")
    @Primary
    public SqlSessionFactory msSqlabcSqlSessionFactory(
            @Qualifier("msSqlDSabc") DataSource datasource) throws Exception {
        var resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/mssqlabc/**/*.xml");

        var bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Bean("msSqlabcSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate msSqlabcSqlSessionTemplate(
            @Qualifier("msSqlabcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("mssqlabcTransactionManager")
    @Primary
    public DataSourceTransactionManager getMsSqlabcTransactionManager(
            @Qualifier("msSqlDSabc") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("msSqlabcMapperScan")
    @Primary
    public MapperScannerConfigurer getMsSqlabcMapperScan() {
        var mapper = new MapperScannerConfigurer();
        mapper.setBasePackage("com.og.ogsynccloud.dao.mssqlabc");
        mapper.setSqlSessionFactoryBeanName("msSqlabcSqlSessionFactory");
        return mapper;
    }
}
