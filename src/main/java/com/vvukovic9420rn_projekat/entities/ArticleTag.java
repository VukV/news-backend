package com.vvukovic9420rn_projekat.entities;

import javax.validation.constraints.NotNull;

public class ArticleTag {

    @NotNull
    private Integer articleId;
    @NotNull
    private Integer tagId;

    public ArticleTag(){
    }

    public ArticleTag(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
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
