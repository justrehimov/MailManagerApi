package com.mailmanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
public enum Error {
    USER_NOT_FOUND(101,"User not found"),
    TOKEN_IS_INVALID(102, "Token is invalid");

    private String message;
    private int code;

    Error(int i, String s) {
        this.code = i;
        this.message = s;
    }
}
