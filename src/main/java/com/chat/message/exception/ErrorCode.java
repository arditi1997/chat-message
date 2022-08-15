package com.chat.message.exception;

import lombok.Getter;

public enum ErrorCode {
    BAD_REQUEST(1000, Constants.BAD_REQUEST),
    USER_ALREADY_REGISTERED(1001, Constants.USER_ALREADY_REGISTERED),
    ROOM_NOT_FOUND(1001, Constants.ROOM_NOT_FOUND),
    USER_NOT_FOUND(1001, Constants.USER_NOT_FOUND),
    USERNAME_OR_PASSWORD_INVALID(1001, Constants.USERNAME_OR_PASSWORD_INVALID);

    private final long code;
    private final String message;
    public static class Constants{
        public final static String BAD_REQUEST = "Please check again your inputs!";
        public final static String USER_ALREADY_REGISTERED = "User is already registered!";
        public final static String ROOM_NOT_FOUND = "Room not found!";
        public final static String USER_NOT_FOUND = "User not found!";
        public final static String USERNAME_OR_PASSWORD_INVALID = "Username or password not valid!";
    }

    ErrorCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
