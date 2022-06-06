package com.vvukovic9420rn_projekat.repositories.comment;

import com.vvukovic9420rn_projekat.entities.Comment;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PgCommentRepository extends Postgres implements CommentRepository {

    @Override
    public void deleteCommentsFromArticle(Integer articleId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE article_id = ?");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (author, content, article_id, date) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, comment.getAuthor());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setInt(3, comment.getArticleId());

            Date date = new Date();
            preparedStatement.setDate(4, new java.sql.Date(date.getTime()));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> getCommentsFromArticle(Integer articleId) {
        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE article_id = ?");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                comments.add(new Comment(resultSet.getInt("id"), resultSet.getInt("article_id"),
                        resultSet.getString("author"), resultSet.getString("content"),
                        new Date(resultSet.getDate("date").getTime()))
                );
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }
}
