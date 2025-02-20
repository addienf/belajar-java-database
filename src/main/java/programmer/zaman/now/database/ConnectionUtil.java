package programmer.zaman.now.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionUtil {

    private final static HikariDataSource dataSource;
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    private final static String username = "root";
    private final static String password = "";
    static HikariConfig config = new HikariConfig();

    static {
        config.setDriverClassName(driver);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        // Konfigurasi Pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
