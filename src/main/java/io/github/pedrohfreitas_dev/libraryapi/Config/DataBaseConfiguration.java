package io.github.pedrohfreitas_dev.libraryapi.Config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);
        ds.setDriverClassName(this.driver);
        return ds;
    }

    @Bean
    public DataSource hikariDataSource(){

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(this.url);
        config.setUsername(this.username);
        config.setPassword(this.password);
        config.setDriverClassName(this.driver);

        config.setMaximumPoolSize(10);
        config.setMaximumPoolSize(1);
        config.setMaxLifetime(600000);
        config.setPoolName("library-db-pool");
        config.setConnectionTimeout(100000);
        config.setConnectionTestQuery("select 1");

        return new HikariDataSource(config);
    }
}
