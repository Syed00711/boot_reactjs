package com.cerner.avaya.rad.model;

public class UserMessage {
    private String userId;

    public UserMessage(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }
}