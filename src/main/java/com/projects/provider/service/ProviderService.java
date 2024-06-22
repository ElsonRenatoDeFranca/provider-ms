package com.projects.provider.service;

import com.projects.provider.exception.ProviderAlreadyExistsException;
import com.projects.provider.exception.ProviderNotFoundException;
import com.projects.provider.mapper.ProviderMapper;
import com.projects.provider.model.Provider;
import com.projects.provider.dto.ProviderDTO;
import com.projects.provider.repository.ProviderRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
