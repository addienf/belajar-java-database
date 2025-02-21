package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseTransactionTest {
    @Test
    void testDatabaseTransaction() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = """
                INSERT INTO comments(email, comment) VALUES
                (?, ?);
                """;


        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < 100; i++) {
            statement.setString(1, "adn@gmail.com");
            statement.setString(2, "hi");
            statement.executeUpdate();
        }

        statement.executeBatch();

        statement.close();

        connection.commit();
        connection.close();
    }

    @Test
    void testDatabaseTransactionRollBack() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = """
                INSERT INTO comments(email, comment) VALUES
                (?, ?);
                """;


        PreparedStatement statement = connection.prepareStatement(sql);
        for (int i = 0; i < 100; i++) {
            statement.setString(1, "adn@gmail.com");
            statement.setString(2, "hi");
            statement.executeUpdate();
        }

        statement.executeBatch();

        statement.close();

        connection.rollback();
        connection.close();
    }
}
