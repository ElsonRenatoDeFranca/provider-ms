package com.projects.provider.mapper;


import com.projects.provider.model.Provider;
import com.projects.provider.dto.ProviderDTO;
import com.projects.provider.vo.ProviderVO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface ProviderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "providerId", source = "providerId")
    @Mapping(target = "providerName", source = "providerName")
    ProviderDTO entityToDto(Provider provider);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "providerId", source = "providerId")
    @Mapping(target = "providerName", source = "providerName")
    ProviderVO dtoToVO(ProviderDTO providerDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "providerId", source = "providerId")
    @Mapping(target = "providerName", source = "providerName")
    Provider dtoToEntity(ProviderDTO providerDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "providerId", source = "providerId")
    @Mapping(target = "providerName", source = "providerName")
    ProviderDTO voToDTO(ProviderVO providerVO);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "providerId", source = "providerId")
    @Mapping(target = "providerName", source = "providerName")
    List<ProviderDTO> providerEntityListToProviderDtoList(List<Provider> entityProviders);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "providerId", source = "providerId")
    @Mapping(target = "providerName", source = "providerName")
    List<ProviderVO> providerDTOListToProviderVOList(List<ProviderDTO> dtoProviders);


}
