package com.Anime.App.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message) {
        super(message);
    }
}
