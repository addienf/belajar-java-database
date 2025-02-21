package programmer.zaman.now.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetaDataTest {
    @Test
    void testMetaData() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();

        DatabaseMetaData databaseMetaData = con.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());

        ResultSet tables = databaseMetaData.getTables("belajar_java_database", null, null, null);
        while (tables.next()){
            System.out.println(tables.getString("TABLE_NAME"));
        }
    }

    @Test
    void testParameterMetaData() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();

        String sql = """
                INSERT INTO comments
                (email, comment) VALUES (?, ?);
                """;

        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();

        System.out.println(parameterMetaData.getParameterCount());
//        System.out.println(parameterMetaData.getParameterType(1));
//        System.out.println(parameterMetaData.getParameterType(2));
    }

    @Test
    void testResultSetMetaData() throws SQLException {
        Connection con = ConnectionUtil.getDataSource().getConnection();
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM customers";
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("Name : " + resultSetMetaData.getColumnName(i));
            System.out.println("Type : " + resultSetMetaData.getColumnType(i));
            System.out.println("Type Name : " +resultSetMetaData.getColumnTypeName(i));
        }
    }
}
