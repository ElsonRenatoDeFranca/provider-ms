package com.projects.provider.mapper;

import com.projects.provider.dto.BudgetDTO;
import com.projects.provider.model.Budget;
import com.projects.provider.vo.BudgetVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface BudgetMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "budgetId", source = "budgetId")
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "requestDescription", source = "requestDescription")
    BudgetDTO entityToDto(Budget budgetEntityModel);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "budgetId", source = "budgetId")
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "requestDescription", source = "requestDescription")
    BudgetVO dtoToVO(BudgetDTO budgetDTO);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "budgetId", source = "budgetId")
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "requestDescription", source = "requestDescription")
    Budget dtoToEntity(BudgetDTO budgetDTO);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "budgetId", source = "budgetId")
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "requestDescription", source = "requestDescription")
    BudgetDTO voToDTO(BudgetVO budgetDTO);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "budgetId", source = "budgetId")
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "requestDescription", source = "requestDescription")
    List<BudgetDTO> entityListToDtoList(List<Budget> budgetEntities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "budgetId", source = "budgetId")
    @Mapping(target = "costCenter", source = "costCenter")
    @Mapping(target = "requestDescription", source = "requestDescription")
    List<BudgetVO> dtoListToVOList(List<BudgetDTO> budgetDTOs);
}
