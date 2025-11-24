package com.mubification.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class Database {

    private static HikariDataSource dataSource;

    public static void connect(String databaseUrl) {
    try {

        // modificando a url do banco do render para o jdbc
        String cleanUrl = databaseUrl.replace("postgres://", "").replace("postgresql://", "");
        String[] parts = cleanUrl.split("@");
        String[] userPass = parts[0].split(":");
        String[] hostDb = parts[1].split("/");
        String[] hostPort = hostDb[0].split(":");

        String username = userPass[0];
        String password = userPass[1];
        String host     = hostPort[0];
        String port     = hostPort.length > 1 ? hostPort[1] : "5432";
        String dbName   = hostDb[1];

        String jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;


        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        // configurações necessárias para o bd no render
        config.addDataSourceProperty("ssl", "true");
        config.addDataSourceProperty("sslmode", "require");

        dataSource = new HikariDataSource(config);

        System.out.println("[✅] Conectado ao banco!");

    } catch (Exception e) {
        System.err.println("[❌] Falha ao conectar no database: " + e.getMessage());
        throw new RuntimeException(e);
    }
}

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("Banco não conectado!");
        }
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() { return dataSource; }
}
