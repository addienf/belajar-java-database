package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class TimeStampTest {
    @Test
    void testTimeStamp() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();
        String sql = """
                INSERT INTO simple_time
                (simple_time, simple_date, simple_timestamp)
                VALUES (?, ?, ?);
                """;

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setTime(1, new Time(System.currentTimeMillis()));
        statement.setDate(2, new Date(System.currentTimeMillis()));
        statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        statement.executeUpdate();

        statement.close();
        con.close();
    }
}
