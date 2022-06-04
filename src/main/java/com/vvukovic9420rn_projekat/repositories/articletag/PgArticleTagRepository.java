package com.vvukovic9420rn_projekat.repositories.articletag;

import com.vvukovic9420rn_projekat.entities.Article;
import com.vvukovic9420rn_projekat.entities.ArticleTag;
import com.vvukovic9420rn_projekat.entities.Tag;
import com.vvukovic9420rn_projekat.repositories.Postgres;

import java.util.List;

public class PgArticleTagRepository extends Postgres implements ArticleTagRepository {

    @Override
    public void addTagToArticle(ArticleTag articleTag) {

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
