package programmer.zaman.now.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

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

    String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
    String username = "root";
    String password = "";

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/belajar_java_database";
        String username = "root";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Sukses Membuat Koneksi !!");
        } catch (SQLException e) {
            Assertions.fail("Gagal Membuat Koneksi " + e.getMessage());
        }
    }

    @Test
    void testCloseConnection() {
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)){
//            Connection con = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Sukses Membuat Koneksi !!");
//            con.close();
//            System.out.println("Sukses Menutup Koneksi !!");
        } catch (SQLException e) {
            Assertions.fail("Gagal Membuat Koneksi " + e.getMessage());
        }
    }
}
