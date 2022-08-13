package com.chat.message.model;

public enum RoleEnum {
    ADMIN("ADMIN"), USER("USER");
    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
