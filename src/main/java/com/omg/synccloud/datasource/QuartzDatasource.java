package com.omg.synccloud.datasource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
public class QuartzDatasource {
    //    @Bean("ogQuartzDS")
//    @ConfigurationProperties("spring.quartz.properties.org.quartz.datasource.ogquartz-datasource")
    public DataSource getOGQuartzDatasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //    @Bean("ogQuartzSqlSessionFactory")
    public SqlSessionFactory ogQuartzSqlSessionFactory(
            @Qualifier("ogQuartzDS") DataSource datasource) throws Exception {
        var resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/ogquartz/**/*.xml");

        var bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    //    @Bean("ogQuartzSqlSessionTemplate")
    public SqlSessionTemplate ogQuartzSqlSessionTemplate(
            @Qualifier("ogQuartzSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //    @Bean("ogQuartzTransactionManager")
    public DataSourceTransactionManager getOGQuartzTransactionManager(@Qualifier("ogQuartzDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //    @Bean("ogQuartzMapperScan")
    public MapperScannerConfigurer getOGQuartzMapperScan() {
        var mapper = new MapperScannerConfigurer();
        mapper.setBasePackage("com.og.ogsynccloud.dao.ogquartz");
        mapper.setSqlSessionFactoryBeanName("ogQuartzSqlSessionFactory");
        return mapper;
    }
}
