package com.vvukovic9420rn_projekat.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateArticleRequest {

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
}
