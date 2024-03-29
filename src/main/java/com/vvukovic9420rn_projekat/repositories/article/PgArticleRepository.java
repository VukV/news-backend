package com.vvukovic9420rn_projekat.repositories.article;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PgArticleRepository extends Postgres implements ArticleRepository {

    @Override
    public List<Article> getAllArticlesSortedByDate(Integer page) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            int offset = (page - 1) * 10;

            preparedStatement = connection.prepareStatement("SELECT * FROM articles ORDER BY date DESC OFFSET ? ROWS LIMIT 10");
            preparedStatement.setInt(1, offset);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("category_id"), resultSet.getString("title"),
                        resultSet.getString("content"), resultSet.getInt("visit_count"),
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

        return articles;
    }

    @Override
    public List<Article> getAllArticlesByCategorySortedByDate(Integer categoryId, Integer page) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            int offset = (page - 1) * 10;

            preparedStatement = connection.prepareStatement("SELECT * FROM articles WHERE category_id=? ORDER BY date DESC OFFSET ? ROWS LIMIT 10");
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("category_id"), resultSet.getString("title"),
                        resultSet.getString("content"), resultSet.getInt("visit_count"),
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

        return articles;
    }

    @Override
    public Article addArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO articles (user_id, category_id, title, content, date, visit_count) VALUES(?, ?, ?, ?, ?, 0)", generatedColumns);
            preparedStatement.setInt(1, article.getUserId());
            preparedStatement.setInt(2, article.getCategoryId());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setString(4, article.getContent());

            Date date = new Date();
            preparedStatement.setDate(5, new java.sql.Date(date.getTime()));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                article.setDate(date);
                article.setId(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public Article updateArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE articles SET category_id=?, title=?, content=? WHERE id=?");
            preparedStatement.setInt(1, article.getCategoryId());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setString(3, article.getContent());
            preparedStatement.setInt(4, article.getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                article.setDate(new Date(resultSet.getDate(6).getTime()));
                article.setId(resultSet.getInt(1));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public void deleteArticleById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM articles WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Article> getNewestTenArticles() {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles ORDER BY date DESC LIMIT 10");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("category_id"), resultSet.getString("title"),
                        resultSet.getString("content"), resultSet.getInt("visit_count"),
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

        return articles;
    }

    @Override
    public List<Article> getTopTenArticles() {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles ORDER BY visit_count DESC LIMIT 10");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                articles.add(new Article(resultSet.getInt("id"), resultSet.getInt("user_id"),
                        resultSet.getInt("category_id"), resultSet.getString("title"),
                        resultSet.getString("content"), resultSet.getInt("visit_count"),
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

        return articles;
    }

    @Override
    public Article getArticleById(Integer id) {
        Article articleToReturn = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            connection.setAutoCommit(false);

            int visitCount = 0;

            preparedStatement = connection.prepareStatement("SELECT * FROM articles WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                int postId = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int categoryId = resultSet.getInt("category_id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = new Date(resultSet.getDate("date").getTime());
                visitCount = resultSet.getInt("visit_count") + 1;

                articleToReturn = new Article(postId, userId, categoryId, title, content, visitCount, date);
            }

            preparedStatement = connection.prepareStatement("UPDATE articles SET visit_count=? WHERE id = ?");
            preparedStatement.setInt(1, visitCount);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            connection.commit();

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articleToReturn;
    }
}
