package com.vvukovic9420rn_projekat.responses;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ArticleCreatorResponse {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String surname;

    public ArticleCreatorResponse(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public ArticleCreatorResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
