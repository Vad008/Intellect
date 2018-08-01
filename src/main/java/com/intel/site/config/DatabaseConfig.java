package com.intel.site.config;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource(Environment environment) {
        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl(environment.getProperty("database.urll"));
        dataSource.setUsername(environment.getProperty("database.username"));
        dataSource.setPassword(environment.getProperty("database.password"));
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate NamedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
//
//    @Autowired
//    public void flywayMigrate(DataSource dataSource){
//        Flyway flyway = new Flyway();
//        flyway.setIgnoreFutureMigrations(true);
//        flyway.setIgnoreMissingMigrations(true);
//        flyway.setOutOfOrder(true);
//        flyway.setLocations("classpath:db/migration");
//        flyway.setDataSource(dataSource);
//        flyway.migrate();
//    }

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
//        final JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//        db.setDataSource(dataSource);
//        db.setCreateTableOnStartup(true);
//        return db;
//    }




}