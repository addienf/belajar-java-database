package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {
    @Test
    void testStatement() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();

        Statement statement = con.createStatement();

        statement.close();
        con.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sql = """
                INSERT INTO customers(id, name, email) VALUES
                ('fauzan', 'Fauzan 1', 'fzn@test.com');
                """;

        int update = statement.executeUpdate(sql);
        System.out.println(update);
        statement.close();
        con.close();
    }

    @Test
    void testExecuteDelete() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sql = """
                DELETE FROM customers;
                """;

        int update = statement.executeUpdate(sql);
        System.out.println(update);
        statement.close();
        con.close();
    }

    @Test
    void testExecuteSelect() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String sql = """
                SELECT * FROM customers;
                """;

        ResultSet resultSet =statement.executeQuery(sql);

        resultSet.close();
        statement.close();
        con.close();
    }
}
