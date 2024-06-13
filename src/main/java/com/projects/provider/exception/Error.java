package com.projects.provider.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Error implements Serializable {
    private String code;
    private String description;
}
