package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementTest {
    @Test
    void testPrepareStatement() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();
        String username = "admin";
        String password = "admin";

        String sql = """
                SELECT * FROM admin WHERE username = ? and password = ?
                """;
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            //Sukses Login
            System.out.println("Sukses Login : " + resultSet.getString("username"));
        } else {
            //Gagal Login
            System.out.println("Gagal Login !");
        }

        statement.close();
        con.close();

    }
}
