package com.vvukovic9420rn_projekat.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateArticleRequest {

    @NotNull
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer categoryId;

    @NotNull @NotEmpty
    private String title;
    @NotNull @NotEmpty
    private String content;
    @NotNull @NotEmpty
    private String tags;

    public UpdateArticleRequest() {
    }

    public UpdateArticleRequest(Integer id, Integer userId, Integer categoryId, String title, String content, String tags) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
