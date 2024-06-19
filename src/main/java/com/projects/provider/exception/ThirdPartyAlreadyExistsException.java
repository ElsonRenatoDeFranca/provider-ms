package com.projects.provider.exception;

public class ThirdPartyAlreadyExistsException extends RuntimeException{
    public ThirdPartyAlreadyExistsException(String exception) {
        super(exception);
    }
}
