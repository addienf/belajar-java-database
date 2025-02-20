package programmer.zaman.now.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HikariCPTest {
    String driver = "com.mysql.cj.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "";
    HikariConfig config = new HikariConfig();


    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            System.out.println("Driver Berhasil !");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testHikariConfig() {
        config.setDriverClassName(driver);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);

        // Konfigurasi Pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);
    }

    @Test
    void testMakingConPool() {
        try {
            HikariDataSource dataSource = new HikariDataSource(config);
            Connection con = dataSource.getConnection();
            System.out.println("Sukses Mengambil Koneksi!!");

            con.close();
            System.out.println("Sukses Menutup Koneksi!!");

            dataSource.close();
            System.out.println("Sukses Menutup Pool!!");
        }catch (SQLException e){
            Assertions.fail(e);
        }

    }
}
