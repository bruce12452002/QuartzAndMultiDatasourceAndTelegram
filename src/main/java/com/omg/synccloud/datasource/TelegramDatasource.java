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
public class TelegramDatasource {

    @Bean("telegramDS")
    @ConfigurationProperties("spring.datasource.telegram-datasource")
    public DataSource getTelegramDatasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("telegramSqlSessionFactory")
    public SqlSessionFactory telegramSqlSessionFactory(
            @Qualifier("telegramDS") DataSource datasource) throws Exception {
        var resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/telegram/**/*.xml");

        var bean = new MybatisSqlSessionFactoryBean(); // myBatis-plus 的 factoryBean
        bean.setDataSource(datasource);
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Bean("telegramSqlSessionTemplate")
    public SqlSessionTemplate telegramSqlSessionTemplate(
            @Qualifier("telegramSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("telegramTransactionManager")
    public DataSourceTransactionManager getTelegramTransactionManager(@Qualifier("telegramDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("telegramMapperScan")
    public MapperScannerConfigurer getTelegramMapperScan() {
        var mapper = new MapperScannerConfigurer();
        mapper.setBasePackage("com.og.ogsynccloud.dao.telegram");
        mapper.setSqlSessionFactoryBeanName("telegramSqlSessionFactory");
        return mapper;
    }
}
