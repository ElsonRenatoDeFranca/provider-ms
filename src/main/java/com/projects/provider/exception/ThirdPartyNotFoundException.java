package com.projects.provider.exception;

public class ThirdPartyNotFoundException extends RuntimeException{
    public ThirdPartyNotFoundException(String exception) {
        super(exception);
    }
}
