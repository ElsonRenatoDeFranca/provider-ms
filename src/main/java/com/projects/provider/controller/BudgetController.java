package com.projects.provider.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class BudgetController implements BudgetApi {
    @Override
    public ResponseEntity<byte[]> download() throws IOException {
        return null;
    }

    @Override
    public ResponseEntity<Void> upload(MultipartFile file) throws IOException {
        return null;
    }
}
