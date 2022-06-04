package com.vvukovic9420rn_projekat.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Comment {

    private Integer id;

    @NotNull
    private Integer articleId;

    @NotNull @NotEmpty
    private String author;
    @NotNull @NotEmpty
    private String content;

    private Date date;

    public Comment() {
    }

    public Comment(Integer id, Integer articleId, String author, String content, Date date) {
        this.id = id;
        this.articleId = articleId;
        this.author = author;
        this.content = content;
        this.date = date;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
