package com.vvukovic9420rn_projekat.repositories.article;

import com.vvukovic9420rn_projekat.entities.Article;
import java.util.List;

public interface ArticleRepository {

    List<Article> getAllArticlesSortedByDate(Integer page);
    List<Article> getAllArticlesByCategorySortedByDate(Integer categoryId, Integer page);
    void addArticle(Article article);
    void updateArticle(Article article);
    void deleteArticleById(Integer id);

    List<Article> getNewestTenArticles();
    List<Article> getTopTenArticles();
    Article getArticleById(Integer id);
}
