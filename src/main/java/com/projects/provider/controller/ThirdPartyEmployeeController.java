package com.projects.provider.controller;


import com.projects.provider.mapper.ThirdPartyMapper;
import com.projects.provider.service.ThirdPartyService;
import com.projects.provider.vo.ThirdPartyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ThirdPartyEmployeeController implements ThirdPartyEmployeeAPI {

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyEmployeeController.class);

    private final ThirdPartyService service;
    private final ThirdPartyMapper mapper;

    public ThirdPartyEmployeeController(ThirdPartyService service, ThirdPartyMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Void> save(ThirdPartyVO thirdPartyVO) {
        service.save(mapper.voToDTO(thirdPartyVO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<ThirdPartyVO>> findAll() {
        return ResponseEntity.ok(mapper.dtoListToVOList(service.findAll()));
    }

    @Override
    public ResponseEntity<ThirdPartyVO> findByThirdPartyName(String thirdPartyName) {
        return ResponseEntity.ok(mapper.dtoToVO(service.findByThirdPartyName(thirdPartyName)));
    }

    @Override
    public ResponseEntity<Void> deleteByThirdPartyName(String thirdPartyName) {
        this.service.deleteByThirdPartyName(thirdPartyName);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateByThirdPartyName(ThirdPartyVO thirdPartyVO, String thirdPartyName) {
        this.service.updateByThirdPartyName(mapper.voToDTO(thirdPartyVO), thirdPartyName);
        return ResponseEntity.noContent().build();
    }
}
