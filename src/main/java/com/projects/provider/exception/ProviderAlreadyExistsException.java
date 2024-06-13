package com.projects.provider.exception;

public class ProviderAlreadyExistsException extends RuntimeException{
    public ProviderAlreadyExistsException(String exception) {
        super(exception);
    }
}
