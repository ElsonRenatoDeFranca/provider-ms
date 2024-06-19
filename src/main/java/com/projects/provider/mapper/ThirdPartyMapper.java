package com.projects.provider.mapper;


import com.projects.provider.dto.ThirdPartyDTO;
import com.projects.provider.model.ThirdPartyEntity;
import com.projects.provider.vo.ThirdPartyVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ThirdPartyMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "thirdPartyId", source = "thirdPartyId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lpuProfile", source = "lpuProfile")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "valuePerHour", source = "valuePerHour")
    ThirdPartyDTO entityToDto(ThirdPartyEntity thirdPartyEntity);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "thirdPartyId", source = "thirdPartyId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lpuProfile", source = "lpuProfile")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "valuePerHour", source = "valuePerHour")
    ThirdPartyVO dtoToVO(ThirdPartyDTO thirdPartyDTO);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "thirdPartyId", source = "thirdPartyId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lpuProfile", source = "lpuProfile")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "valuePerHour", source = "valuePerHour")
    ThirdPartyEntity dtoToEntity(ThirdPartyDTO thirdPartyDTO);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "thirdPartyId", source = "thirdPartyId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lpuProfile", source = "lpuProfile")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "valuePerHour", source = "valuePerHour")
    ThirdPartyDTO voToDTO(ThirdPartyVO thirdPartyVO);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "thirdPartyId", source = "thirdPartyId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lpuProfile", source = "lpuProfile")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "valuePerHour", source = "valuePerHour")
    List<ThirdPartyDTO> entityListToDtoList(List<ThirdPartyEntity> thirdPartyEntities);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "thirdPartyId", source = "thirdPartyId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lpuProfile", source = "lpuProfile")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "manager", source = "manager")
    @Mapping(target = "valuePerHour", source = "valuePerHour")
    List<ThirdPartyVO> dtoListToVOList(List<ThirdPartyDTO> thirdPartyDTOs);

}
