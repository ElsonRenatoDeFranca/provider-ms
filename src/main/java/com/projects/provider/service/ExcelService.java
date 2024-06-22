package com.projects.provider.service;


import com.projects.provider.model.Budget;
import com.projects.provider.model.Provider;
import com.projects.provider.repository.BudgetRepository;
import com.projects.provider.repository.ProviderRepository;
import com.projects.provider.util.ExcelHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ExcelService {
    private final BudgetRepository budgetRepository;
    private final ProviderRepository providerRepository;

    public void saveProviders(MultipartFile file) {
        try {
            List<Provider> providers = ExcelHelper.excelToProvider(file.getInputStream());
            providerRepository.saveAll(providers);
            log.info("Save providers {} time Elapsed: {}", providers.size());
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void saveBudget(MultipartFile file) {
        try {
            List<Budget> budgets = ExcelHelper.excelToBudget(file.getInputStream());
            budgetRepository.saveAll(budgets);
            log.info("Save budgets {} time Elapsed: {}", budgets.size());
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
