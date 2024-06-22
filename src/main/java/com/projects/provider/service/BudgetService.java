package com.projects.provider.service;

import com.projects.provider.dto.BudgetDTO;
import com.projects.provider.mapper.BudgetMapper;
import com.projects.provider.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
    }

    public List<BudgetDTO> findAll() {
        return budgetMapper.entityListToDtoList(budgetRepository.findAll());
    }

}
