package com.vvukovic9420rn_projekat.services;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.repositories.article.ArticleRepository;
import com.vvukovic9420rn_projekat.requests.CreateArticleRequest;

import javax.inject.Inject;
import java.util.List;

public class ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles(Integer page){
        return articleRepository.getAllArticlesSortedByDate(page);
    }

    public List<Article> getArticlesByCategory(Integer id, Integer page){
        return articleRepository.getAllArticlesByCategorySortedByDate(id, page);
    }

    public void addArticle(CreateArticleRequest createArticleRequest){

    }
}
