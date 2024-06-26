package com.projects.provider.controller;

import com.projects.provider.exception.ResponseMessage;
import com.projects.provider.mapper.ProviderMapper;
import com.projects.provider.service.ExcelService;
import com.projects.provider.service.ProviderService;
import com.projects.provider.util.ExcelHelper;
import com.projects.provider.vo.ProviderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class ProviderController implements ProviderApi {

    private static final Logger log = LoggerFactory.getLogger(ProviderController.class);
    private final ProviderService providerService;
    private final ProviderMapper providerMapper;
    private final ExcelService excelService;

    public ProviderController(ProviderService providerService, ProviderMapper providerMapper, ExcelService excelService) {
        this.providerService = providerService;
        this.providerMapper = providerMapper;
        this.excelService = excelService;
    }

    @Override
    public ResponseEntity<Void> save(ProviderVO providerVO) {
        log.info("Saving provider {}", providerVO);
        providerService.save(providerMapper.voToDTO(providerVO));

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Override
    public ResponseEntity<List<ProviderVO>> findAll() {
        log.info("Searching all providers");
        return ResponseEntity.ok(providerMapper.providerDTOListToProviderVOList(providerService.findAll()));
    }

    @Override
    public ResponseEntity<ProviderVO> findByProviderId(String providerId) {
        log.info("Searching provider by id {}", providerId);
        ProviderVO providerVO = providerMapper.dtoToVO(providerService.findByProviderId(providerId));

        if (providerVO == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(providerVO);
    }

    @Override
    public ResponseEntity<Void> deleteByProviderId(String providerId) {
        log.info("Deleting provider by id {}", providerId);
        providerService.deleteByProviderId(providerId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateByProviderId(ProviderVO providerVO, String providerId) {
        log.info("Update provider by id {}", providerId);
        var providerDto = providerMapper.voToDTO(providerVO);

        providerService.updateByProviderId(providerDto, providerId);
        return ResponseEntity.noContent().build();
    }

}
