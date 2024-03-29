package com.vvukovic9420rn_projekat.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

    private Integer id;

    @NotNull
    private String password;

    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String surname;

    @NotNull @NotEmpty
    private String type;
    @NotNull
    private Boolean isActive;

    public User() {
    }

    public User(Integer id, String password, String email, String name, String surname, String type, Boolean isActive) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
