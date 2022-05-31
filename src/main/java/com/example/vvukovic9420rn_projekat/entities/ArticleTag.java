package com.example.vvukovic9420rn_projekat.entities;

import javax.validation.constraints.NotNull;

public class ArticleTag {

    private Integer id;

    @NotNull
    private Integer articleId;
    @NotNull
    private Integer tagId;

    public ArticleTag(){
    }

    public ArticleTag(Integer id, Integer articleId, Integer tagId) {
        this.id = id;
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
