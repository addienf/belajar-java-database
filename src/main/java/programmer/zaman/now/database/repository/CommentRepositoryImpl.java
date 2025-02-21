package programmer.zaman.now.database.repository;

import programmer.zaman.now.database.ConnectionUtil;
import programmer.zaman.now.database.entity.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository{
    @Override
    public void insert(Comment comment) {
        try (Connection con = ConnectionUtil.getDataSource().getConnection()){
            String sql = """
                    INSERT INTO comments (email, comment)
                    VALUES (?, ?);
                    """;
            try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1, comment.getEmail());
                statement.setString(2,comment.getComment());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()){
                    if (resultSet.next()){
                        comment.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment findById(Integer id) {
        try (Connection con = ConnectionUtil.getDataSource().getConnection()){
            String sql = """
                    SELECT * FROM comments WHERE id = ?
                    """;
            try (PreparedStatement statement = con.prepareStatement(sql)){
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()){
                        return new Comment(
                          resultSet.getInt("id"),
                          resultSet.getString("email"),
                          resultSet.getString("comment")
                        );
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAll() {
        try (Connection con = ConnectionUtil.getDataSource().getConnection()){
            String sql = """
                    SELECT * FROM comments
                    """;
            try (Statement statement = con.createStatement()){
                try (ResultSet resultSet = statement.executeQuery(sql)){
                    List<Comment> comments = new ArrayList<>();
                    while (resultSet.next()){
                        comments.add(new Comment(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }
                    return comments;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Comment> findAllByEmail(String email) {
        try (Connection con = ConnectionUtil.getDataSource().getConnection()){
            String sql = """
                    SELECT * FROM comments WHERE email = ?;
                    """;
            try (PreparedStatement statement = con.prepareStatement(sql)){
                statement.setString(1, email);
                try(ResultSet resultSet = statement.executeQuery()){
                    List<Comment> comments = new ArrayList<>();
                    while (resultSet.next()){
                        comments.add(new Comment(
                                resultSet.getInt("id"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }
                    return comments;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
