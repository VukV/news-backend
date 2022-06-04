package com.vvukovic9420rn_projekat.repositories.articletag;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.entities.ArticleTag;
import com.vvukovic9420rn_projekat.entities.Tag;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            preparedStatement.setInt(1, articleTag.getTagId());

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
        return null;
    }

    @Override
    public List<Article> getArticlesFromTag(Integer tagId) {
        return null;
    }
}
