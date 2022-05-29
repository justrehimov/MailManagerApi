package com.mailmanager.exception;

import com.mailmanager.enums.Error;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenException extends Exception{
    private Error error;

    public TokenException(Error error){
        this.error = error;
    }
}
