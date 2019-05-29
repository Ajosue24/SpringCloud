package com.vytra.services.config;


import com.impossibl.postgres.jdbc.PGDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PgEnvironment {

    @Value("${pg.db.host}")
    private String DBHost;
    @Value("${pg.db.dbname}")
    private String DBName;
    @Value("${pg.db.username}")
    private String DBUserName;
    @Value("${pg.db.password}")
    private String DBPassword;
    @Value("${pg.db.port}")
    private String DBPort;

    public PGDataSource getPgDataSource(){
        PGDataSource dataSourcepg = new PGDataSource();
        dataSourcepg.setHost(DBHost);
        dataSourcepg.setPort(5432);
        dataSourcepg.setDatabaseName(DBName);
        dataSourcepg.setUser(DBUserName );
        dataSourcepg.setPassword(DBPassword);
        return dataSourcepg;
    }



}
