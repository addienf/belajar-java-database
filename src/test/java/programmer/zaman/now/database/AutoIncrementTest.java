package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {
    @Test
    void testAutoIncrement() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();

        String email = "adn@test.com";
        String comment = "hi";
        String sql = """
                INSERT INTO comments(email, comment) VALUES (?, ?);
                """;

        PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, email);
        statement.setString(2, comment);
        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()){
            int id = resultSet.getInt(1);
            System.out.println("Id Comment = " + id);
        }

        statement.close();
        con.close();
    }
}
