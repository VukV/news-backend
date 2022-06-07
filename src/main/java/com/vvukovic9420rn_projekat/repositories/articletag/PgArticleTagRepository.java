package com.vvukovic9420rn_projekat.repositories.articletag;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.entities.ArticleTag;
import com.vvukovic9420rn_projekat.entities.Tag;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PgArticleTagRepository extends Postgres implements ArticleTagRepository {

    @Override
    public void addTagToArticle(ArticleTag articleTag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO article_tag (article_id, tag_id) VALUES(?, ?)");
            preparedStatement.setInt(1, articleTag.getArticleId());
            preparedStatement.setInt(2, articleTag.getTagId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Tag> getTagsFromArticle(Integer articleId) {
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM tags INNER JOIN article_tag a on tags.id = a.tag_id AND a.article_id = ?");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"), resultSet.getString("name")));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }

    @Override
    public List<Article> getArticlesFromTag(Integer tagId, Integer page) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            int offset = (page - 1) * 10;

            preparedStatement = connection.prepareStatement("SELECT * FROM articles INNER JOIN article_tag a on articles.id = a.article_id AND a.tag_id = ? OFFSET ? ROWS LIMIT 10");
            preparedStatement.setInt(1, tagId);
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
    public void deleteArticle(Integer articleId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM article_tag WHERE article_id = ?");
            preparedStatement.setInt(1, articleId);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
