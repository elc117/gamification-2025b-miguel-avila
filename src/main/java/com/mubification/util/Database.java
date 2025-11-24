package com.mubification.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class Database {

    private static HikariDataSource dataSource;

    public static void connect(String databaseUrl) {
        try {
            // Render usa postgres://... então convertemos para jdbc:postgresql://...
            String jdbcUrl = databaseUrl.replace("postgres://", "jdbc:postgresql://");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            config.setDriverClassName("org.postgresql.Driver");
            
            dataSource = new HikariDataSource(config);

            System.out.println("Database connected!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to DB: " + e.getMessage());
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
