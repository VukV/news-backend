package com.example.vvukovic9420rn_projekat.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Article {

    private Integer id;

    @NotNull
    private Integer userId;
    @NotNull
    private Integer categoryId;

    @NotNull @NotEmpty
    private String title;
    @NotNull @NotEmpty
    private String content;
    @NotNull
    private Integer visitCount;
    @NotNull
    private Date date;

    public Article() {
    }

    public Article(Integer id, Integer userId, Integer categoryId, String title, String content, Integer visitCount, Date date) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.visitCount = visitCount;
        this.date = date;
    }
}
