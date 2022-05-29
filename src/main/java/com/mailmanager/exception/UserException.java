package com.mailmanager.exception;

import com.mailmanager.enums.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserException extends Exception{
    private Error error;

    public UserException(Error error) {
        this.error = error;
    }
}
