package com.projects.provider.service;

import com.projects.provider.exception.ProviderAlreadyExistsException;
import com.projects.provider.exception.ProviderNotFoundException;
import com.projects.provider.mapper.ProviderMapper;
import com.projects.provider.model.Provider;
import com.projects.provider.dto.ProviderDTO;
import com.projects.provider.repository.ProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;
    private final ProviderMapper providerMapper;

    private static final String PROVIDER_NOT_FOUND_EXCEPTION_MESSAGE = "Provider not found";
    private static final String PROVIDER_MISMATCH_EXCEPTION_MESSAGE = "Provider already exists";


    public ProviderService(ProviderRepository crmRepository, ProviderMapper crmMapper) {
        this.providerRepository = crmRepository;
        this.providerMapper = crmMapper;
    }

    public List<ProviderDTO> findAll() {
        return providerMapper.providerEntityListToProviderDtoList(providerRepository.findAll());
    }

    public ProviderDTO findByProviderId(String providerId) throws ProviderNotFoundException {
        Provider provider = providerRepository.findByProviderId(providerId);
        if (provider == null) {
            throw new ProviderNotFoundException(PROVIDER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return providerMapper.entityToDto(provider);
    }

    @Transactional
    public void save(ProviderDTO providerDto) throws ProviderAlreadyExistsException {
        Provider provider = providerRepository.findByProviderId(providerDto.getProviderId());

        if (provider != null) {
            throw new ProviderAlreadyExistsException(PROVIDER_MISMATCH_EXCEPTION_MESSAGE);
        }
        Provider newProvider = providerMapper.dtoToEntity(providerDto);
        providerRepository.save(newProvider);
    }

    @Transactional
    public void deleteByProviderId(String providerId) throws ProviderNotFoundException {
        Provider provider = providerRepository.findByProviderId(providerId);

        if (provider == null) {
            throw new ProviderNotFoundException(PROVIDER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        providerRepository.deleteByProviderId(providerId);

    }

    @Transactional
    public ProviderDTO updateByProviderId(ProviderDTO providerDto, String providerId) throws ProviderNotFoundException {
        Provider existingProvider = providerRepository.findByProviderId(providerId);
        Provider updatedProvider = providerMapper.dtoToEntity(providerDto);

        if (existingProvider == null) {
            throw new ProviderNotFoundException(PROVIDER_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        providerRepository.deleteByProviderId(providerId);
        providerRepository.save(updatedProvider);
        return providerMapper.entityToDto(updatedProvider);
    }


}
