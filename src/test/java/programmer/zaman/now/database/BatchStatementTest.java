package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchStatementTest {
    @Test
    void testBatchStatement() throws SQLException {

        Connection con = ConnectionUtil.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sql = """
                INSERT INTO comments(email, comment) VALUES
                ('adn@test.com', 'hi');
                """;

        for (int i = 0; i < 100; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();

        statement.close();
        con.close();
    }

    @Test
    void testBatchPreparedStatement() throws SQLException {

        Connection con = ConnectionUtil.getDataSource().getConnection();

        String sql = """
                INSERT INTO comments(email, comment) VALUES
                (?, ?);
                """;

        PreparedStatement statement = con.prepareStatement(sql);

        for (int i = 0; i < 100; i++) {
            statement.clearParameters();
            statement.setString(1, "adn@gmail.com");
            statement.setString(2, "hi");
            statement.addBatch();
        }
        statement.executeBatch();


        statement.close();
        con.close();
    }
}
