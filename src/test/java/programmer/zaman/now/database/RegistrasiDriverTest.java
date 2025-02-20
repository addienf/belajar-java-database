package programmer.zaman.now.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegistrasiDriverTest {
    @Test
    void testDriver() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            System.out.println("Koneksi Berhasil !");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
