package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjectionTest {
    @Test
    void testSQLInject() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();
        Statement statement = con.createStatement();

//        String username = "admin";
//        String password = "admin";

//        Bahaya !!!
        String username = "admin'; #";
        String password = "salah";

        String sql = "SELECT * FROM admin WHERE username = '" + username +
                "' AND password = '" + password + "'";

        ResultSet resultSet =statement.executeQuery(sql);

        if (resultSet.next()){
            //Sukses Login
            System.out.println("Sukses Login : " + resultSet.getString("username"));
        } else {
            //Gagal Login
            System.out.println("Gagal Login !");
        }

        resultSet.close();
        statement.close();
        con.close();
    }
}
