package com.projects.provider.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String PROVIDER_ALREADY_EXISTS_ERROR_CODE = "0001";
    private final static String PROVIDER_NOT_FOUND_ERROR_CODE = "0002";
    private final static String PROVIDER_INTERNAL_SERVER_ERROR_CODE = "0003";


    @ExceptionHandler({ProviderNotFoundException.class})
    public ResponseEntity<Object> handleProviderNotFoundException(ProviderNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Error.builder()
                        .code(PROVIDER_NOT_FOUND_ERROR_CODE)
                        .description(exception.getMessage())
                        .build());
    }
    @ExceptionHandler({ProviderAlreadyExistsException.class})
    public ResponseEntity<Object> handleProviderAlreadyExistsException(ProviderAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .code(PROVIDER_ALREADY_EXISTS_ERROR_CODE)
                        .description(exception.getMessage())
                        .build());
    }
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Error.builder()
                        .code(PROVIDER_INTERNAL_SERVER_ERROR_CODE)
                        .description(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
    }
}
