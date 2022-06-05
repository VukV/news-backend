package com.vvukovic9420rn_projekat.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateUserRequest {

    @NotNull @NotEmpty
    private String name;
    @NotNull @NotEmpty
    private String surname;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String type;

    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String passwordRepeat;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String name, String surname, String email, String type, String password, String passwordRepeat) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
