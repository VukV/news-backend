package com.vvukovic9420rn_projekat.repositories.articletag;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.entities.ArticleTag;
import com.vvukovic9420rn_projekat.entities.Tag;

import java.util.List;

public interface ArticleTagRepository {

    void addTagToArticle(ArticleTag articleTag);

    List<Tag> getTagsFromArticle(Integer articleId);
    List<Article> getArticlesFromTag(Integer tagId, Integer page);
}
