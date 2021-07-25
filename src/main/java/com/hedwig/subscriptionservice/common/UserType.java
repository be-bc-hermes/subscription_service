package com.hedwig.subscriptionservice.common;

public enum UserType {
    MOBILE("MOBILE"),
    DESKTOP("DESKTOP");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
