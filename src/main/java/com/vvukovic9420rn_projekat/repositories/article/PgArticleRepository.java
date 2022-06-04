package com.vvukovic9420rn_projekat.repositories.article;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.util.List;

public class PgArticleRepository extends Postgres implements ArticleRepository {

    @Override
    public List<Article> getAllArticlesSortedByDate(Integer page) {
        return null;
    }

    @Override
    public List<Article> getAllArticlesByCategorySortedByDate(Integer categoryId, Integer page) {
        return null;
    }

    @Override
    public void addArticle(Article article) {

    }

    @Override
    public void updateArticle(Article article) {

    }

    @Override
    public void deleteArticleById(Integer id) {

    }

    @Override
    public List<Article> getNewestTenArticles() {
        return null;
    }

    @Override
    public List<Article> getTopTenArticles() {
        return null;
    }

    @Override
    public Article getArticleById(Integer id) {
        return null;
    }
}
