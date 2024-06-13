package com.projects.provider.exception;

public class ProviderNotFoundException extends RuntimeException{
    public ProviderNotFoundException(String exception) {
        super(exception);
    }
}
