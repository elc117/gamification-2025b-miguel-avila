package com.mubification.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.net.URI;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {

    private static HikariDataSource dataSource;

    public static void connect(String databaseUrl) {
        try {

            String jdbcUrl;
            String username = null;
            String password = null;

            if (databaseUrl.startsWith("jdbc:")) {
                jdbcUrl = databaseUrl;

                String[] params = databaseUrl.split("\\?");
                if (params.length == 2) {
                    for (String param : params[1].split("&")) {
                        if (param.startsWith("user=")) {
                            username = param.substring(5);
                        } else if (param.startsWith("password=")) {
                            password = param.substring(9);
                        }
                    }
                }

                if (username == null || password == null) {
                    throw new RuntimeException("URL JDBC sem user/password!");
                }

            } else {
                // NÃO USAR new URI(databaseUrl) !!!
                String clean = databaseUrl
                        .replace("postgres://", "")
                        .replace("postgresql://", "");

                String[] parts = clean.split("@");
                String[] userPass = parts[0].split(":");
                String[] hostDb = parts[1].split("/");
                String[] hostPort = hostDb[0].split(":");

                username = userPass[0];
                password = userPass[1];

                String host = hostPort[0];
                String port = hostPort.length > 1 ? hostPort[1] : "5432";
                String dbName = hostDb[1];

                jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName + "?sslmode=require";
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
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
        if (dataSource == null) throw new IllegalStateException("Banco não conectado!");
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
