package com.vvukovic9420rn_projekat.requests;

import javax.validation.constraints.NotNull;

public class ChangeUserStatusRequest {

    @NotNull
    private Integer userId;
    @NotNull
    private Boolean status;

    public ChangeUserStatusRequest(Integer userId, Boolean status) {
        this.userId = userId;
        this.status = status;
    }

    public ChangeUserStatusRequest() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
