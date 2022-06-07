package com.vvukovic9420rn_projekat.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateArticleRequest {

    @NotNull
    private Integer categoryId;

    @NotNull @NotEmpty
    private String title;
    @NotNull @NotEmpty
    private String content;
    @NotNull @NotEmpty
    private String tags;

    public CreateArticleRequest() {
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
