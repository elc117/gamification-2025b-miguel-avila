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
            String username;
            String password;

            // Se for postgres:// ou postgresql:// → parsear corretamente
            if (databaseUrl.startsWith("postgres://") || databaseUrl.startsWith("postgresql://")) {

                // Render e Heroku seguem este formato
                // postgres://user:pass@host:port/dbname
                URI uri = new URI(databaseUrl);

                username = uri.getUserInfo().split(":")[0];
                password = uri.getUserInfo().split(":")[1];

                String host = uri.getHost();
                int port = uri.getPort();
                String dbName = uri.getPath().substring(1); // remove barra inicial

                jdbcUrl = "jdbc:postgresql://" + host + ":" + port + "/" + dbName + "?sslmode=require";
            }
            else if (databaseUrl.startsWith("jdbc:postgresql://")) {
                // Já está pronto para uso
                jdbcUrl = databaseUrl;

                // extrair user/password não é necessário,
                // o Render geralmente coloca tudo dentro da URL JDBC
                username = null;
                password = null;
            }
            else {
                throw new IllegalArgumentException("Formato inesperado de DATABASE_URL: " + databaseUrl);
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);

            if (username != null) config.setUsername(username);
            if (password != null) config.setPassword(password);

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
