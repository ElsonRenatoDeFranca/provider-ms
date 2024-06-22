package com.projects.provider.controller;

import com.projects.provider.mapper.BudgetMapper;
import com.projects.provider.service.BudgetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class BudgetController implements BudgetApi {
    private static final Logger log = LoggerFactory.getLogger(BudgetController.class);
    private final BudgetService budgetService;
    private final BudgetMapper budgetMapper;

    public BudgetController(BudgetService budgetService, BudgetMapper budgetMapper) {
        this.budgetService = budgetService;
        this.budgetMapper = budgetMapper;
    }

    @Override
    public ResponseEntity<byte[]> download() throws IOException {
        return this.budgetService.generateExcelReports();
    }

    @Override
    public ResponseEntity<Void> upload(MultipartFile file) throws IOException {
        this.budgetService.upload(file);
        return ResponseEntity.ok().build();
    }
}
