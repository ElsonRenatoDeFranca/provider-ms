package com.projects.provider.controller;

import com.projects.provider.exception.ProviderMismatchException;
import com.projects.provider.exception.ProviderNotFoundException;
import com.projects.provider.dto.ProviderDTO;
import com.projects.provider.mapper.ProviderMapper;
import com.projects.provider.service.ProviderService;
import com.projects.provider.vo.ProviderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController implements ProviderApi {

    private static final Logger log = LoggerFactory.getLogger(ProviderController.class);
    private final ProviderService providerService;
    private final ProviderMapper providerMapper;

    public ProviderController(ProviderService providerService, ProviderMapper providerMapper) {
        this.providerService = providerService;
        this.providerMapper = providerMapper;
    }

    @Override
    public ResponseEntity<Void> save(ProviderVO providerVO) {
        log.info("Saving provider {}", providerVO);
        try {
            providerService.save(providerMapper.voToDTO(providerVO));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (ProviderMismatchException e) {
            return ResponseEntity.badRequest().build();
        }
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

        try {
            providerService.deleteByProviderId(providerId);
            ProviderDTO customerDto = providerService.findByProviderId(providerId);

            if (customerDto == null) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } catch (ProviderNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Void> updateByProviderId(ProviderDTO providerDto, String providerId) {
        log.info("Update provider by id {}", providerId);

        try {
            providerService.updateByProviderId(providerDto, providerId);
            return ResponseEntity.ok().build();
        } catch (ProviderNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
